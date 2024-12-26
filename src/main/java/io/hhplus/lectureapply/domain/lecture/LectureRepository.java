package io.hhplus.lectureapply.domain.lecture;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface LectureRepository {

    Lecture getLectureForApply(Long lectureId);

    List<Lecture> findAvailableLectures(LocalDate targetDate, LocalDateTime currentTime);

    Lecture save(Lecture lecture);
}
