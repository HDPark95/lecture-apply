package io.hhplus.lectureapply.domain.lectureapply;

import io.hhplus.lectureapply.domain.core.BaseEntity;
import io.hhplus.lectureapply.domain.lecture.Lecture;
import io.hhplus.lectureapply.domain.participant.Participant;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "reservation",
        uniqueConstraints = @UniqueConstraint(columnNames = {"lecture_id", "student_id"}) //동일한 강의에 대한 중복 예약 방지
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class LectureApply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), nullable = false)
    private Lecture lecture;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT) ,nullable = false)
    private Participant participant;

    @Column(name = "lecture_date")
    private LocalDateTime lectureDate;

    @Column(name = "lecturer_name")
    private String lecturerName;

    @Column(name = "lecture_title")
    private String lectureTitle;

    @Builder
    public LectureApply(Long id, Lecture lecture, Participant participant, LocalDateTime lectureDate, String lecturerName, String lectureTitle) {
        this.id = id;
        this.lecture = lecture;
        this.participant = participant;
        this.lectureDate = lectureDate;
        this.lecturerName = lecturerName;
        this.lectureTitle = lectureTitle;
    }
}
