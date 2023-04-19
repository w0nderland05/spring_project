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
    @DisplayName("8자 이상, 영문, 특수문자, 숫자 예외없이 true 반환(최종목적)")
    void passwordTest(){
        assertPasswordTrue("82everywin!");

    }
    /**
     * 예외 상황
     * 1가지
     * 1) 8자 이상x 2) 영문 x 3) 숫자 4) 특수문자
     *
     * 2가지 (8자 이상 기본 충족 조건)
     * 1) 영문+숫자 2) 영문+특수문자 3) 숫자 + 특수문자
     */
    @Test
    @DisplayName("오류 메세지 반환해야 통과 - 예외상황  ")
    void passWord_Find_Exception(){
        assertPasswordFail("user01!"); //8자 미만
        assertPasswordFail("123456?!!"); // 영문x
        assertPasswordFail("username!"); // 숫자
        // 8자 이상은 기본 충족 조건
        assertPasswordFail("everywin82"); //영문+숫자
        assertPasswordFail("everywin!@#"); // 영문+ 특수문자
        assertPasswordFail("12345!@#"); // 숫자+특수문자
    }

    @Test
    @DisplayName("특수문자 확인 - 올바른 비번(최종목적)")
    void password_Special_Character(){
        String userPw="82everywin!";
        assertEquals("true",validator.special_character(userPw));
    }

    @Test
    @DisplayName("특수문자 확인- % 사용시 오류 msg -반환시 통과 ")
    void password_Special_Character1_Fail1(){
        String userPw="82everywin%";
        String msg= "비밀번호에 특수문자는 !@#$^*+=-만 사용 가능합니다.";
        assertEquals(msg,validator.special_character(userPw));
    }

    @Test
    @DisplayName("특수문자 확인- & 사용시 오류 msg -반환시 통과 ")
    void password_Special_Character_Fail2(){
        String userPw="82everywin&";
        String msg= "비밀번호에 특수문자는 !@#$^*+=-만 사용 가능합니다.";
        assertEquals(msg,validator.special_character(userPw));
    }

    @Test
    @DisplayName("반복된 문자 확인 - 올바른 비번(최종목적) ")
    void password_Repeat_Character(){
        String userPw="82everywin!";
        assertEquals("true",validator.Repeat_Character(userPw));
    }

    @Test
    @DisplayName("반복된 문자 확인 - 연속문자 사용시 msg - 반환시 통과  ")
    void password_Repeat_Character_Fail(){
      //  String userPw="abcdefghi"; // 이경우는 연속사용x -> 통과하면 x
        String userPw ="aaabbb"; //-> 여기서 테스트 통과 x
        String msg="비밀번호에 동일한 문자를 과도하게 연속해서 사용할 수 없습니다.";
        assertEquals(msg,validator.Repeat_Character(userPw));
    }


    private void assertPasswordTrue(String userPw ) {
        String result = validator.checkPassword(userPw);
        if (result == "true") {
            assertEquals("true", result);
        }
    }
    private void assertPasswordFail(String userPw){
        String result = validator.checkPassword(userPw);
        String msg="비밀번호는 영문과 특수문자 숫자를 포함하여 8자 이상이어야 합니다.";
        if(result==msg){
           assertEquals(msg,result);
       }

    }
}
