package org.study.admin.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserJoinTest {


    @Test
    @DisplayName("가입-예외 발생하지 않고, 성공적으로 가입됨(최종 목적), test시 true 반환")
    void joinSuccess(){

    }

    /** 유효성 검사 S */
    @Test
    @DisplayName("자동생성- 회원번호 자동생성되는지 체크")
    void JoinTest_Auto_Increment_UserNo(){

    }

    @Test
    @DisplayName("필수항목 유효성 검사-이메일 형식 아니면 ")
    void joinTest_Valid_Essential_Of_Email(){

    }

    @Test
    @DisplayName("필수항목 유효성 검사-비밀번호 암호화 정도")
    void JoinTest_Valid_Essential_Of_PassWord(){

    }

    @Test
    @DisplayName("필수항목 유효성 검사-생년월일_2023기준, 1900이하 존재 x")
    void JoinTest_Valid_Essential_Of_Birth(){

    }

    @Test
    @DisplayName("필수항목 유효성 검사-휴대전화번호_양식에 맞는지 체크")
    void JoinTest_Valid_Essential_Of_CellPhone(){

    }

    @Test
    @DisplayName("필수항목 유효성 검사 - 필수항목 천체 체크")
    void JoinTest_Essential(){

    }
    /** 유효성 검사 E */


    /** DB 연결 성공 */

    @Test
    @DisplayName("Email로 회원 목록 조회 체크")
    void Check_UserList_for_Email(){
        
    }
}
