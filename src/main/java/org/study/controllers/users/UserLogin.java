package org.study.controllers.users;

import lombok.Data;

/**
 * 로그인 커맨드 객체 구현 클래스
 *
 */
@Data
public class UserLogin {
    private String userId;
    private String userPw;

    private String success;
}
