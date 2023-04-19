package org.study.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class studyRegController {

    @GetMapping ("user/study/register")
    public String studyReg(){
        return "/front/study/register";
    }

}
