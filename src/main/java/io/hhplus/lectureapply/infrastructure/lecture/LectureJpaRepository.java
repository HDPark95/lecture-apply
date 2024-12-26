package io.hhplus.lectureapply.infrastructure.lecture;

import io.hhplus.lectureapply.domain.lecture.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {

}
