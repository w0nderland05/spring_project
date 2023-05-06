package org.study.admin.Board;

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
import org.study.models.board.BoardConfigService;
import org.study.models.board.BoardInfoService;
import org.study.repositories.board.BoardRepository;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class BoardInfoTest {

    private BoardConfig boardConfig;

    @Autowired
    private BoardRepository repository;

    @Autowired
    private BoardConfigService service;

    @Autowired
    private BoardInfoService infoService;

    @BeforeEach
    void insert() {
        BoardConfig boardConfig = BoardConfig.builder()
                .bId("study")
                .boardNm("스터디")
                .isUse(true)
                .rowsPerPage(10L)
                .useViewList(true)
                .category("수능\n공무원\n")
                .viewType(ViewType.USER.toString())
                .useEditor(true)
                .afterWriteTarget(AfterWriteTarget.VIEW.toString())
                .useComment(true)
                .skin(SkinType.DEFAULT.toString())
                .isReview(true)
                .build();
        service.config(boardConfig);

        BoardConfig boardConfig2 = BoardConfig.builder()
                .bId("community")
                .boardNm("커뮤니티")
                .isUse(true)
                .rowsPerPage(20L)
                .useViewList(true)
                .category("자유게시판\n질문과답변\n")
                .viewType(ViewType.USER.toString())
                .useEditor(true)
                .afterWriteTarget(AfterWriteTarget.VIEW.toString())
                .useComment(true)
                .skin(SkinType.DEFAULT.toString())
                .isReview(true)
                .build();
        service.config(boardConfig2);
    }

    /**
     *  게시물 목록을 조회할 때 필요한 테스트입니다.
     *  클래스 : BoardInfoService
     *  메서드 : get()
     */
    @Test
    @DisplayName("개별 조회 - BId를 통해 게시글을 가져 올 수 있다. ")
    void get() {
        BoardConfig config = infoService.get("community");
        System.out.println(config);

    }
}
