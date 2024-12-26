package io.hhplus.lectureapply.interfaces.lectureapply;

import io.hhplus.lectureapply.domain.lecture.Lecture;
import io.hhplus.lectureapply.domain.lectureapply.LectureApply;

import java.time.LocalDateTime;

public record LectureApplyResponse(
        Long lectureApplyId,
        String lecturerName,
        LocalDateTime lectureDateTime,
        String lectureTitle
) {
    public static LectureApplyResponse from(LectureApply lectureApply) {
        return new LectureApplyResponse(
                lectureApply.getId(),
                lectureApply.getLecturerName(),
                lectureApply.getLectureDate(),
                lectureApply.getLectureTitle()
        );
    }
}
