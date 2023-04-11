package org.study.controllers.admins;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/study")
public class StudyController {

    @GetMapping
    public String studyList(){
        return "/admin/study/studylist";
    }


}
