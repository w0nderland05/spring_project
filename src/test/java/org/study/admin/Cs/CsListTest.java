package org.study.admin.Cs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * '신고관리 -> 신고목록 기능'에 해당하는 테스트 클래스 입니다.
 *  파일명 : 'ReportListService'
 *  메서드 : gets(), get(), regDt()
 */

public class CsListTest {

    /**
     * @Test ReportListService::gets()
     *
     * 메서드 gets()에 해당하는 테스트 메서드입니다.
     * 신고 목록 전체를 조회할 수 있습니다.
     */
    @Test
    @DisplayName("신고목록 조회가능한지 체크-ReportNotFoundException 예외 발생")
    void Report_gets(){

    }

    /**
     * @Test ReportListService::get()
     *
     * 메서드 get()에 해당하는 테스트 메서드입니다.
     * 한 개의 신고목록 만을 조회할 때 필요한 기능입니다.
     * ReportEntity::code를 통해서 조회가능합니다.
     */
    @Test
    @DisplayName("Code를 통해서 하나의 목록만 조회 가능한지 체크")
    void Report_get(){

    }

    /**@Test ReportService::regDt()
     *
     * 메서드 regDt()에 해당하는 테스트 메서드입니다.
     * 최신 등록순으로 정렬 되는지 체크하는 기능입니다.
     */
    @Test
    @DisplayName("최신 등록순으로 정렬되는지 체크")
    void Report_Lists_By_CreatedDt(){

    }
}
