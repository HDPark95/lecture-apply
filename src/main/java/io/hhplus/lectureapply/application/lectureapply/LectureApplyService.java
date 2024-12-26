package io.hhplus.lectureapply.application.lectureapply;

import io.hhplus.lectureapply.domain.lectureapply.LectureApply;

import java.time.LocalDateTime;

public interface LectureApplyService {

    LectureApply apply(Long lectureId, Long participantId, LocalDateTime currentDateTime);
}
