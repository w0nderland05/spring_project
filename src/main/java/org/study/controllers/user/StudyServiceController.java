package org.study.controllers.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.controllers.admin.study.StudyConfig;
import org.study.models.study.DuplicationStudyCdException;
import org.study.models.study.StudyApplyService;

@Controller
@RequestMapping("user/study")
@RequiredArgsConstructor
public class StudyServiceController {

    private final StudyApplyService service;

    @GetMapping("/register")
    public String studyReg() {
        return "/front/study/register";
    }

    @GetMapping("/join")
    public String studyJoin() {
        return "/front/study/join";
    }

    @PostMapping
    public String save(@Valid StudyConfig studyConfig, Errors errors){
       try{
           service.apply(studyConfig, errors);
       }catch(DuplicationStudyCdException e){
           errors.rejectValue("studyCode", "Duplicate.studyConfig.studyCode");

       }
       if(errors.hasErrors()){
           return"/user/study/register";
       }
       return"redirect:/user/study/join";
    }
}


