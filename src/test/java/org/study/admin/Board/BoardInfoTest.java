package org.study.admin.Board;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class BoardInfoTest {

    /**
     *  게시물 목록을 조회할 때 필요한 테스트입니다.
     *  클래스 : BoardInfoService
     *  메서드 : get()
     */
    @Test
    @DisplayName("개별 조회 - BId를 통해 게시글을 가져 올 수 있다. ")
    void get() {

    }
}
