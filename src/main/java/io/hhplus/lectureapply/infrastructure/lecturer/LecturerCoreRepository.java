package io.hhplus.lectureapply.infrastructure.lecturer;

import io.hhplus.lectureapply.domain.lecturer.Lecturer;
import io.hhplus.lectureapply.domain.lecturer.LecturerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class LecturerCoreRepository implements LecturerRepository {
    private final LecturerJPARepository lecturerJpaRepository;

    @Override
    public Lecturer save(Lecturer lecturer) {
        return lecturerJpaRepository.save(lecturer);
    }
}
