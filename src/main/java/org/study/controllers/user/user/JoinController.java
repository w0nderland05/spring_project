package org.study.controllers.user.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.study.models.user.UserJoinService;

// 사용자 관련 컨트롤러
@Controller
@RequestMapping("/user/join")
@RequiredArgsConstructor
public class JoinController {

    // 회원 가입 추가 유효성 검사
    private final UserJoinValidator joinValidator;

    // 회원 정보 저장 서비스(가입, 수정)
    private final UserJoinService service;

    // 이메일 인증 코드 보내기 관련 서비스
    //private final EmailService emailService;

    // 회원가입 양식 - GET /user
    @GetMapping
    public String join(@ModelAttribute  UserJoin userJoin, Model model) {
        commonProcess(model);

        // 달력 부분 1900 년도까지 minDate 설정
        model.addAttribute("minDate", "1900-01-01");

        return "front/user/join";
    }

    // 회원가입 처리 - POST /user
    @PostMapping
    public String joinPs(@Valid UserJoin userJoin, Errors errors, Model model) {
        commonProcess(model);

        joinValidator.validate(userJoin,errors);

        if (errors.hasErrors()) {
            return "front/user/join";
        }

        service.join(userJoin);

        return "redirect:/user/login"; // 회원가입 성공시 -> 로그인 페이지 이동
    }
    /**
    // 이메일 인증 처리
    @PostMapping("/email")
    public String emailAuthentication(@ModelAttribute UserJoin userJoin, Model model) {
        // 이메일 전송
        String authCode = null;
        try {
            authCode = emailService.sendSimpleMessage(userJoin.getUserEmail());
            model.addAttribute("authCode", authCode);
            return "front/user/join";
        } catch (Exception e) {
            // 이메일 보내기에 실패한 경우
            model.addAttribute("errorMessage", "이메일 보내기에 실패했습니다.");
            return "front/user/join";
        }
    }
    */

    // 회원정보 수정 - GET /user/사용자 ID
    @GetMapping("/{userId}/edit")
    public String edit(@PathVariable String userId) {

        return "user/edit";
    }

    // 회원정보 수정 PATCH /user
    @PatchMapping
    public String editPs() {

        return "redirect:/mypage"; // 회원정보 수정 후 마이페이지 이동
    }

    private void commonProcess(Model model) {
        model.addAttribute("addCss", new String[] { "user/style" });
        model.addAttribute("pageTitle", "회원가입");
    }
}
