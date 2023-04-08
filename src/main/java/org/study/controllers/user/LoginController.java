package org.study.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/login")
public class LoginController {

    // 로그인 양식 - GET /user/login
    @GetMapping("/login")
    public String login(String success, Model model) {
        UserLogin userLogin = new UserLogin();
        userLogin.setSuccess(success);

        model.addAttribute("userLogin", userLogin);

        return "user/login";
    }

}
