package org.study.controllers.admin.board;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.commons.validators.BadRequestException;
import org.study.commons.validators.CommonException;
import org.study.models.board.BoardConfigService;
import org.study.models.board.BoardInfoService;
import org.study.models.board.BoardListService;
import org.study.models.board.DuplicateCateBIdException;

import java.util.List;

@Controller
@RequestMapping("/admin/board")
public class BoardController {

    @Autowired
    private BoardConfigService service;

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
        try {
            // 게시판 저장 처리
            service.config(boardConfig, errors);
        } catch (DuplicateCateBIdException e) { // 중복된 bId 예외인 경우
            errors.rejectValue("bId", "Duplicate.boardConfig.bId");
        }

        String mode = boardConfig.getMode();
        if (errors.hasErrors()) {
            String tpl = "admin/board/";
            if (mode != null && mode.equals("update")) { // mode가 업데이트면
                tpl += "update"; // admin/board/update
            } else {
                tpl += "config"; // admin/board/config
            }
            return tpl;
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
