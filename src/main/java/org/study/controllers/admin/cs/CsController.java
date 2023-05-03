package org.study.controllers.admin.cs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.entities.Report;
import org.study.models.cs.ReportListService;

import java.util.List;

@Controller
@RequestMapping("/admin/cs")
public class CsController {

    @Autowired
    private ReportListService listService;
    /**
     * <CS 관리> 클릭시 나오는 페이지
     * == 신고 목록
     *
     * @return
     */
    @GetMapping
    public String index(Model model){
        List<CsConfig> configs = listService.gets();
        model.addAttribute("configs",configs);

        return "admin/cs/index";
    }


    /**
     * 문의 목록
     *
     * @return
     */
    @GetMapping("/qna")
    public String qna(Model model){
        CsConfig cs

        return "admin/cs/qna";
    }

    /**
     * 문의 목록
     * 문의 상세보기
     *
     * @return
     */
    @GetMapping("/view")
    public String view(){
        return "admin/cs/view";
    }

}
