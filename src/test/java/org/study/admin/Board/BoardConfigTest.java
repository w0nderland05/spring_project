package org.study.admin.Board;

import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.study.commons.constants.board.AfterWriteTarget;
import org.study.commons.constants.board.SkinType;
import org.study.commons.constants.board.ViewType;
import org.study.commons.validators.BadRequestException;
import org.study.controllers.admin.board.BoardConfig;
import org.study.repositories.board.BoardRepository;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * '게시판 관리 - 게시판 등록'에 해당하는 테스트 클래스 입니다.
 * 파일명 : "BoardConfigService"
 * 메서드 : config()
 *
 */
@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
public class BoardConfigTest {

    private BoardConfig boardConfig;

    @Autowired
    private BoardRepository repository;

    @Autowired
    private BoardConfigService service;

    @BeforeEach
    void config() {
        boardConfig = BoardConfig.builder()
                .bId("create1")
                .boardNm("게시판1")
                .isUse(true)
                .rowsPerPage(10L)
                .useViewList(true)
                .category("QnA Notice")
                .viewType(ViewType.USER.toString())
                .useEditor(true)
                .afterWriteTarget(AfterWriteTarget.VIEW.toString())
                .useComment(true)
                .skin(SkinType.DEFAULT.toString())
                .isReview(true)
                .build();
    }

    @Test
    @DisplayName("예외 없이 게시판이 성공적으로 등록 (최종목적) ")
    void configSuccess(){
        assertDoesNotThrow(()->{
            service.config(boardConfig);
        });
        System.out.println(boardConfig);
    }

    @Test
    @DisplayName("BoardConfig- 필수입력값 Null값일때 예외메세지발생")
    void boardConfig_Null_Exception(){
        BadRequestException thrown = assertThrows(BadRequestException.class,() -> {
           service.config(null);
        });

        /** "잘못된 접근입니다." 문구 포함여부 체크 */
        assertTrue(thrown.getMessage().contains("잘못된 접근"));

        /** HttpStatus가 400 - BadRequest로 설정되어 있는지 체크 */
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatus());
    }

    /** 유효성 검사 S */

    @Test
    @DisplayName("필수 입력 값 체크 -예외메세지발생")
    void boardConfig_Essential(){

    }


}
