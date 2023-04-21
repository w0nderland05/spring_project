package org.study.admin.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.study.commons.constants.board.AfterWriteTarget;
import org.study.commons.constants.board.SkinType;
import org.study.commons.constants.board.ViewType;
import org.study.controllers.admin.board.BoardConfig;
import org.study.entities.board.Board;
import org.study.repositories.board.BoardRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardConfigService {
    @Autowired
    private BoardRepository repository;

    public void config(BoardConfig config) {
        Board board = Board.builder()
                .bId(config.getBId())
                .boardNm(config.getBoardNm())
                .isUse(config.isUse())
                .rowsPerPage(config.getRowsPerPage())
                .useViewList(config.isUseViewList())
                .category(config.getCategory())
                .viewType(ViewType.ADMIN) // 관리자만
                .useEditor(config.isUseEditor())
//                .useFileAttach() 타입을 어떻게..?
//                .useImageAttach() 타입을 어떻게..?
                .afterWriteTarget(AfterWriteTarget.valueOf(config.getAfterWriteTarget()))
                .useComment(config.isUseComment())
//                .skin(SkinType.valueOf(config.getSkin())) 타입 해결해야함.
                .isReview(config.isReview())
                .build();

        repository.save(board);
    }
}
