package org.study.models.board;

import org.aspectj.weaver.MemberUtils;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Service
public class BoardConfigService {
    @Autowired
    private BoardRepository repository;
    @Autowired
    private BoardConfigValidator validator;
//    @Autowired
//    private MemberUtils memberUtils;

    public void config(BoardConfig config) {
        config(config, null);
    }

    public void config(BoardConfig config, Errors errors) {
        if (errors != null && errors.hasErrors()) {
            return;
        }

        validator.check(config, errors);

        /**
         * mode가 null이 아니고, update고, 중복 bId일 경우 (수정)
         * 아니라면 (생성)
         */

        Board entity = null;
        String bId = config.getBId(); // 게시판 아이디
        String mode = config.getMode();
        if(mode != null && mode.equals("update") && repository.exists(bId)) { // mode값이 수정, 중복bId 경우 수정
            entity = repository.findById(bId).orElseGet(() -> Board.builder().bId(bId).build());
        } else { // 모드가 수정이 아니면 새로 추가 ( 비 영속 상태 )
            entity = new Board();
            entity.setBId(bId);
        }
        entity.getCreatedAt();
        entity.setBoardNm(config.getBoardNm());
        entity.setUse(config.isUse());
        entity.setRowsPerPage(config.getRowsPerPage());
        entity.setUseViewList(config.isUseViewList());
        entity.setCategory(config.getCategory());
        entity.setViewType(ViewType.valueOf(config.getViewType()));
        entity.setUseEditor(config.isUseEditor());
        /** 파일, 이미지는 추후 등록 */
        entity.setAfterWriteTarget(AfterWriteTarget.valueOf(config.getAfterWriteTarget()));
        entity.setUseComment(config.isUseComment());
        entity.setSkin(SkinType.valueOf(config.getSkin()));
        entity.setReview(config.isReview());

        /** category: '\n' 줄바꿈울 기준으로 인식 */
        String getCate = config.getCategory();
        String[] categoryArr = getCate.split("\n");

        for (String category : categoryArr) {
            entity.setCategory(category);
        }

        repository.saveAndFlush(entity);
    }
}
