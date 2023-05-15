package org.study.controllers.admin.board;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.study.commons.validators.CommonException;
import org.study.models.board.BoardSaveService;
import org.study.models.board.BoardInfoService;
import org.study.models.board.BoardListService;

import java.util.List;

@Controller
@RequestMapping("/admin/board")
public class BoardController {

    @Autowired
    private BoardSaveService service;

    @Autowired
    private BoardInfoService infoService;

    @Autowired
    private BoardListService listService;

    /**
     * <게시판관리> 클릭하면 나오는 페이지
     * == 게시판목록
     *
     * @return
     */
    @GetMapping
    public String index(Model model) {
        List<BoardConfig> configs = listService.gets();
        model.addAttribute("configs", configs);

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
    @PostMapping("/save")
    public String save(@Valid BoardConfig boardConfig, Errors errors) {
        
        String mode = boardConfig.getMode();
        if (errors.hasErrors()) {
            String tpl = mode == null ? "register" : "update";
            return "admin/board/" + tpl;
        }
        service.config(boardConfig);

        return "redirect:/admin/board"; // 게시판 등록&수정 후 목록으로 이동
    }

    /**
     * 게시판 상세 -> 게시판 수정 & 삭제 가능
     * @return
     */
    @GetMapping("/update/{bId}")
    public String update(@PathVariable String bId, Model model, HttpServletResponse response) {
        model.addAttribute("mode", "update");
        try {
            BoardConfig boardConfig = infoService.get(bId);
            model.addAttribute("boardConfig", boardConfig);
        } catch (CommonException e) {
            response.setStatus(e.getStatus().value());
            model.addAttribute("script", "alert('" + e.getMessage() + "');history.back();");
            return "commons/execute_script";
        }
        return "admin/board/update";
    }
}
