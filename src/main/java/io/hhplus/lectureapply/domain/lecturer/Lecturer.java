package io.hhplus.lectureapply.domain.lecturer;

import io.hhplus.lectureapply.domain.core.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lecturer")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Lecturer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Builder
    public Lecturer(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
