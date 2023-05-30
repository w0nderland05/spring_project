package org.study.models.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.commons.validators.BoardNotFoundException;
import org.study.controllers.admin.board.BoardConfig;
import org.study.entities.board.Board;
import org.study.repositories.board.BoardRepository;

@Service
public class BoardInfoService { // 조회

    @Autowired
    private BoardRepository repository;

    public Board get(String bId) {
        Board board = repository.findById(bId).orElseThrow(BoardNotFoundException::new);
        return board;

//        BoardConfig config = BoardConfig.builder()
//                .bId(board.getBId())
//                .boardNm(board.getBoardNm())
//                .isUse(board.isUse())
//                .rowsPerPage(board.getRowsPerPage())
//                .useViewList(board.isUseViewList())
//                .category(board.getCategory())
//                .viewType(board.getViewType().toString())
//                .useEditor(board.isUseEditor())
//                /** 파일첨부, 이미지첨부 추후 등록 */
//                .afterWriteTarget(board.getAfterWriteTarget().toString())
//                .useComment(board.isUseComment())
//                .skin(board.getSkin().toString())
//                .isReview(board.isReview())
//                .build();
    }
}
