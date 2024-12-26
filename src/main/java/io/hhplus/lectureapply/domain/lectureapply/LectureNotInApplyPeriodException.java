package io.hhplus.lectureapply.domain.lectureapply;

public class LectureNotInApplyPeriodException extends RuntimeException {
    public LectureNotInApplyPeriodException(String message) {
        super(message);
    }
}
