package org.study.models.study;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.study.commons.constants.Status;
import org.study.controllers.admin.study.StudyConfig;
import org.study.entities.Study;
import org.study.repositories.StudyRepository;

@Service
@RequiredArgsConstructor
public class StudyApplyService {


    private final StudyRepository repository;

    private final StudyRegisterValidator validator;


    public void apply(StudyConfig config){
        apply(config, null);
    }


    public void apply(StudyConfig config, Errors errors) {
        if(errors !=null && errors.hasErrors()){
            return;
        }
        validator.check(config,errors);

       Long studyCd = config.getStudyCode();
       Study study = null;
       if(repository.exists(studyCd)){

           study= repository.findById(studyCd).orElseGet(() ->StudyConfig.of(config));
           study.setStudyCode(studyCd);
           study.setStudyNm(config.getStudyNm());
           study.setCategory(config.getCategory());
           study.setMaxMember(config.getMaxMember());
           study.setNumOfWeek(config.getNumOfWeek());
           study.setRegionType(config.getRegionType());
           study.setSimpleIntro(config.getSimpleIntro());
           study.setIntroduction(config.getIntroduction());

       }
       if(study == null){
           study = StudyConfig.of(config);
       }
       repository.saveAndFlush(study);

    }
    }

