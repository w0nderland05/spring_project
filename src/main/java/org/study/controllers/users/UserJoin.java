package org.study.controllers.users;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.study.commons.constants.Gender;
import org.study.entities.User;

import java.util.Calendar;

/**
 * 회원 가입 커맨드 객체 생성 클래스
 *
 */
@Data
public class UserJoin {

    @NotBlank @Email
    private String userEmail; // 이메일

    @NotBlank
    private String userPw; // 비밀번호

    @NotBlank
    private String userPwCk; // 비밀번호 확인

    private String userNickName; // 닉네임
    @NotBlank
    private String userNm; // 회원명

    @Enumerated(EnumType.STRING)
    private Enum gender = Gender.MAN ;

    private Calendar birth;

    @AssertTrue
    private boolean termsAgree; // 회원가입 약관 동의

    // User 엔티티로 변환
    public static User of(UserJoin userJoin) {

        return new ModelMapper().map(userJoin, User.class);
    }
}
