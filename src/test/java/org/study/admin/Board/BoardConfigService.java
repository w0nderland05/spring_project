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
                .viewType(ViewType.ADMIN) // 게시판은 관리자만 등록할 수 있게 설정
                .useEditor(config.isUseEditor())
               // .useFileAttach(config.getUseFileAttach()) //타입 추후 테스트
              //  .useImageAttach() //타입 추후 테스트

                // 체크한 값으로 선택 되도록 설정
                .afterWriteTarget(AfterWriteTarget.valueOf(config.getAfterWriteTarget()))
                .useComment(config.isUseComment())
                .skin(SkinType.DEFAULT) // 스킨은 기본 값으로 초기 설정
                .isReview(config.isReview())
                .build();

        repository.save(board);
    }
}
