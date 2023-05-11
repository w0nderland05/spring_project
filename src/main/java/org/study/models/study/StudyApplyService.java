package org.study.models.study;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.study.commons.UserUtils;
import org.study.commons.constants.RegionType;
import org.study.commons.constants.Status;
import org.study.controllers.admin.study.StudyConfig;
import org.study.entities.Study;
import org.study.repositories.StudyRepository;

@Service
@RequiredArgsConstructor
public class StudyApplyService {


    private final StudyRepository repository;

    private final StudyRegisterValidator validator;

    private final UserUtils userUtils;

    public void apply(StudyConfig config){
        apply(config, null);
    }


    public void apply(StudyConfig config, Errors errors) {

        // 로그인 하지 않은 경우 작성 불가 처리
        if(!userUtils.isLogin() || (errors !=null && errors.hasErrors())){
            return;
        }
        validator.check(config,errors);

       Long studyCd = config.getStudyCode();
       Study study = null;
       if(repository.exists(studyCd)){

           study= repository.findById(studyCd).orElseGet(() ->StudyConfig.of(config));
       }
       if(study == null){
           study = StudyConfig.of(config);
       }

        study.setStudyCode(studyCd);
        study.setStudyNm(config.getStudyNm());
        study.setCategory(config.getCategory());
        study.setMaxMember(config.getMaxMember());
        study.setNumOfWeek(config.getNumOfWeek());
        study.setRegionType(RegionType.valueOf(config.getRegionType()));
        study.setSimpleIntro(config.getSimpleIntro());
        study.setIntroduction(config.getIntroduction());
        study.setApproveStatus(Status.valueOf(config.getApproveStatus()));
        study.setUser(userUtils.getEntity());

       repository.saveAndFlush(study);

    }
    }

