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




}
