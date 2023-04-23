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

    @Autowired
    private BoardConfigValidator validator;

    public void config(BoardConfig config) {
        validator.check(config);

        String bId = config.getBId();
        Board board1 = null;
        if(repository.exists(bId)) { // 이미 등록된 게시판 ID가 있다면
            board1 = repository.findById(bId).orElseGet(() -> BoardConfig.of(config));
            board1.setBId(bId);
            board1.setBoardNm(config.getBoardNm());
            board1.setRowsPerPage(config.getRowsPerPage());
            board1.setSkin(SkinType.DEFAULT);
            /** 기본 값으로 설정이 맞는지?? */
        }

        if(board1 == null) { // 게시판 ID가 없다면 boardConfig -> Board 엔티티로 변환
            board1 = BoardConfig.of(config);
        }

        repository.saveAndFlush(board1);
    }
}
