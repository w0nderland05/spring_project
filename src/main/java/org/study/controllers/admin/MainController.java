package org.study.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class MainController {

    @GetMapping
    public String main(){
<<<<<<< HEAD
        return "/admin/main";
=======

        return "/admin/layouts/main";
>>>>>>> member
    }
}
