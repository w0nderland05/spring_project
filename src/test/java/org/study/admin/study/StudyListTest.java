package org.study.admin.study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.study.commons.constants.RegionType;
import org.study.commons.constants.Status;
import org.study.controllers.admin.study.StudyConfig;
import org.study.models.study.StudyApplyService;
import org.study.models.study.StudyListService;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

/**
 * '스터디 관리 -> 스터디목록관리/ 개설신청관리 '에 해당하는 테스트 클래스 입니다.
 *
 */

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
public class StudyListTest {

    @Autowired
    private StudyListService listService;

    @Autowired
    private StudyApplyService applyService;


    private StudyConfig studyConfig;

    @BeforeEach
    public void register(){
        studyConfig = new StudyConfig();
        studyConfig.setMode("create");
        studyConfig.setStudyCode(Long.valueOf("5245625"));
        studyConfig.setStudyNm("코리아스터디");
        studyConfig.setCategory("IT");
        studyConfig.setRequestDt(LocalDateTime.now());
        studyConfig.setApproveStatus(Status.APPLY);
        studyConfig.setRegStatusDt(LocalDateTime.now());
        studyConfig.setMaxMember(Long.valueOf("40"));
        studyConfig.setRemainSeat(Long.valueOf("3"));
        studyConfig.setActiveStatus(true);
        studyConfig.setNumOfWeek("주2-3회");
        studyConfig.setRegionType(RegionType.OFFLINE);
        studyConfig.setSimpleIntro("백엔드개발 스터디 입니다.");
        studyConfig.setIntroduction("즐겁게 공부해봅시다.");

        applyService.apply(studyConfig);

    }





    /**
     * @Test StudyListService::gets()
     *스터디목록 전체를 조회
     * 메서드 gets()
     */

    @Test
    @DisplayName("스터디 목록 전체 조회")
    void study_gets(){
        assertDoesNotThrow(() -> {
       listService.gets();
        });



    }

    /**
     * @Test StudyListService::get()
     * 스터디목록 개별 조회
     * 메서드 get()
     */
    @Test
    @DisplayName("스터디 목록 개별 조회")
    void study_get(){

    }

}
