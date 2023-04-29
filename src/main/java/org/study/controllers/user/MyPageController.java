package org.study.controllers.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.entities.User;

import java.security.Principal;

/*
회원정보 수정 (/user/mypage/edit/{userId})
        - 스터디 관리 (/user/mypage/mystudy/{studyCode})
        - 문의내역 (/user/mypage/qna)
            - 문의내역 상세(/user/mypage/qna/{qnaNo})
*/

@Controller
@RequestMapping("/user/mypage")
@RequiredArgsConstructor
public class MyPageController {

    private final User user;

    /**
     * <마이페이지> 클릭하면 나오는 페이지
     * 회원 정보 수정
     * @return
     */

    @GetMapping("/edit")
    public String myPage() {
        return "front/mypage/edit";
    }


    /*
    @GetMapping("/edit")
    public String myPage(@AuthenticationPrincipal Principal principal, Model model) {
        model.addAttribute("userNo", principal.getName());
        return "front/mypage/edit";
    }
    */

    /**
     * <스터디 관리> 클릭하면 나오는 페이지
     * @return
     */
    @GetMapping("/mystudy")
    public String myStudy() {
        return "front/mypage/mystudy";
    }

    /**
     * <문의 내역> 클릭하면 나오는 페이지
     * @return
     */
    @GetMapping("/qna")
    public String Qna() {
        return "front/mypage/qna";
    }
    
}
