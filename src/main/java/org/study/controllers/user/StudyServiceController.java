package org.study.controllers.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.commons.Areas;
import org.study.controllers.admin.study.StudyConfig;
import org.study.models.study.DuplicationStudyCdException;
import org.study.models.study.StudyApplyService;

@Controller
@RequestMapping("user/study")
@RequiredArgsConstructor
public class StudyServiceController {

    private final StudyApplyService service;

    @GetMapping("/register")
    public String studyReg(Model model, StudyConfig studyConfig) {
        model.addAttribute("studyConfig", studyConfig);
        model.addAttribute("sidoList", Areas.sido);
        return "/front/study/register";
    }

    @GetMapping("/join")
    public String studyJoin(Model model, StudyConfig studyConfig) {
        model.addAttribute("studyConfig", studyConfig);
        model.addAttribute("sidoList", Areas.sido);
        return "/front/study/join";
    }

    @PostMapping("/save")
    public String save(@Valid StudyConfig studyConfig, Errors errors, Model model){

        model.addAttribute("sidoList", Areas.sido);
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


