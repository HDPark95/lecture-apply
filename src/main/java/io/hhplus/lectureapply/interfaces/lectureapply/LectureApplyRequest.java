package io.hhplus.lectureapply.interfaces.lectureapply;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
public class LectureApplyRequest {
    private Long lectureId;
    private Long participantId;

    public LectureApplyRequest(Long lectureId, Long participantId) {
        this.lectureId = lectureId;
        this.participantId = participantId;
    }
}
