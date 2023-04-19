package org.study.admin.user;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.study.commons.constants.Gender;
import org.study.commons.constants.UserRole;
import org.study.controllers.user.UserJoin;
import org.study.entities.User;
import org.study.models.user.UserJoinService;
import org.study.repositories.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@RequiredArgsConstructor
public class UserJoinTest {

    private final UserJoinService service;

    private final UserRepository repository;

    @BeforeEach
    UserJoin join() {

        UserJoin userJoin = new UserJoin();
        userJoin.setUserEmail("user01@korea.org");
        userJoin.setUserNm("사용자01");
        userJoin.setUserPw("82everywin!");
        userJoin.setUserPwCk("82everywin!");
        userJoin.setUserNickNm("뚜비");
        userJoin.setGender(Gender.MAN);
        userJoin.setBirth(LocalDate.of(2000,8,2));
        userJoin.setCellphone("010-0011-0022");
        userJoin.setTermsAgree(true);
        return userJoin;
    }

    @Test
    @DisplayName("회원가입 성공시 true 반환 (최종목적)")
    void joinSuccess(){
        UserJoin userJoin = join();

        service.join(userJoin);


    }

    @Test
    @DisplayName("User가 Null일때 JoinFailException 발생")
    void JoinUser_Null_Fail(){}


    @Test
    @DisplayName("User 객체에 값이 비어있으면 예외 발생")
    void joinUser_Empty_Fail(){

    }
/**
    @Test
    @DisplayName("회원가입 완료후 비밀번호 해시화 되었는지 체크")
    void joinUser_Password_Hash(){

    }
 */

    @Test
    @DisplayName("생성된 해시과 실제 비밀번호 일치하는지 체크")
    void joinUser_Password_Equals_Hash(){

    }

    @Test
    @DisplayName("Email로 DB-회원 목록 조회 체크")
    void check_UserList_for_Email(){

    }
}
