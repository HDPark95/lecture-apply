package io.hhplus.lectureapply.infrastructure.lecture;

import com.querydsl.jpa.impl.JPAQueryFactory;
import io.hhplus.lectureapply.domain.lecture.Lecture;
import io.hhplus.lectureapply.domain.lecture.LectureNotFoundException;
import io.hhplus.lectureapply.domain.lecture.LectureRepository;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static io.hhplus.lectureapply.domain.lecture.QLecture.lecture;
import static io.hhplus.lectureapply.domain.lecturer.QLecturer.lecturer;

@Repository
@RequiredArgsConstructor
public class LectureCoreRepositoryImpl implements LectureRepository {

    private final LectureJpaRepository lectureJpaRepository;

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Lecture> findAvailableLectures(LocalDate targetDate, LocalDateTime currentTime) {
        return jpaQueryFactory.selectFrom(lecture)
                .innerJoin(lecture.lecturer, lecturer).fetchJoin()
                .where(
                        lecture.applyStartDate.loe(currentTime),
                        lecture.currentParticipants.lt(lecture.maxParticipants),
                        lecture.lectureDateTime.gt(currentTime),
                        lecture.lectureDateTime.between(targetDate.atStartOfDay(), targetDate.plusDays(1).atStartOfDay())
                )
                .fetch();
    }

    @Override
    public Lecture save(Lecture lecture) {
        return lectureJpaRepository.save(lecture);
    }

    @Override
    public Lecture getLectureForApply(Long lectureId) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(lecture)
                        .innerJoin(lecture.lecturer, lecturer).fetchJoin()
                        .where(lecture.id.eq(lectureId))
                        .setLockMode(LockModeType.PESSIMISTIC_WRITE)
                        .fetchOne()
        ).orElseThrow(
                () -> new LectureNotFoundException("해당하는 강의가 없습니다.")
        );
    }
}
