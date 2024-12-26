package io.hhplus.lectureapply.interfaces.lecture;


import io.hhplus.lectureapply.domain.lecture.LectureInfo;

import java.time.LocalDateTime;

public record LectureResponse(Long id,
                              String title,
                              String description,
                              String lecturerName,
                              int maxParticipants,
                              int currentParticipants,
                              LocalDateTime applyStartTime,
                              LocalDateTime lectureDateTime) {
    public static LectureResponse from(LectureInfo lectureInfo) {
        return new LectureResponse(lectureInfo.id(),
                lectureInfo.title(),
                lectureInfo.description(),
                lectureInfo.lecturerName(),
                lectureInfo.maxParticipants(),
                lectureInfo.currentParticipants(),
                lectureInfo.applyStartTime(),
                lectureInfo.lectureDateTime());
    }
}
