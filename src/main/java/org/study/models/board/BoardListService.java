package org.study.models.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.study.controllers.admin.board.BoardConfig;
import org.study.entities.board.Board;
import org.study.repositories.board.BoardRepository;

import java.util.List;

@Service
public class BoardListService {

    @Autowired
    private BoardRepository repository;

    public List<BoardConfig> gets() {
        List<Board> boards = repository.findAll(Sort.by(Sort.Order.desc("createdAt")));
        List<BoardConfig> configs = boards.stream().map(this::toConfig).toList();

        return configs;
    }

    private BoardConfig toConfig(Board board) {
        return BoardConfig.builder()
                .bId(board.getBId())
                .boardNm(board.getBoardNm())
                .isUse(board.isUse())
                .rowsPerPage(board.getRowsPerPage())
                .useViewList(board.isUseViewList())
                .category(board.getCategory())
                .viewType(board.getViewType().toString())
                .useEditor(board.isUseEditor())
                /** 파일첨부, 이미지첨부 추후 등록 */
                .afterWriteTarget(board.getAfterWriteTarget().toString())
                .useComment(board.isUseComment())
                .skin(board.getSkin().toString())
                .isReview(board.isReview())
                .build();
    }
}
