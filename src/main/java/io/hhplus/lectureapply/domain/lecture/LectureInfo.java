package io.hhplus.lectureapply.domain.lecture;

import java.time.LocalDateTime;

public record LectureInfo(Long id,
                          String title,
                          String description,
                          String lecturerName,
                          int maxParticipants,
                          int currentParticipants,
                          LocalDateTime applyStartTime,
                          LocalDateTime lectureDateTime) {
    public static LectureInfo from(Lecture lecture) {
        return new LectureInfo(lecture.getId(),
                lecture.getTitle(),
                lecture.getDescription(),
                lecture.getLecturer().getName(),
                lecture.getMaxParticipants(),
                lecture.getCurrentParticipants(),
                lecture.getApplyStartDate(),
                lecture.getLectureDateTime());
    }
}
