package org.study.models.study;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.study.commons.UserUtils;
import org.study.commons.constants.RegionType;
import org.study.commons.constants.Status;
import org.study.controllers.admin.study.StudyConfig;
import org.study.entities.Study;
import org.study.repositories.StudyRepository;

@Service("StudySavesService")
@RequiredArgsConstructor
public class StudySaveService {
    /**
    private final StudyRepository repository;
    private final UserUtils userUtils;

    public void save(StudyConfig studyConfig){
        Study study = null;
        Long studyCode = study.getStudyCode();
        String mode = studyConfig.getMode();
        if(mode != null && mode.equals("update")){
            study = repository.findById(studyCode).orElseGet(()-> Study.builder().studyCode(studyCode).build());
        }else{
            study = new Study();
            study.setStudyCode(studyCode);
        }

        study.setStudyCode(studyCode);
        study.setStudyNm(studyConfig.getStudyNm());
        study.setCategory(studyConfig.getCategory());
        study.setMaxMember(studyConfig.getMaxMember());
        study.setNumOfWeek(studyConfig.getNumOfWeek());
        study.setRegionType(RegionType.valueOf(studyConfig.getRegionType()));
        study.setSimpleIntro(studyConfig.getSimpleIntro());
        study.setIntroduction(studyConfig.getIntroduction());
        study.setApproveStatus(Status.valueOf(studyConfig.getApproveStatus()));
        study.setUser(userUtils.getEntity());

        repository.saveAndFlush(study);
    }
     */
}
