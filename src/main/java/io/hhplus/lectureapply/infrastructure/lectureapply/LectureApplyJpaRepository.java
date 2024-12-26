package io.hhplus.lectureapply.infrastructure.lectureapply;

import io.hhplus.lectureapply.domain.lectureapply.LectureApply;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureApplyJpaRepository extends JpaRepository<LectureApply, Long> {

}
