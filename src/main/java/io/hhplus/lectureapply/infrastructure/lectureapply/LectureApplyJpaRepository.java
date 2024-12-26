package io.hhplus.lectureapply.infrastructure.lectureapply;

import io.hhplus.lectureapply.domain.lectureapply.LectureApply;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureApplyJpaRepository extends JpaRepository<LectureApply, Long> {

    List<LectureApply> findAllByParticipantId(Long participantId);
}
