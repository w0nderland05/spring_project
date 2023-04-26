package org.study.admin.Cs;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.study.commons.constants.ReportStatus;
import org.study.commons.validators.ReportNotFoundException;
import org.study.repositories.ReportRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * '신고관리 -> 신고목록 기능'에 해당하는 테스트 클래스 입니다.
 *  파일명 : 'ReportListService'
 *  메서드 : gets(), get(), regDt()
 */

@SpringBootTest
@WebAppConfiguration
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class CsListTest {
    @MockBean
    private CsConfig csConfig;

    @Autowired
    private ReportListService listService;

    @BeforeEach
    public void report() {
        csConfig = new CsConfig();
        csConfig.setDivision("board");
        csConfig.setCode(Long.valueOf("58670212"));
        csConfig.setDetail("빵꾸똥꾸라고 욕했어요.");
        csConfig.setStatus(ReportStatus.READY);
        csConfig.setProcess("욕설로 5회 신고 확인되어 탈퇴처리되었습니다.");

    }

    /**
     * @Test ReportListService::gets()
     *
     * 메서드 gets()에 해당하는 테스트 메서드입니다.
     * 신고 목록 전체를 조회할 수 있습니다.
     */
    @Test
    @DisplayName("신고목록 조회가능한지 체크-ReportNotFoundException 예외 발생")
    void report_gets(){
        List<CsConfig> list = listService.gets();
        System.out.println(list);
//        ReportNotFoundException thrown = assertThrows(ReportNotFoundException.class, () -> {
//            listService.reportList(csConfig);
//        });
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
    void report_get(){

    }

    /**@Test ReportService::regDt()
     *
     * 메서드 regDt()에 해당하는 테스트 메서드입니다.
     * 최신 등록순으로 정렬 되는지 체크하는 기능입니다.
     */
    @Test
    @DisplayName("최신 등록순으로 정렬되는지 체크")
    void report_Lists_By_CreatedDt(){

    }
}
