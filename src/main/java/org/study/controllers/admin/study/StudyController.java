package org.study.controllers.admin.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/study")
public class StudyController {

    @GetMapping
    public String index() {
        
        return "admin/study2/index";
    }

}
