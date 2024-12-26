package io.hhplus.lectureapply.interfaces.lectureapply;

import io.hhplus.lectureapply.application.lectureapply.LectureApplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lecture-apply")
public class LectureApplyController {

    private final LectureApplyService lectureApplyService;

    @PostMapping
    public ResponseEntity<Void> apply(LectureApplyRequest request) {
        lectureApplyService.apply(request.getLectureId(), request.getParticipantId(), LocalDateTime.now());
        return ResponseEntity.status(201).build();
    }

}
