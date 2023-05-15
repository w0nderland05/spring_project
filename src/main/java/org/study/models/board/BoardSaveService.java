package org.study.models.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.study.commons.UserUtils;
import org.study.commons.constants.board.AfterWriteTarget;
import org.study.commons.constants.board.SkinType;
import org.study.commons.constants.board.ViewType;
import org.study.controllers.admin.board.BoardConfig;
import org.study.entities.board.Board;
import org.study.repositories.board.BoardRepository;

import java.time.LocalDateTime;

@Service
public class BoardSaveService {
    @Autowired
    private BoardRepository repository;
    @Autowired
    private BoardSaveValidator validator;
    @Autowired
    private UserUtils userUtils;

    public void save(BoardConfig config) {
        save(config, null);
    }

    public void save(BoardConfig boardConfig, Errors errors) {
        if (errors != null && errors.hasErrors()) {
            return;
        }

        validator.check(boardConfig, errors);

        /**
         * mode가 null이 아니고, update고, 중복 bId일 경우 (수정)
         * 아니라면 (생성)
         */

        Board board = null;
        String bId = boardConfig.getBId(); // 게시판 아이디
        String mode = boardConfig.getMode();
        if(mode != null && mode.equals("update") && repository.exists(bId)) { // mode값이 수정, 중복bId 경우 수정
            board = repository.findById(bId).orElse(null);
//                    orElseGet(() -> Board.builder().bId(bId).build());
        }

        if (board == null) {
            board = new Board();
            board.setBId(bId);
            // 회원 정보는 최초 등록시에만 저장하고 변경이 되지 않도록 처리
            board.setUser(userUtils.getEntity());
        }

        board.setCreatedAt(LocalDateTime.now());
        board.setBoardNm(boardConfig.getBoardNm());
        board.setUse(boardConfig.isUse());
        board.setRowsPerPage(boardConfig.getRowsPerPage());
        board.setUseViewList(boardConfig.isUseViewList());
        board.setCategory(boardConfig.getCategory());
        board.setViewType(ViewType.valueOf(boardConfig.getViewType()));
        board.setUseEditor(boardConfig.isUseEditor());
        /** 파일, 이미지는 추후 등록 */
        board.setAfterWriteTarget(AfterWriteTarget.valueOf(boardConfig.getAfterWriteTarget()));
        board.setUseComment(boardConfig.isUseComment());
        board.setSkin(SkinType.valueOf(boardConfig.getSkin()));
        board.setReview(boardConfig.isReview());

        /** category: '\n' 줄바꿈울 기준으로 인식 */
        String getCate = boardConfig.getCategory();
        String[] categoryArr = getCate.split("\n");

        for (String category : categoryArr) {
            board.setCategory(category);
        }

        repository.saveAndFlush(board);
    }
}
