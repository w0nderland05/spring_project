package org.study.admin.user;

public class JoinFailException extends RuntimeException{
    public JoinFailException(){
        super("회원가입에 실패")
    }
}
