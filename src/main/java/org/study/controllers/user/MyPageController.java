package org.study.controllers.user;

import jakarta.validation.Valid;
import org.aspectj.weaver.MemberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.commons.UserUtils;
import org.study.controllers.admin.cs.CsConfig;
import org.study.controllers.admin.cs.QuestionConfig;
import org.study.controllers.admin.study.StudyConfig;
import org.study.controllers.user.user.UserJoin;
import org.study.entities.Question;
import org.study.entities.User;
import org.study.models.cs.*;
import org.study.models.user.UserInfo;

/*
회원정보 수정 (/user/mypage/edit/{userId})
        - 스터디 관리 (/user/mypage/mystudy/{studyCode})
        - 문의내역 (/user/mypage/qna)
            - 문의내역 상세(/user/mypage/qna/{qnaNo})
*/

@Controller
@RequestMapping("/user/mypage")
public class MyPageController {

    @Autowired
    private QuestionValidator qsValidator;

    @Autowired
    private QuestionRegisterService service;

    @Autowired
    private CsRegisterService csService;

    @Autowired
    private UserUtils userUtils;


    /**
     * <마이페이지> 클릭하면 나오는 페이지
     * 회원 정보 수정
     * @return
     */

    @GetMapping("/edit")
    public String myPage(Model model) {
        UserJoin userJoin = new UserJoin();
        model.addAttribute("userJoin",userJoin);

        String userNm = userUtils.getUser().getUserNm();
        model.addAttribute("userNm", userNm);

        String userEmail = userUtils.getUser().getUserEmail();
        model.addAttribute("userEmail", userEmail);


        return "front/mypage/edit";
    }

    /**
     * <스터디 관리> 클릭하면 나오는 페이지
     * @return
     */
    @GetMapping("/mystudy")
    public String myStudy() {
        return "front/mypage/mystudy";
    }

    /**
     * <문의 관리> 클릭하면 나오는 페이지
     * @return
     */
    @GetMapping("/qna")
    public String qna(Model model) {

        QuestionConfig qsCon = new QuestionConfig();


        qsCon.setUser(userUtils.getEntity());

        System.out.println("제목 = " + qsCon.getSubject());

        model.addAttribute("qsCon", qsCon);

        return "front/mypage/qna";
    }

    @GetMapping("/register")
    public String register(Model model) {
        QuestionConfig qsConfig = new QuestionConfig();
        model.addAttribute("qsConfig",qsConfig);
        return "front/mypage/register";
    }

    @GetMapping("/report_register")
    public String rpRegister(Model model) {
        CsConfig csConfig = new CsConfig();
        model.addAttribute("csConfig",csConfig);
        return "front/mypage/report_register";
    }

    @PostMapping("/saves")
    public String save(@Valid CsConfig csConfig, Errors errors, Model model) {
        model.addAttribute("csConfig",csConfig);

        csService.register(csConfig, errors);
        if(errors.hasErrors()) {
            return "front/mypage/report_register";
        }

        return "redirect:/user/mypage/qna";
    }

    @PostMapping("/save")
    public String save(@Valid QuestionConfig qsConfig, Errors errors, Model model) {
        model.addAttribute("qsConfig",qsConfig);

        service.qsRegister(qsConfig, errors);
        if (errors.hasErrors()){
            return "front/mypage/register";
        }

        return "redirect:/user/mypage/qna";

//        try {
//            service.qsRegister(qsConfig, errors);
//            if (errors.hasErrors()){
//                return "front/mypage/register";
//            }
//        } catch (DuplicateQsCodeException e) {
//            errors.rejectValue("qsCode", "Duplicate.question.qsCode");
//        }
//
//        service.qsRegister(qsConfig);
    }
}
