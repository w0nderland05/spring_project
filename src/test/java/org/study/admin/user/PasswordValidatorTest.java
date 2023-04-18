package org.study.admin.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.study.commons.validators.PasswordValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PasswordValidatorTest {

    private PasswordValidator validator;

    @BeforeEach
    void init() { //인터페이스
        validator = new PasswordValidator() {};
    }

    @Test
    @DisplayName("8자 이상, 영문, 특수문자, 숫자(최종목적)")
    void passwordTest(){
        String userPw = "82everywin!";
        String userEmail = "user01";

        assertEquals("true",validator.checkPassword(userPw,userEmail));

        // 오류 메세지 반환
        String userPw2 = "everywin!";
        String userEmail2 = "user01";

    }


}
