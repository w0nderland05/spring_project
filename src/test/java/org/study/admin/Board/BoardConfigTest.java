package org.study.admin.Board;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.study.commons.constants.board.AfterWriteTarget;
import org.study.commons.constants.board.SkinType;
import org.study.commons.constants.board.ViewType;
import org.study.commons.validators.BadRequestException;
import org.study.controllers.admin.board.BoardConfig;
import org.study.entities.board.Board;
import org.study.repositories.board.BoardRepository;

import java.util.Scanner;

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
//@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
public class BoardConfigTest {

//    @Autowired
//    private MockMvc mockMvc;

    private BoardConfig boardConfig;

    @Autowired
    private BoardRepository repository;

    @Autowired
    private BoardConfigService service;

    @BeforeEach
    void config() {
        // 테스트 양식 데이터 추가
        boardConfig = BoardConfig.builder()
                .bId("QnA")
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
    @DisplayName("BoardConfig- 전체 Null값일때 예외메세지발생")
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
        // 발생 경우 수
        // bId , boardNm, rowsPerPage, skin 에 대한 필수 입력 값 체크
        // null, isBlank

        /** 빈값 체크 S */
        String[] fields = {"bId", "boardNm" /* "rowsPerPage" */, "skin"};
        String bId = boardConfig.getBId();
        String boardNm = boardConfig.getBoardNm();
        String skin = boardConfig.getSkin();

        for (String field : fields) {
            String includedWord = null;

            if (field.equals("bId")) {
                boardConfig.setBId("   ");
                boardConfig.setBoardNm(boardNm);
                boardConfig.setSkin(skin);
                includedWord = "게시판 아이디";

            } else if (field.equals("boardNm")) {
                boardConfig.setBId(bId);
                boardConfig.setBoardNm("   ");
                boardConfig.setSkin(skin);
                includedWord = "게시판 이름";

            } else if (field.equals("skin")) {
                boardConfig.setBId(bId);
                boardConfig.setBoardNm(boardNm);
                boardConfig.setSkin("   ");
                includedWord = "스킨";
            }
            BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
                service.config(boardConfig);
            });
            System.out.println(field);
            System.out.println(thrown.getMessage());

            // 예외 메세지에 핵심 키워드가 포함되어 있는지 체크
            assertTrue(thrown.getMessage().contains(includedWord));

            // 상태 코드 검증(HttpStatus.BAD_REQUEST)
            assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatus());

        }
        /** 빈값 체크 E */

        /** NULL 체크 S */
        String[] fields2 = {"bId", "boardNm", "rowsPerPage", "skin"};
        Long rowsPerPage = boardConfig.getRowsPerPage();

        for (String field : fields2) {
            String includedWord = null;
            if (field.equals("bId")) {
                boardConfig.setBId(null);
                boardConfig.setBoardNm(boardNm);
                boardConfig.setRowsPerPage(rowsPerPage);
                boardConfig.setSkin(skin);
                includedWord = "게시판 아이디";

            } else if (field.equals("boardNm")) {
                boardConfig.setBId(bId);
                boardConfig.setBoardNm(null);
                boardConfig.setRowsPerPage(rowsPerPage);
                boardConfig.setSkin(skin);
                includedWord = "게시판 이름";

            } else if (field.equals("rowsPerPage")) {
                boardConfig.setBId(bId);
                boardConfig.setBoardNm(boardNm);
                boardConfig.setRowsPerPage(null);
                boardConfig.setSkin(skin);
                includedWord = "페이지 당 게시글 수";

            } else if (field.equals("skin")) {
                boardConfig.setBId(bId);
                boardConfig.setBoardNm(boardNm);
                boardConfig.setRowsPerPage(rowsPerPage);
                boardConfig.setSkin(null);
                includedWord = "스킨";
            }

            BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
                service.config(boardConfig);
            });
            System.out.println(field);
            System.out.println(thrown.getMessage());

            // 예외 메세지에 핵심 키워드가 포함되어 있는지 체크
            assertTrue(thrown.getMessage().contains(includedWord));
            // 상태 코드 검증(HttpStatus.BAD_REQUEST)
            assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatus());
        }
        /** NULL 체크 E */
    }

    //validator Test
    /**
     * 유효성 검사 추가 (오류메세지가 제대로 나오는지에 대한 체크는 통합테스트에서 진행)
     * 1. 게시판 아이디가 중복되는지 체크
     * 2. rowsPerPage : 최소 10개 부터 되는지 체크
     * 3. category: '\n' 줄바꿈울 기준으로 인식 되는지 
     */

    /** 1. 게시판 아이디가 중복되는지 체크 */
    @Test
    @DisplayName("bId 중복 등록 시 DuplicateCateBIdException 발생 여부")
    void DuplicateCateBIdTest() {
        // 테스트 전 분류 등록
        service.config(boardConfig);

        assertThrows(DuplicateCateBIdException.class, () -> {
           // 중복 분류로 등록
           service.config(boardConfig);
        });
    }

    /** 2. rowsPerPage : 최소 10부터 되는지 체크 */
    @Test
    @DisplayName("rowsPerPage : 최소 10부터 되는지 체크")
    void rowsPerPageMinTest() {
        assertThrows(BadRequestException.class, () -> {
            boardConfig.setRowsPerPage(9L);
            service.config(boardConfig);
        });
        System.out.println(boardConfig);
    }

    /** 3. category: '\n' 줄바꿈울 기준으로 인식 되는지 */
    @Test
    @DisplayName("category: '\n' 줄바꿈울 기준으로 인식 되는지")
    void categoryEnterTest() {
        // textarea, split
        boardConfig.setCategory("분류1\n분류2\n분류3\n");
        service.config(boardConfig);
        System.out.println(boardConfig);
    }

    /** mode가 update면 수정인지 체크 */
    @Test
    @DisplayName("mode가 update면 수정인지 체크")
    void modeUpdateTest() {
        boardConfig.setMode("update");
        service.config(boardConfig);
        System.out.println(boardConfig);
    }
}
