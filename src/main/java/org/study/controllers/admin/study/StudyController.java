package org.study.controllers.admin.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/study")
public class StudyController {

    /**
     * <스터디관리> 클릭시 나오는 페이지
     * ==스터디 목록
     *
     * @return
     */
    @GetMapping
    public String index(){
        return "admin/study/index";
    }

    /**
     * 스터디 개설 신청 관리
     *
     * @return
     */
    @GetMapping("/approvals")
    public String approvals(){
        return "admin/study/approvals";
    }

    /**
     * 카테고리 관리
     *
     * @return
     */
    @GetMapping("/category")
    public String category(){
        return "admin/study/category";
    }
    

}
