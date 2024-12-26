package io.hhplus.lectureapply.domain.lecture;

public class LectureNotFoundException extends RuntimeException {
    public LectureNotFoundException(String message) {
        super(message);
    }
}
