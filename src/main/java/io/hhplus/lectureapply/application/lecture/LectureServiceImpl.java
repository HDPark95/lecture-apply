package io.hhplus.lectureapply.application.lecture;

import io.hhplus.lectureapply.domain.lecture.Lecture;
import io.hhplus.lectureapply.domain.lecture.LectureInfo;
import io.hhplus.lectureapply.domain.lecture.LectureRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;

    @Override
    @Transactional
    public List<LectureInfo> getAvailableLectures(LocalDate targetDate, LocalDateTime currentTime) {
        List<Lecture> lectures = lectureRepository.findAvailableLectures(targetDate, currentTime);
        return lectures.stream().map(LectureInfo::from).toList();
    }

}
