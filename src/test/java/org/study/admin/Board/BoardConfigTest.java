package org.study.admin.Board;

import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
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
@TestPropertySource(locations="classpath:application-test.properties")
public class BoardConfigTest {

    @Autowired
    private BoardConfig boardConfig;

    @Autowired
    private BoardRepository repository;

    @Autowired
    private BoardConfigService service;

    @BeforeEach
    void config() {
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
    }

    @Test
    @DisplayName("예외 없이 게시판이 성공적으로 등록 ")
    void configSuccess(){
        assertDoesNotThrow(()->{
            service.config(boardConfig);
        });
    }

    @Test
    @DisplayName("Config Null값일때 BoardConfigFailException 발생")
    void boardConfig_Null_Exception(){
        BoardConfig.builder().bId(null);

//        assertNotThrows(()->)
    }

    @Test
    @DisplayName("필수 입력 값 체크 - BoardConfigFailException 발생")
    void boardConfig_Essential(){

    }


}
