package io.hhplus.lectureapply.infrastructure.lecturer;

import io.hhplus.lectureapply.domain.lecturer.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerJPARepository extends JpaRepository<Lecturer, Long> {
}
