package io.hhplus.lectureapply.application.lectureapply;

import io.hhplus.lectureapply.domain.lecture.Lecture;
import io.hhplus.lectureapply.domain.lecture.LectureRepository;
import io.hhplus.lectureapply.domain.lecturer.Lecturer;
import io.hhplus.lectureapply.domain.lecturer.LecturerRepository;
import io.hhplus.lectureapply.domain.participant.Participant;
import io.hhplus.lectureapply.domain.participant.ParticipantRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LectureApplyServiceIntegrationTest {

    @Autowired
    private LectureApplyService lectureApplyService;

    @Autowired
    private LectureRepository lectureRepository;
    @Autowired
    private ParticipantRepository participantRepository;
    @Autowired
    private LecturerRepository lecturerRepository;

    Lecture lecture;

    @BeforeEach
    void setUp() {
        Lecturer lecturer = Lecturer.builder()
                .name("강사1")
                .email("test@email.com")
                .build();
        lecturer = lecturerRepository.save(lecturer);

        lecture = Lecture.builder()
                .title("통합 테스트 특강")
                .description("테스트용 특강 설명")
                .lectureDateTime(LocalDateTime.of(2024, 12, 31, 10, 0))
                .applyStartDate(LocalDateTime.of(2024, 12, 1, 10, 0))
                .maxParticipants(30)
                .currentParticipants(0)
                .lecturer(lecturer)
                .build();
        lectureRepository.save(lecture);

        // 수강생 40명 생성 및 저장
        for (int i = 1; i <= 40; i++) {
            Participant participant = Participant.builder()
                    .name("참가자" + i)
                    .phoneNumber("010-1234-" + String.format("%04d", i)) // 전화번호 고유화
                    .build();

            participantRepository.save(participant);
        }
    }

    @Test
    void 동시에_동일한_특강에_40명이_신청하면_30명만_성공한다() throws InterruptedException {
        // given
        Long lectureId = lecture.getId();
        LocalDateTime currentDateTime = LocalDateTime.of(2024, 12, 2, 10, 0);
        ExecutorService executorService = Executors.newFixedThreadPool(40); // 40개의 스레드 생성
        int totalApplicants = 40;

        // when
        List<Callable<Void>> tasks = new ArrayList<>();

        // 작업 생성
        for (int i = 0; i < totalApplicants; i++) {
            Long participantId = Long.valueOf(i + 1);
            tasks.add(() -> {
                try {
                    lectureApplyService.apply(lectureId, participantId, currentDateTime);
                } catch (Exception ignored) {
                    // 예외는 무시
                }
                return null;
            });
        }
        executorService.invokeAll(tasks);
        // then
        Lecture updatedLecture = lectureRepository.findById(lectureId).get();
        assertThat(updatedLecture.getCurrentParticipants()).isEqualTo(30); // 최대 30명만 성공
    }

    @Test
    @Transactional
    void 동일한_유저가_같은_특강에_5번_신청하면_1번만_성공한다() {
        // given
        Long lectureId = lecture.getId();
        Long participantId = 1L; // 동일한 유저 ID
        LocalDateTime currentDateTime = LocalDateTime.of(2024, 12, 2, 10, 0);

        // when
        int successfulApplications = 0;
        for (int i = 0; i < 5; i++) {
            try {
                lectureApplyService.apply(lectureId, participantId, currentDateTime);
                successfulApplications++;
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }

        // then
        assertThat(successfulApplications).isEqualTo(1); // 동일한 유저는 1번만 성공
    }
}
