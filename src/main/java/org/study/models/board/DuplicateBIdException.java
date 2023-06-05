package org.study.models.board;

import org.springframework.http.HttpStatus;
import org.study.commons.validators.CommonException;

import java.util.ResourceBundle;

/**
 * 게시판 아이디 중복 등록 시 발생 예외
 */
public class DuplicateBIdException extends CommonException {
    private static String message;
    static {
        ResourceBundle rs = ResourceBundle.getBundle("messages.validation");
        message = rs.getString("Duplicate.boardConfig.bId");
    }
    public DuplicateBIdException() {super(message, HttpStatus.BAD_REQUEST);}

}
