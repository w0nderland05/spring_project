package org.study.admin.study;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
@ExtendWith(MockitoExtension.class)
public class StudyApplyTest {
    /**
    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private StudyCategoryRepository scRepository;

    // private final StudyApplyService applyService;
    @Autowired
    private StudyApplyService applyService;


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
    /**

    /** 단위 테스트 S */

    // 단위 테스트는 구현된 Service가 정상 동작하는지 단위별로 테스트 합니다.
    // junit5의 단언(assert)와 mockito를 주로 사용하게 됩니다.

    @Test
    @DisplayName("스터디 개설 신청이 완료되면 예외가 발생하지 않음(최종 목적)")
    public void applySuccess() {
    }

    @Test
    @DisplayName("스터디 신청 필수 항목 유효성 검사 - 카테고리, 한줄 소개글, 소개내용, 메인 사진")
    public void applyValidationTest(){
        // 카테고리는 나중에 검사



    }

    /**
     * 신청 데이터 항목을 커맨드 객체에 담아 두고 그 데이터를 가지고
     * apply() 함수를 호출해서 최종 처리 결과를 보고
     * H2 데이터베이스에 추가된 내용과 비교하여 각 항목의 일치여부를 검증한다.
     */
    @Test
    @DisplayName("스터디 신청 데이터와 DB 데이터의 일치 여부 체크")
    public void assertApplyToDB(){


    }


    /** 단위 테스트 E */

    /**
     * 스터디에 들어가있는 회원 조회 -> querydsl 사용
     */

}

