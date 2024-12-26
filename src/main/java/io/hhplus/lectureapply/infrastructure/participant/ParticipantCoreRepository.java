package io.hhplus.lectureapply.infrastructure.participant;

import io.hhplus.lectureapply.domain.participant.Participant;
import io.hhplus.lectureapply.domain.participant.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ParticipantCoreRepository implements ParticipantRepository {

    private final ParticipantJPARepository participantJPARepository;

    @Override
    public Participant save(Participant participant) {
        return participantJPARepository.save(participant);
    }
}
