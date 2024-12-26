package io.hhplus.lectureapply.infrastructure.participant;

import io.hhplus.lectureapply.domain.participant.Participant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantJPARepository extends JpaRepository<Participant, Long> {

    Participant save(Participant participant);
}
