package io.hhplus.lectureapply.domain.lecture;


import io.hhplus.lectureapply.domain.core.BaseEntity;
import io.hhplus.lectureapply.domain.lectureapply.LectureApply;
import io.hhplus.lectureapply.domain.lectureapply.LectureCapacityFullException;
import io.hhplus.lectureapply.domain.lectureapply.LectureNotInApplyPeriodException;
import io.hhplus.lectureapply.domain.lecturer.Lecturer;
import io.hhplus.lectureapply.domain.participant.Participant;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.time.LocalDateTime;


@Getter
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "lecture")
public class Lecture extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecturer_id" , foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), nullable = false)
    private Lecturer lecturer;

    @Column(name = "lecture_date_time", nullable = false)
    private LocalDateTime lectureDateTime;

    @Column(name = "apply_start_date", nullable = false)
    private LocalDateTime applyStartDate;

    @Column(name = "max_participants", nullable = false, columnDefinition = "INT DEFAULT 30")
    private int maxParticipants;

    @Column(name = "current_participants", nullable = false, columnDefinition = "INT DEFAULT 0")
    private int currentParticipants;

    @Builder
    public Lecture(Long id, String title, String description, Lecturer lecturer, LocalDateTime lectureDateTime, LocalDateTime applyStartDate, int maxParticipants, int currentParticipants) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.lecturer = lecturer;
        this.lectureDateTime = lectureDateTime;
        this.applyStartDate = applyStartDate;
        this.maxParticipants = maxParticipants;
        this.currentParticipants = currentParticipants;
    }

    public LectureApply apply(Long participantId, LocalDateTime currentDateTime) {
        if (currentParticipants >= maxParticipants) {
            throw new LectureCapacityFullException("수강 신청 인원이 마감되었습니다.");
        }
        if (currentDateTime.isBefore(applyStartDate) || currentDateTime.isAfter(lectureDateTime)) {
            throw new LectureNotInApplyPeriodException("수강 신청 기간이 아닙니다.");
        }
        currentParticipants++;
        return LectureApply.builder()
                .lecture(this)
                .lectureDate(lectureDateTime)
                .lecturerName(lecturer.getName())
                .lectureTitle(title)
                .participant(Participant.builder().id(participantId).build())
                .build();
    }
}
