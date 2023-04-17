package org.study.controllers.admin.report;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/cs")
public class ReportController {

    /**
     * <신고 관리> 클릭시 나오는 페이지
     * == 신고 목록
     *
     * @return
     */
    @GetMapping
    public String index(){
        return "admin/cs/index";
    }


    /**
     * 신고 사유 관리
     *
     * @return
     */
    @GetMapping("/reason")
    public String reason(){
        return "admin/cs/reason";
    }

    /**
     * 문의 관리
     *
     * @return
     */
    @GetMapping("/qna")
    public String qna(){
        return "admin/cs/qna";
    }


    /**
     * 문의 상세보기
     *
     * @return
     */
    @GetMapping("/view")
    public String view(){
        return "admin/cs/view";
    }

}
