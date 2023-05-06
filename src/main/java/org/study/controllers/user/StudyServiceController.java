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
import org.study.models.study.StudyApplyService;

@Controller
@RequestMapping("user/study")
@RequiredArgsConstructor
public class StudyServiceController {

    private final StudyApplyService service;

    @GetMapping("/register")
    public String studyReg(Model model) {
        StudyConfig studyConfig = new StudyConfig();
        model.addAttribute("studyConfig", studyConfig);
        model.addAttribute("sidoList", Areas.sido);
        return "/front/study/register";
    }

    @GetMapping("/join")
    public String studyJoin(Model model, StudyConfig studyConfig) {
        model.addAttribute("studyConfig", studyConfig);
        model.addAttribute("sidoList", Areas.sido);
        String addressDo = studyConfig.getAddressDo();
        if (addressDo != null) {
            String[] siguguns = Areas.getSigugun(addressDo);
            model.addAttribute("siguguns", siguguns);
        }

        return "/front/study/join";
    }

    @PostMapping("/save")
    public String studySave(@Valid StudyConfig studyConfig, Errors errors, Model model) {

        model.addAttribute("sidoList", Areas.sido);
        String addressDo = studyConfig.getAddressDo();
        if (addressDo != null) {
            String[] siguguns = Areas.getSigugun(addressDo);
            model.addAttribute("siguguns", siguguns);
        }
        try {
            service.apply(studyConfig);
        } catch (RuntimeException e) {
            errors.reject("studySaveError", e.getMessage());
        }
        
        String mode = studyConfig.getMode();

        if (errors.hasErrors()) {
            System.out.println(errors);
            String tpl = "front/study/";
            if (mode != null && mode.equals("update")) {
                tpl += "update";
            } else {
                tpl += "register";
            }
            return tpl;
        }



        return "redirect:/user/study/join";// 게시판 등록 후 스터디함께해요 페이지로 이동
    }
}


