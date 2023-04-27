package org.study.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/study")
public class StudyServiceController {

    @GetMapping("/register")
    public String studyReg() {
        return "/front/study/register";
    }

    @GetMapping("/join")
    public String studyJoin() {
        return "/front/study/join";
    }
}
