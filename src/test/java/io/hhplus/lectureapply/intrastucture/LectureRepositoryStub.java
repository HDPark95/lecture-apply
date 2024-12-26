package io.hhplus.lectureapply.intrastucture;

import io.hhplus.lectureapply.domain.lecture.Lecture;
import io.hhplus.lectureapply.domain.lecture.LectureRepository;
import io.hhplus.lectureapply.domain.lecturer.Lecturer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class LectureRepositoryStub implements LectureRepository {

    private final List<Lecture> lectures = List.of(
            Lecture
                    .builder()
                    .title("항해 토요 특강")
                    .description("멘토링 세션")
                    .lectureDateTime(LocalDateTime.of(2024, 10, 1, 10, 0))
                    .applyStartDate(LocalDateTime.of(2024, 9, 1, 10, 0))
                    .maxParticipants(30)
                    .currentParticipants(5)
                    .lecturer(Lecturer.builder().name("김항해코치").email("hh@hhplus.com").build())
                    .build(),
            Lecture
                    .builder()
                    .title("항해 일요 특강")
                    .description("멘토링 세션")
                    .lectureDateTime(LocalDateTime.of(2023, 10, 2, 10, 0))
                    .applyStartDate(LocalDateTime.of(2023, 9, 2, 10, 0))
                    .maxParticipants(30)
                    .currentParticipants(30)
                    .lecturer(Lecturer.builder().name("박항해코치").email("hh@hhplus.com").build())
                    .build()
    );

    @Override
    public List<Lecture> findAvailableLectures(LocalDate targetDate, LocalDateTime currentTime) {
        return lectures.stream().filter(
                lecture -> lecture.getLectureDateTime().toLocalDate().equals(targetDate)
                        && lecture.getApplyStartDate().isBefore(currentTime)
                        && lecture.getLectureDateTime().isAfter(currentTime)
                        && lecture.getCurrentParticipants() < lecture.getMaxParticipants()
                )
                .toList();
    }

    @Override
    public Lecture save(Lecture lecture) {
        return lecture;
    }
}
