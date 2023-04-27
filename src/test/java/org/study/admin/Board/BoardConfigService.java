package org.study.admin.Board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.study.commons.constants.board.AfterWriteTarget;
import org.study.commons.constants.board.SkinType;
import org.study.commons.constants.board.ViewType;
import org.study.controllers.admin.board.BoardConfig;
import org.study.controllers.admin.category.CategoryForm;
import org.study.entities.board.Board;
import org.study.repositories.board.BoardRepository;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class BoardConfigService {
    @Autowired
    private BoardRepository repository;

    @Autowired
    private BoardConfigValidator validator;

    public void config(BoardConfig config) {
        config(config, null);
    }

    public void config(BoardConfig config, Errors errors) {
        if (errors != null && errors.hasErrors()) {
            return;
        }

        validator.check(config, errors);

        /**
         * 엔티티가 이미 등록되어 있으면 기존 엔티티 가져오고(수정)
         * 없다면 새로운 엔티티로 변환 BoardConfig.of(config);(생성)
         *
         */
        String bId = config.getBId();
        Board board = null;
        if(bId != null && repository.exists(bId)) { // 이미 등록된 게시판 ID가 있다면
            board = repository.findById(bId).orElseGet(() -> BoardConfig.of(config));
            board.setBId(bId);
            board.setBoardNm(config.getBoardNm());
            board.setRowsPerPage(config.getRowsPerPage());
            board.setSkin(SkinType.DEFAULT);
            /** 기본 값으로 설정이 맞는지?? */
        }

        if(board == null) { // 게시판 ID가 없다면 boardConfig -> Board 엔티티로 변환
            board = BoardConfig.of(config);
        }

        /** category: '\n' 줄바꿈울 기준으로 인식 */

        repository.saveAndFlush(board);
    }
}
