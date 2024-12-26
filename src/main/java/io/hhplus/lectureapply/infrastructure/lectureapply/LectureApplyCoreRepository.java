package io.hhplus.lectureapply.infrastructure.lectureapply;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.hhplus.lectureapply.domain.lecture.Lecture;
import io.hhplus.lectureapply.domain.lectureapply.LectureApply;
import io.hhplus.lectureapply.domain.lectureapply.LectureApplyRepository;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static io.hhplus.lectureapply.domain.lecture.QLecture.lecture;

@Repository
@RequiredArgsConstructor
public class LectureApplyCoreRepository implements LectureApplyRepository {

    private final LectureApplyJpaRepository lectureApplyJpaRepository;

    @Override
    public LectureApply save(LectureApply lectureApply) {
        return lectureApplyJpaRepository.save(lectureApply);
    }

    @Override
    public List<LectureApply> findAllByParticipantId(Long participantId) {
        return lectureApplyJpaRepository.findAllByParticipantId(participantId);
    }
}
