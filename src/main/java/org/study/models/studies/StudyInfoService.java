package org.study.models.studies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.study.commons.validators.StudyNotFoundException;
import org.study.entities.Studies;
import org.study.repositories.StudiesRepository;

/**
 * 스터디 개별 조회
 *
 */
@Service
@RequiredArgsConstructor
public class StudyInfoService {

    private final StudiesRepository studiesRepository;


    /**
     * 스터디 번호로 조회
     * @param studyNo
     * @param isMine
     * @return
     */
    public Studies get(Long studyNo, boolean isMine) {

        Studies study = studiesRepository.findById(studyNo).orElseThrow(StudyNotFoundException::new);

        return study;
    }
}
