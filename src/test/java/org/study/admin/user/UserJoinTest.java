package org.study.admin.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserJoinTest {


    @Test
    @DisplayName("회원가입 성공시 true 반환 (최종목적)")
    void JoinSuccess(){

    }

    @Test
    @DisplayName("User가 Null일때 JoinFailException 발생")
    void JoinUser_Null_Fail(){

    }

    @Test
    @DisplayName("User 객체에 값이 비어있으면 예외 발생")
    void JoinUser_Empty_Fail(){

    }
/**
    @Test
    @DisplayName("회원가입 완료후 비밀번호 해시화 되었는지 체크")
    void JoinUser_Password_Hash(){

    }
 */

    @Test
    @DisplayName("생성된 해시과 실제 비밀번호 일치하는지 체크")
    void JoinUser_Password_Equals_Hash(){

    }

    @Test
    @DisplayName("Email로 DB-회원 목록 조회 체크")
    void Check_UserList_for_Email(){

    }
}
