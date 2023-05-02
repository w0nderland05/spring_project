package org.study.controllers.admin.board;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.models.board.BoardConfigService;
import org.study.models.board.DuplicateCateBIdException;

@Controller
@RequestMapping("/admin/board")
public class BoardController {

    @Autowired
    private BoardConfigService service;

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
    public String register(Model model){
        BoardConfig boardConfig = new BoardConfig();
        model.addAttribute("boardConfig", boardConfig);

        return "admin/board/config";
    }

    /**
     * 게시판 목록 ->//(페이지이동)//-> 수정하기 버튼 => 수정 & 등록완료 => 목록페이지 이동
     *: 코드 302 로 페이지 임시이동 예정
     *
     * @param boardConfig
     * @param errors
     *
     * @return
     */
    @PostMapping
    public String save(@Valid BoardConfig boardConfig, Errors errors) {
        try {
            // 게시판 저장 처리
            service.config(boardConfig, errors);
        } catch (DuplicateCateBIdException e) { // 중복된 분류 예외인 경우
            errors.rejectValue("bId", "Duplicate.boardConfig.bId");
        }
        if (errors.hasErrors()) {
            return "admin/board/config";
        }

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
