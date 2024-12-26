package io.hhplus.lectureapply.application.lecture;

import io.hhplus.lectureapply.domain.lecture.LectureInfo;
import io.hhplus.lectureapply.domain.lecture.LectureRepository;
import io.hhplus.lectureapply.intrastucture.LectureRepositoryStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

class LectureServiceTest {

    private LectureRepository lectureRepository = new LectureRepositoryStub();
    private LectureService lectureService = new LectureServiceImpl(lectureRepository);

    @Test
    void 수강신청시작일_이전에는_강의목록가_조회되지_않는다() {
        // given
        LocalDate targetDate = LocalDate.of(2024, 10, 1);
        LocalDateTime currentDateTime = LocalDateTime.of(2024, 8, 1, 9, 0, 0);

        // when
        List<LectureInfo> availableLectures = lectureService.getAvailableLectures(targetDate, currentDateTime);

        // then
        Assertions.assertTrue(availableLectures.isEmpty());
    }

    @Test
    void 강의일_이후의_강의목록은_조회되지_않는다() {
        // given
        LocalDate targetDate = LocalDate.of(2024, 10, 1);
        LocalDateTime currentDateTime = LocalDateTime.of(2024, 10, 2, 9, 0, 0);
        // when
        List<LectureInfo> availableLectures = lectureService.getAvailableLectures(targetDate, currentDateTime);
        // then
        Assertions.assertTrue(availableLectures.isEmpty());
    }

    @Test
    void 현재_수강신청인원이_수용인원을_초과한_강의목록은_조회되지_않는다() {
        // given
        LocalDate targetDate = LocalDate.of(2023, 10, 1);
        LocalDateTime currentDateTime = LocalDateTime.of(2023, 9, 3, 10, 0, 0);
        // when
        List<LectureInfo> availableLectures = lectureService.getAvailableLectures(targetDate, currentDateTime);
        // then
        Assertions.assertTrue(availableLectures.isEmpty());
    }

    @Test
    void 수강신청시작일_이후이며_강의일_이전이며_수강신청인원이_수용인원을_초과하지_않은_강의목록만_조회된다() {
        // given
        LocalDate targetDate = LocalDate.of(2024, 10, 1);
        LocalDateTime currentDateTime = LocalDateTime.of(2024, 9, 2, 9, 0, 0);
        // when
        List<LectureInfo> availableLectures = lectureService.getAvailableLectures(targetDate, currentDateTime);
        // then
        Assertions.assertEquals(1, availableLectures.size());
    }
}