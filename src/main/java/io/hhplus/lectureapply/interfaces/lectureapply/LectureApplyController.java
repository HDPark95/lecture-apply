package io.hhplus.lectureapply.interfaces.lectureapply;

import io.hhplus.lectureapply.application.lectureapply.LectureApplyService;
import io.hhplus.lectureapply.domain.lectureapply.LectureApply;
import io.hhplus.lectureapply.interfaces.lecture.LectureResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<LectureApplyResponse>> getLectureApply(@RequestParam Long participantId) {
        List<LectureApply> lectureApplies = lectureApplyService.getLectureApply(participantId);
        return ResponseEntity.ok(lectureApplies.stream().map(LectureApplyResponse::from).toList());
    }
}
