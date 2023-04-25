package org.study.admin.user;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.validation.Errors;
import org.study.commons.constants.Gender;
import org.study.commons.validators.BadRequestException;
import org.study.controllers.user.UserJoin;
import org.study.controllers.user.UserJoinValidator;
import org.study.models.user.UserJoinService;
import org.study.repositories.UserRepository;
import org.study.admin.user.*;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
public class UserJoinTest {

    private UserJoin userJoin;
    @Autowired
    private UserJoinService joinService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserJoinValidator joinValidator;

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

    }

    @Test
    @DisplayName("회원가입 성공시 예외발생하지 않음 (최종목적)")
    void joinSuccess() {
        assertDoesNotThrow(() -> {
            joinService.join(userJoin);
        });
        System.out.println(userJoin);

    }

    // 필수 항목 체크
    @Test
    @DisplayName("필수 항목 null값 일때 오류 메세지 반환 ")
    void join_Essential() {
        BadRequestException thrown = assertThrows(BadRequestException.class,()->{
           joinService.join(null);

        });

        assertTrue(thrown.getMessage().contains("잘못된 접근"));



    }
    @Test
    @DisplayName("필수항목 체크 - @NotBlank 값의 오류메세지 제대로 나오는지 ")
    void join_User_Null_BadRequest() {


    }

}