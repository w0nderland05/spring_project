package org.study.controllers.users;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

// 사용자 관련 컨트롤러
@Configuration
@RequestMapping("/user")
public class UserController {

    // 회원가입 양식 - GET /user
    @GetMapping
    public String join() {

        return "front/user/join";
    }

    // 회원가입 처리 - POST /user
    @PostMapping
    public String joinPs() {

        return "redirect:/user/login";
    }

    // 회원정보 수정 - GET /user/사용자 ID
    @GetMapping("/{userId}")
    public String edit(@PathVariable String userId) {
        return "front/user/edit";
    }

    // 회원정보 수정 PATCH /user
    @PatchMapping
    public String editPs() {
        return "redirect:/mypage"; // 회원정보 수정 후 마이페이지 이동
    }

    // 로그인 양식 - GET /user/login
    @GetMapping("/login")
    public String login() {
        return "front/user/login";
    }
}
