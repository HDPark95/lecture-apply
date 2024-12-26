package io.hhplus.lectureapply.application.lectureapply;

import io.hhplus.lectureapply.domain.lectureapply.LectureApply;

import java.time.LocalDateTime;
import java.util.List;

public interface LectureApplyService {

    LectureApply apply(Long lectureId, Long participantId, LocalDateTime currentDateTime);

    List<LectureApply> getLectureApply(Long participantId);
}
