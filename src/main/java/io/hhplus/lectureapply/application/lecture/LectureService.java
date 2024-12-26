package io.hhplus.lectureapply.application.lecture;

import io.hhplus.lectureapply.domain.lecture.LectureInfo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface LectureService {
    List<LectureInfo> getAvailableLectures(LocalDate targetDate, LocalDateTime currentTime);
}
