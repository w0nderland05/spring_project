package org.study.admin.user;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.study.controllers.user.UserLogin;

@SpringBootTest
@RequiredArgsConstructor
public class UserLoginTest {

    private final UserLogin userLogin;

    @Test
    @DisplayName("로그인 성공시 true 반환")
    void LoginSuccess(){

    }

    /** 로그인시 필수 항목 체크 S */

    @Test
    @DisplayName("이메일 값 null이면 오류메세지 반환")
    void Login_Email_Null_Then_Exception(){

    }

    @Test
    @DisplayName("비밀번호 값 null이면 오류메세지 반환")
    void Login_Password_Null_Then_Exception(){

    }
    /** 로그인시 필수 항목 체크 E */

    /** DB에서 조회하여 없는 경우 발생 */

    @Test
    @DisplayName("이메일 값 존재하지 않는 경우 (틀리게 입력 ) - '존재하지 않는 이메일 입니다'메세지 반환 체크")
    void Login_NotFound_Email(){

    }

    @Test
    @DisplayName("비밀번호 값 존재하지 않는 경우(틀리게 입력)-'존재하지 않는 비밀번호 입니다'메세지 반환")
    void Login_NotFound_Password(){

    }

    @Test
    @DisplayName("등록된 회원이지만, 비밀번호가 일치하지 않는 경우 -'비밀번호가 일치하지 않습니다'메세지 반환")
    void Login_NotEquals_Password(){

    }

    @Test
    @DisplayName("'아이디 저장'시 session에 값 저장되어있는지 체크")
    void Login_Email_Save_Session(){

    }

}
