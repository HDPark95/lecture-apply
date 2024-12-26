package io.hhplus.lectureapply.intrastucture;

import io.hhplus.lectureapply.domain.lecture.Lecture;
import io.hhplus.lectureapply.domain.lectureapply.LectureApply;
import io.hhplus.lectureapply.domain.lectureapply.LectureApplyRepository;
import io.hhplus.lectureapply.domain.participant.Participant;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDateTime;
import java.util.List;

public class LectureApplyRepositoryStub implements LectureApplyRepository {

    private final List<LectureApply> lectureApplies = List.of(
            LectureApply
                    .builder()
                    .lecture(Lecture.builder().id(2L).build())
                    .participant(Participant.builder().id(1L).build())
                    .build()
    );


    @Override
    public LectureApply save(LectureApply lectureApply) {
        return lectureApply;
    }
}
