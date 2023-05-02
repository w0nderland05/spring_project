package org.study.admin.Cs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.study.commons.constants.ReportStatus;
import org.study.commons.validators.ReportNotFoundException;
import org.study.entities.Report;
import org.study.repositories.ReportRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * '신고관리 -> 신고목록 기능'에 해당하는 테스트 클래스 입니다.
 *  파일명 : 'ReportListService'
 *  메서드 : gets(), get(), regDt()
 */

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
public class CsListTest {

    private CsConfig config;

    @Autowired
    private ReportListService listService;

    @Autowired
    private ReportRepository repository;

    @Autowired
    private CsRegisterService registerService;

    @BeforeEach
    public void report() {

        CsConfig csConfig = CsConfig.builder()
                .division("board")
                .code(58670212L)
                .detail("빵꾸똥꾸라고 욕했어요.")
                .status(ReportStatus.CLEAR.toString())
                .process("욕설로 5회 신고 확인되어 탈퇴처리되었습니다.")
                .build();

        registerService.register(csConfig);

        CsConfig csConfig2 = CsConfig.builder()
                .division("board")
                .code(12345678L)
                .detail("스터디 목적이 부적절해요")
                .status(ReportStatus.CLEAR.toString())
                .process("해당 스터디를 삭제조치하였습니다.")
                .build();

        registerService.register(csConfig2);
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

        assertDoesNotThrow(() -> {
           listService.gets();
        });

        System.out.println(listService.gets());

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

        CsConfig report = listService.get(config.getCode());

        System.out.println(report);

    }

    /**@Test ReportService::createdAt()
     *
     * 메서드 createdAt()에 해당하는 테스트 메서드입니다.
     * 최신 등록순으로 정렬 되는지 체크하는 기능입니다.
     */
    @Test
    @DisplayName("최신 등록순으로 정렬되는지 체크")
    void report_Lists_By_CreatedDt(){

    }

    /**
     * 처리상태(처리완료/처리 대기중) 에 따른 목록만 조회가 되는지 테스트하는 메서드 입니다.
     * 문의관리/ 신고관리 모두 해당
     */
    @Test
    @DisplayName("처리상태에 따른 목록 조회 ")
    void cs_List_By_CsStatus() {

    }
}
