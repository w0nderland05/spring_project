package org.study.controllers.user;

import jakarta.validation.Valid;
import org.aspectj.weaver.MemberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.commons.UserUtils;
import org.study.controllers.admin.cs.QuestionConfig;
import org.study.controllers.user.user.UserJoin;
import org.study.models.cs.DuplicateQsCodeException;
import org.study.models.cs.QuestionListService;
import org.study.models.cs.QuestionRegisterService;
import org.study.models.cs.QuestionValidator;

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
    private QuestionListService listService;

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
        model.addAttribute("myNm", userNm);


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
    public String qna() {
        return "front/mypage/qna";
    }

    @GetMapping("/register")
    public String register(Model model) {
        QuestionConfig qsConfig = new QuestionConfig();
        model.addAttribute("qsConfig",qsConfig);
        return "front/mypage/register";
    }

    @PostMapping("/save")
    public String save(/*@Valid */QuestionConfig qsConfig, Errors errors, Model model) {
        System.out.println("save post????");
//        Long qsCode = qsConfig.getQsCode();
        model.addAttribute("qsConfig",qsConfig);

        System.out.println("qsConcf = " + qsConfig);

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
