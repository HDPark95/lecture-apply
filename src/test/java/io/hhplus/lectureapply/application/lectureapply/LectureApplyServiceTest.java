package io.hhplus.lectureapply.application.lectureapply;

import io.hhplus.lectureapply.domain.lecture.LectureNotFoundException;
import io.hhplus.lectureapply.domain.lecture.LectureRepository;
import io.hhplus.lectureapply.domain.lectureapply.LectureApplyRepository;
import io.hhplus.lectureapply.domain.lectureapply.LectureCapacityFullException;
import io.hhplus.lectureapply.domain.lectureapply.LectureNotInApplyPeriodException;
import io.hhplus.lectureapply.intrastucture.LectureApplyRepositoryStub;
import io.hhplus.lectureapply.intrastucture.LectureRepositoryStub;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LectureApplyServiceTest {

    private LectureApplyRepository lectureApplyRepository = new LectureApplyRepositoryStub();
    private LectureRepository lectureRepository = new LectureRepositoryStub();
    private LectureApplyService lectureApplyService = new LectureApplyServiceImpl(lectureApplyRepository, lectureRepository);

    @Test
    void 신청하는_강의의_아이디가_잘못된_경우_실패() {
        //given
        Long lectureId = 3L;
        Long participantId = 1L;
        LocalDateTime currentDateTime = LocalDateTime.of(2024, 12, 1, 0, 0);
        //when & then
        assertThrows(LectureNotFoundException.class, () -> {
            lectureApplyService.apply(lectureId, participantId, currentDateTime);
        });
    }
    @Test
    void 수강_신청_가능_기간이_아니라_실패() {
        //given
        Long lectureId = 1L;
        Long participantId = 1L;
        LocalDateTime currentDateTime = LocalDateTime.of(2023, 12, 1, 0, 0);
        //when & then
        assertThrows(LectureNotInApplyPeriodException.class, () -> {
            lectureApplyService.apply(lectureId, participantId, currentDateTime);
        });
    }

    @Test
    void 수강_인원_초과로_실패() {
        //given
        Long lectureId = 2L;
        Long participantId = 1L;
        LocalDateTime currentDateTime = LocalDateTime.of(2023, 9, 3, 10, 0);
        //when & then
        assertThrows(LectureCapacityFullException.class, () -> {
            lectureApplyService.apply(lectureId, participantId, currentDateTime);
        });
    }

}