package org.study.admin.study;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.study.commons.constants.RegionType;
import org.study.commons.constants.Status;
import org.study.commons.validators.BadRequestException;
import org.study.controllers.admin.study.StudyConfig;
import org.study.entities.Study;
import org.study.models.study.StudyApplyService;
import org.study.models.study.StudyRegisterValidator;
import org.study.repositories.StudyRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class StudyApplyTest {

     /**
      * @Test Case - StudyApplyService::apply
     *
     * 스터디 신청이 되면 관리자페이지의 승인을 거치게 되며
     * 승인이라는 상태 값을 통해서 신청 상태가 관리가 됩니다.
     *
     * 테스트는 등록과 승인이라는 측면에서 진행되며 기능이 구현됩니다.
     * 1. 등록에서의 최종 목표는 예외 발생 없이 등록이 되는 것
     * 2. 제대로 등록하기 위한 유효성 검사 항목이 다음과 같이 정리됩니다.
     */
    @Autowired
    private StudyApplyService applyService;

    private StudyConfig studyConfig;

    /** 단위 테스트 S*/

    // 단위 테스트는 구현된 Service가 정상 동작하는지 단위별로 테스트 합니다.
    // junit5의 단언(assert)와 mockito를 주로 사용하게 됩니다.

    @BeforeEach
    public void apply() {
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

    }

    @Test
    @DisplayName("스터디 개설 신청이 완료되면 예외가 발생하지 않음(최종 목적)")
    public void applySuccess() {
        assertDoesNotThrow(() -> {
            applyService.apply(studyConfig);
        });
    }


    @Test
    @DisplayName("study Null값이면 예외메세지 발생 - BadRequestException")
    public void study_Null_Exception() {
        BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
            applyService.apply(null);
        });
        //"잘못된 접근입니다."문구 포함여부 체크
        assertTrue(thrown.getMessage().contains("잘못된 접근"));

        //HttpStatus가 400-BadRequest로 설정되어 있는지 체크
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatus());

    }


    private StudyConfig createStudyConfig() {
        String studyNm = studyConfig.getStudyNm();
        String category = studyConfig.getCategory();
        String numOfWeek = studyConfig.getNumOfWeek();
        String simpleIntro = studyConfig.getSimpleIntro();
        String Introduction = studyConfig.getIntroduction();
        Long maxMember = studyConfig.getMaxMember();
        RegionType regionType = studyConfig.getRegionType();
        Long studyCode = studyConfig.getStudyCode();


        studyConfig.setStudyNm(studyNm);
        studyConfig.setCategory(category);
        studyConfig.setNumOfWeek(numOfWeek);
        studyConfig.setSimpleIntro(simpleIntro);
        studyConfig.setIntroduction(Introduction);
        studyConfig.setMaxMember(maxMember);
        studyConfig.setRegionType(regionType);
        studyConfig.setStudyCode(studyCode);

        return studyConfig;
    }

    @Test
    @DisplayName("스터디 신청 필수 입력값 체크 - 예외메세지 발생")
    public void applyValidationTest() {
        /**
         * 필수항목
         * 스터디명,카테고리,스터디주당횟수, 한줄소개글,소개글 ,신청최대인원수, 스터디 지역타입, 스터디 코드
         */
        String[] fields = {"studyNm", "category", "numOfWeek", "simpleIntro", "Introduction" /**, "maxMember", "regionType", "studyCode" */};


        /** 빈값 체크 S*/
        for (String field : fields) {
            String includedWord = null;
            if (field.equals("studyNm")) { //스터디명
                studyConfig = createStudyConfig();
                studyConfig.setStudyNm(" ");
                includedWord = "스터디명";

            } else if (field.equals("category")) { //카테고리
                studyConfig = createStudyConfig();
                studyConfig.setCategory(" ");
                studyConfig.setStudyNm("sutdyNm");
                includedWord = "카테고리";

            } else if (field.equals("numOfWeek")) { //주당스터디횟수
                studyConfig = createStudyConfig();
                studyConfig.setNumOfWeek(" ");
                studyConfig.setCategory("category");
                includedWord = "주당횟수";

            } else if (field.equals("simpleIntro")) { //한줄소개글
                studyConfig = createStudyConfig();
                studyConfig.setSimpleIntro(" ");
                studyConfig.setNumOfWeek("numOfWeek ");
                includedWord = "한줄 소개글";

            } else if (field.equals("Introduction")) { //소개글
                studyConfig = createStudyConfig();
                studyConfig.setIntroduction(" ");
                studyConfig.setSimpleIntro("simpleIntro");
                includedWord = "소개글";
            }
            BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
                applyService.apply(studyConfig);
            });
            System.out.println(thrown.getMessage());

            // 예외 메세지에 핵심 키워드가 포함되어 있는지 체크
            assertTrue(thrown.getMessage().contains(includedWord));
            // 상태 코드 검증(HttpStatus.BAD_REQUEST)
            assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatus());

        }
        /** 빈값 체크 E*/

        /** NULL 체크 S*/
        String[] fields2 = {"studyNm", "category", "numOfWeek", "simpleIntro", "Introduction" , "maxMember", "regionType", "studyCode" };

        for (String field : fields2) {
            String includedWord = null;
            if (field.equals("studyNm")) { //스터디명
                studyConfig = createStudyConfig();
                studyConfig.setStudyNm(null);

                includedWord = "스터디명";

            } else if (field.equals("category")) { //카테고리
                studyConfig = createStudyConfig();
                studyConfig.setCategory(null);
                studyConfig.setStudyNm("studyNm");
                includedWord = "카테고리";

            } else if (field.equals("numOfWeek")) { //주당스터디횟수
                studyConfig = createStudyConfig();
                studyConfig.setNumOfWeek(null);
                studyConfig.setCategory("category");
                includedWord = "주당횟수";

            } else if (field.equals("simpleIntro")) { //한줄소개글
                studyConfig = createStudyConfig();
                studyConfig.setSimpleIntro(null);
                studyConfig.setNumOfWeek("numOfWeek");
                includedWord = "한줄 소개글";

            } else if (field.equals("Introduction")) { //소개글
                studyConfig = createStudyConfig();
                studyConfig.setIntroduction(null);
                studyConfig.setSimpleIntro("simpleIntro");
                includedWord = "소개글";

            }else if (field.equals("maxMember")) { //최대인원수
                studyConfig = createStudyConfig();
                studyConfig.setMaxMember(null);
                studyConfig.setIntroduction("Introduction");
                includedWord = "신청최대인원수";

            } else if (field.equals("regionType")) { //지역타입
                studyConfig = createStudyConfig();
                studyConfig.setRegionType(null);
                studyConfig.setMaxMember(Long.valueOf("30"));
                includedWord = "지역타입";

            } else if (field.equals("studyCode")) { //스터디코드
                studyConfig = createStudyConfig();
                studyConfig.setStudyCode(null);
                studyConfig.setRegionType(RegionType.OFFLINE);
                includedWord = "스터디 코드";

            }
            BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
                applyService.apply(studyConfig);
            });
            System.out.println(thrown.getMessage());
            //예외메세지에 핵심키워드 포함여부 체크
            assertTrue(thrown.getMessage().contains(includedWord));
            // 상태 코드 검증(HttpStatus.BAD_REQUEST)
            assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatus());

        }
        /**Null 체크 E*/
    }


        /**
         * 신청 데이터 항목을 커맨드 객체에 담아 두고 그 데이터를 가지고
         * apply() 함수를 호출해서 최종 처리 결과를 보고
         * H2 데이터베이스에 추가된 내용과 비교하여 각 항목의 일치여부를 검증한다.
         */
        @Test
        @DisplayName("스터디 신청 데이터와 DB 데이터의 일치 여부 체크")
        public void assertApplyToDB () {


        }


        /** 단위 테스트 E */


    }

