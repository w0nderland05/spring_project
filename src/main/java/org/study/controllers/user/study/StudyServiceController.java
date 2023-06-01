package org.study.controllers.user.study;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.commons.Areas;
import org.study.commons.UserUtils;
import org.study.controllers.admin.study.StudyConfig;

//@Controller
@RequestMapping("user/study")
@RequiredArgsConstructor
public class StudyServiceController {

    private final UserUtils userUtils;

    @GetMapping("/register")
    public String studyReg(Model model) {
        if (!userUtils.isLogin()) {
            return "/user/login";
        }
        StudyConfig studyConfig = new StudyConfig();
        studyConfig.setUser(userUtils.getEntity());
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

        if (!userUtils.isLogin()) {
            errors.reject("loginRequriedService");
        }
        model.addAttribute("sidoList", Areas.sido);
        String addressDo = studyConfig.getAddressDo();
        if (addressDo != null) {
            String[] siguguns = Areas.getSigugun(addressDo);
            model.addAttribute("siguguns", siguguns);
        }
        try {
            //service.apply(studyConfig);
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


