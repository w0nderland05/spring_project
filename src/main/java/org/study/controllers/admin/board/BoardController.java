package org.study.controllers.admin.board;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/board")
public class BoardController {

    /**
     * <게시판관리> 클릭하면 나오는 페이지
     * == 게시판목록
     *
     * @return
     */
    @GetMapping
    public String index(){
        return "admin/board/index";
    }

    /**
     * 게시판 등록  (세부목록 클릭시 )
     *
     * @return
     */
    @GetMapping("/config")
    public String register(){
        return "admin/board/config";
    }

    /**
     * 게시판 목록 ->//(페이지이동)//-> 수정하기 버튼 => 수정 & 등록완료 => 목록페이지 이동
     *: 코드 302 로 페이지 임시이동 예정
     *
     * @return
     */
    @PostMapping("/save")
    public String save(){
        return "redirect:/admin/board";
    }

    /**
     * 게시글 상세 -> 게시글 수정 & 삭제 가능
     * @return
     */
    @GetMapping("/update/{code}")
    public String update(@PathVariable Long code){
        return "admin/board/update";
    }

}
