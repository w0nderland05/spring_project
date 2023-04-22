package org.study.models.study;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.commons.constants.Status;
import org.study.controllers.admin.study.StudyConfig;
import org.study.entities.Study;
import org.study.repositories.StudyRepository;

@Service
public class StudyApplyService {

    @Autowired
    private  StudyRepository repository;



    public void apply(StudyConfig config) {
        Study study = Study.builder()
                .studyCode(config.getStudyCode())
                .studyNm(config.getStudyNm())
                .category(config.getCategory())
                .requestDt(config.getRequestDt())
                .approveStatus(Status.APPLY)
                .regStatusDt(config.getRegStatusDt())
                .maxMember(config.getMaxMember())
                .remainSeat(config.getRemainSeat())
                .activeStatus(config.isActiveStatus())
                .numOfWeek(config.getNumOfWeek())
                .regionType(config.getRegionType())
                .simpleIntro(config.getSimpleIntro())
                .introduction(config.getIntroduction())
                .build();

        repository.save(study);
    }


    }

