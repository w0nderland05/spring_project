package org.study.admin.Board;

import org.hibernate.annotations.DialectOverride;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BoardListTest {



    /**
     * 클래스 : BoardListService
     * 메서드 : gets()
     */

    @Test
    @DisplayName("최종목적 - 전체 게시판 리스트를 조회 성공  ")
    void gets(){

    }

    /**
     * 특정 카테고리를 선택한 후, 해당 카테고리에만 해당되는 게시물 목록이 조회되는지 테스트하는 메서드입니다.
     */
    @Test
    @DisplayName("하나의 카테고리 선택 - 해당 게시물만 조회 가능 ")
    void get_category_lists(){

    }
}
