package org.study.admin.Board;

import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.study.commons.constants.board.AfterWriteTarget;
import org.study.commons.constants.board.SkinType;
import org.study.commons.constants.board.ViewType;
import org.study.controllers.admin.board.BoardConfig;
import org.study.repositories.board.BoardRepository;

import static org.junit.jupiter.api.Assertions.*;

/**
 * '게시판 관리 - 게시판 등록'에 해당하는 테스트 클래스 입니다.
 * 파일명 : "BoardConfigService"
 * 메서드 : config()
 *
 */
@SpringBootTest
public class BoardConfigTest {

    private BoardConfig boardConfig;

    @Autowired
    private BoardRepository repository;

    @Autowired
    private BoardConfigService service;

    @BeforeEach
    BoardConfig config() {

        boardConfig = new BoardConfig();
        boardConfig.setMode("create");
        boardConfig.setBId("bId");
        boardConfig.setBoardNm("게시판명");
        boardConfig.setUse(true);
        boardConfig.setRowsPerPage(10L);
        boardConfig.setUseViewList(true);
        boardConfig.setCategory("");
        boardConfig.setViewType(ViewType.ADMIN.toString());
        boardConfig.setUseEditor(true);
        boardConfig.setUseFileAttach(null);
        boardConfig.setUseImageAttach(null);
        boardConfig.setAfterWriteTarget(AfterWriteTarget.VIEW.toString());
        boardConfig.setUseComment(true);
        boardConfig.setSkin(SkinType.DEFAULT.toString());
        boardConfig.setReview(true);

        return boardConfig;
    }

    @Test
    @DisplayName("예외 없이 게시판이 성공적으로 등록 (최종목적) ")
    void configSuccess(){
        assertDoesNotThrow(()->{
            service.config(boardConfig);
        });


    }

    @Test
    @DisplayName("BoardConfig Null값일때 예외메세지발생")
    void boardConfig_Null_Exception(){
        assertTh
    }

    /** 유효성 검사 S */

    @Test
    @DisplayName("필수 입력 값 체크 -예외메세지발생")
    void boardConfig_Essential(){

    }


}
