package io.hhplus.lectureapply.interfaces.lecture;

import io.hhplus.lectureapply.application.lecture.LectureService;
import io.hhplus.lectureapply.domain.lecture.LectureInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/api/v1/lectures")
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @GetMapping("/available")
    public ResponseEntity<List<LectureResponse>> getAvailableLectures(@RequestParam(required = false) LocalDate targetDate) {
        List<LectureInfo> availableLectures = lectureService.getAvailableLectures(targetDate, LocalDateTime.now());
        return ResponseEntity.ok(availableLectures.stream().map(LectureResponse::from).toList());
    }
}
