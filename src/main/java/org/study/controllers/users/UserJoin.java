package org.study.controllers.users;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * 회원 가입 커맨드 객체 생성 클래스
 *
 */
@Data
public class UserJoin {
    @NotBlank
    private String userId;
    @NotBlank
    private String userPw;

    @NotBlank
    private String userPwCk; // 비밀번호 확인

    @NotBlank
    private String userNm;

    @NotBlank @Email
    private String email;

    private String cellPhone;

    @AssertTrue
    private boolean termsAgree; // 회원가입 약관 동의
}
