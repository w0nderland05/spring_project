package org.study.admin.user;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;


@SpringBootTest
@TestPropertySource(locations="classpath:application-test.properties")
@AutoConfigureMockMvc
@Transactional // 테스트후 데이터 지우기
public class UserJoinTest {

    @Autowired
    private MockMvc mockMvc;
    private UserJoin userJoin;
    @Autowired
    private UserJoinService joinService;

    @Autowired
    private UserRepository repository;

    @Autowired
    private UserJoinValidator joinValidator;

    private Errors errors;
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
    @DisplayName("userJoin - null값 일때 오류 메세지 반환 ")
    void join_Essential() {
        BadRequestException thrown = assertThrows(BadRequestException.class,()->{
            joinService.join(null);
        });

        assertTrue(thrown.getMessage().contains("잘못된 접근"));

        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatus());
    }

    /**
     * 필수 입력 값 : userEmail, userPw, userPwCk, userNickNm, userNm
     *
     * Blank or Null 값 입력시 오류 메세지 포함 확인
     *
     * 나머지 validator에 관한 오류 확인은 통합테스트를 통해 확인 할 예정
     */

    @Test
    @DisplayName("필수항목 체크 - userEmail ")
    void join_User_Null_BadRequest() {

        String userEmail = userJoin.getUserEmail();
        for(int i=0;i<2;i++){
            if(i==0){
                userJoin.setUserEmail(" ");
            }else {
                userJoin.setUserEmail(null);
            }

            BadRequestException thrown = assertThrows(BadRequestException.class,()->{

                joinService.join(userJoin);
            });

            assertTrue(thrown.getMessage().contains("이메일"));

            userJoin.setUserEmail(userEmail);
            i++;
        }
    }

    @Test
    @DisplayName("필수항목 체크 - userPw ")
    void join_User_Null_BadRequest1() {

        String UserPw = userJoin.getUserPw();
        for(int i=0;i<2;i++){
            if(i==0){
                userJoin.setUserPw(" ");
            }else {
                userJoin.setUserPw(null);
            }

            BadRequestException thrown = assertThrows(BadRequestException.class,()->{

                joinService.join(userJoin);
            });

            assertTrue(thrown.getMessage().contains("비밀번호"));

            userJoin.setUserPw(UserPw);
            i++;
        }
    }

    @Test
    @DisplayName("필수항목 체크 - userPwCk")
    void join_User_Null_BadRequest2() {

        String UserPwCk = userJoin.getUserPwCk();
        for(int i=0;i<2;i++){
            if(i==0){
                userJoin.setUserPwCk(" ");
            }else {
                userJoin.setUserPwCk(null);
            }

            BadRequestException thrown = assertThrows(BadRequestException.class,()->{

                joinService.join(userJoin);
            });

            assertTrue(thrown.getMessage().contains("비밀번호를 확인"));
            userJoin.setUserPwCk(UserPwCk);
            i++;
        }
    }
    @Test
    @DisplayName("필수항목 체크 - userNickNm")
    void join_User_Null_BadRequest3() {

        String UserNickNm = userJoin.getUserNickNm();
        for(int i=0;i<2;i++){
            if(i==0){
                userJoin.setUserNickNm(" ");
            }else {
                userJoin.setUserNickNm(null);
            }

            BadRequestException thrown = assertThrows(BadRequestException.class,()->{

                joinService.join(userJoin);
            });

            assertTrue(thrown.getMessage().contains("닉네임"));

            userJoin.setUserNickNm(UserNickNm);
            i++;
        }
    }

    @Test
    @DisplayName("필수항목 체크 - userNm")
    void join_User_Null_BadRequest4() {

        String UserNm = userJoin.getUserNm();
        for(int i=0;i<2;i++){
            if(i==0){
                userJoin.setUserNm(" ");
            }else {
                userJoin.setUserNm(null);
            }

            BadRequestException thrown = assertThrows(BadRequestException.class,()->{

                joinService.join(userJoin);
            });

            assertTrue(thrown.getMessage().contains("회원명"));

            userJoin.setUserNm(UserNm);
            i++;
        }
    }

    /** 필수항목 체크 - E */

    /** 통합 테스트 S */
    @Test
    @DisplayName("성공적으로 회원가입되면 /user/login 으로 이동")
    void joinSuccessRedirectTest() throws Exception{
        // mock으로 진행시 필수 항목만 넣기
        mockMvc.perform(post("/user/join")
                .param("userEmail",userJoin.getUserEmail())
                .param("userPw",userJoin.getUserPw())
                .param("userPwCk",userJoin.getUserPwCk())
                .param("userNickNm",userJoin.getUserNickNm())
                .param("userNm",userJoin.getUserNm())
                .param("gender",String.valueOf(userJoin.getGender()))
                                .param("termsAgree", String.valueOf(userJoin.isTermsAgree()))
                //.param("birth", userJoin.getBirth())
            //    .param("cellphone",userJoin.getCellphone())
                .with(csrf()))
                .andExpect(redirectedUrl("/user/login"));
    }

    @Test
    @DisplayName("유효성 검사 오류메세지 반환 체크")
    void errorMessageResponseTest() throws Exception {
        joinService.join(userJoin);

        String body = mockMvc.perform(post("/user/join")
                .param("userEmail",userJoin.getUserEmail()).with(csrf()))
                .andReturn().getResponse().getContentAsString();
        assertTrue(body.contains("이미 등록된 회원입니다."));
        /**
         *
         */


    }


}