package io.hhplus.lectureapply.domain.lectureapply;

public class DuplicateLectureApplyException extends RuntimeException {
    public DuplicateLectureApplyException(String message) {
        super(message);
    }
}
