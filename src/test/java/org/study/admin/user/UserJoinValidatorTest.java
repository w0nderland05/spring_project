package org.study.admin.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.Errors;
<<<<<<< HEAD
import org.springframework.validation.Validator;
import org.study.commons.constants.Gender;
import org.study.commons.validators.PasswordValidator;
import org.study.controllers.user.UserJoin;
import org.study.controllers.user.UserJoinValidator;
import org.study.models.user.UserJoinService;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.*;
=======
import org.study.controllers.user.UserJoinValidator;
import org.study.models.user.UserJoinService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

>>>>>>> 364e5dc25cc12edce9d8d264c69ab529c10d670b

/**
 * UserJoinValidator 파일을 통해서 각 항목에 대한 유효성 검사를 진행할 예정입니다.
 *
 *
 */
@SpringBootTest
public class UserJoinValidatorTest {
    private UserJoin userJoin;

    @Autowired
    private UserJoinService service;

<<<<<<< HEAD
    @BeforeEach
    void join() {

        userJoin = new UserJoin();
        userJoin.setUserEmail("user01@korea.org");
        userJoin.setUserNm("사용자01");
        userJoin.setUserPw("82everywin!");
        userJoin.setUserPwCk("82everywin!");
        userJoin.setUserNickNm("뚜비");
        userJoin.setGender(Gender.MAN);
        userJoin.setBirth("2000-08-02"); // service 를 통해 들어가는건 validate 걸친 예
        userJoin.setCellphone("01000110022"); //생일과 마찬가지
        userJoin.setTermsAgree(true);

        //service.join(userJoin);

    }
=======
    @Mock
    private Errors errors;


    /**
     * Validator Errors::Reject메서드 호출 검증
     * @param errorCode
     * @param defaultMessage
     */
    private void checkErrorsReject(String errorCode, String defaultMessage) {
        ArgumentCaptor<String> captureErrorCode = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captureDefaultMessage = ArgumentCaptor.forClass(String.class);
        then(errors).should().reject(captureErrorCode.capture(), captureDefaultMessage.capture());

        /** 캡쳐된 인자 추출 */
        String realErrorCode = captureErrorCode.getValue();
        String realDefaultMessage = captureDefaultMessage.getValue();

        /** 실제 메서드 내에서 주입된 인자와 예상 인자와 검증 */
        assertEquals(realErrorCode, errorCode);
        assertEquals(realDefaultMessage, defaultMessage);

    }

    /**
     * Validator Errors::rejectValue메서드 호출 검증
     * @param errorCode
     * @param defaultMessage
     */
    private void checkErrorsRejectValue(String field, String errorCode, String defaultMessage) {
        ArgumentCaptor<String> captureField = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captureErrorCode = ArgumentCaptor.forClass(String.class);
        ArgumentCaptor<String> captureDefaultMessage = ArgumentCaptor.forClass(String.class);
        then(errors).should().rejectValue(field, captureErrorCode.capture(), captureDefaultMessage.capture());

        /** 캡쳐된 인자 추출 */
        String realField = captureField.getValue();
        String realErrorCode = captureErrorCode.getValue();
        String realDefaultMessage = captureDefaultMessage.getValue();

        /** 실제 메서드 내에서 주입된 인자와 예상 인자와 검증 */
        assertEquals(realField, field);
        assertEquals(realErrorCode, errorCode);
        assertEquals(realDefaultMessage, defaultMessage);

    }

>>>>>>> 364e5dc25cc12edce9d8d264c69ab529c10d670b
    /** 유효성 검사 S */
    @BeforeEach
    void join_user_data() {

    }

    // 필수 항목 체크
    @Test
    @DisplayName("필수항목 체크 -  BadRequestException 발생")
    void join_User_Null_BadRequest() {
       UserJoinValidator validator = mock(UserJoinValidator.class);


       // given
        willReturn("비밀번호는 특수문자를 포함해 8글자 이상이어야 합니다.").given(validator).validate(any(),any());
       //when
       // userjoin.setUserPw("!2345");

    }


    @Test
    @DisplayName("필수항목 유효성 검사-이메일 형식체크")
    void join_Valid_Essential_Of_Email(){

    }

    @Test
    @DisplayName("필수항목 유효성 검사-비밀번호 암호화 강도")
    void join_Valid_Essential_Of_PassWord(){

    }

    @Test
    @DisplayName("필수항목 유효성 검사-비밀번호 확인 중복체크")
    void join_Valid_Essential_Of_PassWord_Check(){

    }

    @Test
    @DisplayName("생성된 해시과 실제 비밀번호 일치하는지 체크")
    void joinUser_Password_Equals_Hash(){

    }

    @Test
    @DisplayName("필수항목 유효성 검사-휴대전화번호_양식에 맞는지 체크")
    void join_Valid_Essential_Of_CellPhone(){

    }

    /** 유효성 검사 E */

    @Test
    @DisplayName("Email로 DB-회원 목록 조회 체크")
    void check_UserList_for_Email(){

    }
}

