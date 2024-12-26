package io.hhplus.lectureapply.application.lectureapply;

import io.hhplus.lectureapply.domain.lectureapply.DuplicateLectureApplyException;
import io.hhplus.lectureapply.domain.lecture.Lecture;
import io.hhplus.lectureapply.domain.lecture.LectureRepository;
import io.hhplus.lectureapply.domain.lectureapply.LectureApply;
import io.hhplus.lectureapply.domain.lectureapply.LectureApplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectureApplyServiceImpl implements LectureApplyService {

    private final LectureApplyRepository lectureApplyRepository;
    private final LectureRepository lectureRepository;

    @Override
    @Transactional(readOnly = false)
    public LectureApply apply(Long lectureId, Long participantId, LocalDateTime currentDateTime) {
        Lecture lecture = lectureRepository.getLectureForApply(lectureId);
        try{
            return lectureApplyRepository.save(lecture.apply(participantId, currentDateTime));
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateLectureApplyException("이미 신청한 강의입니다.");
        }
    }
}
