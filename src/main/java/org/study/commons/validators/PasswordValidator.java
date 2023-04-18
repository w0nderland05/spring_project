package org.study.commons.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface PasswordValidator {

    // 비밀번호 유효성 검사
    default String checkPassword(String userPw ) {

        // 특수문자 확인 메서드 위치
        // 연속된 문자가 사용되었는지 체크할 것.

        Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");
        Matcher passMatcher1 = passPattern1.matcher(userPw);

        if (!passMatcher1.find()) {
            return "비밀번호는 영문과 특수문자 숫자를 포함하여 8자 이상이어야 합니다.";
        }

        return "true";
    }

    /**
     * 예외 상황
     * 1가지
     * 1) 8자 이상x 2) 영문 x 3) 숫자 4) 특수문자
     *
     * 2가지 (8자 이상 기본 충족 조건)
     * 1) 영문+숫자 2) 영문+특수문자 3) 숫자 + 특수문자
     */

   default String special_character(String userPw){
        // 특수문자 확인
        Pattern passPattern3 = Pattern.compile("\\W");
        Pattern passPattern4 = Pattern.compile("[!@#$^*+=-]");

        for(int i = 0; i < userPw.length(); i++){
            String s = String.valueOf(userPw.charAt(i));
            Matcher passMatcher3 = passPattern3.matcher(s);

            if(passMatcher3.find()){
                Matcher passMatcher4 = passPattern4.matcher(s);
                if(!passMatcher4.find()){
                    return "비밀번호에 특수문자는 !@#$^*+=-만 사용 가능합니다.";
                }
            }
        }
        return "true";
    }

    // 연속 해서 사용하는 문자의 기준이 몇개인지 >>
    // 여기서 기본 조건을 통과하는 기준을 갖춘건지
    default String Repeat_Character(String userPw){
        // 반복된 문자 확인
        Pattern passPattern2 = Pattern.compile("(\\w)\\1\\1\\1");
        Matcher passMatcher2 = passPattern2.matcher(userPw);

        if(passMatcher2.find()){
            // 나머지 조건은 만족해야 함,
            return "비밀번호에 동일한 문자를 과도하게 연속해서 사용할 수 없습니다.";
        }
        return "true";
    }

    // Repeat_Character 테스트 통과시  checkPassword() 에 메서드 주입합시다.



}


