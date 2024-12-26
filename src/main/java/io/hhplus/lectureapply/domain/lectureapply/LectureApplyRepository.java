package io.hhplus.lectureapply.domain.lectureapply;

import java.util.List;

public interface LectureApplyRepository {
    LectureApply save(LectureApply lectureApply);

    List<LectureApply> findAllByParticipantId(Long participantId);
}
