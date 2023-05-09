package org.study.models.Community;

import org.springframework.http.HttpStatus;
import org.study.commons.validators.CommonException;

import java.util.ResourceBundle;

/**
 * 분류 코드 중복 등록 시 발생 예외
 */
public class DuplicatePostGidException extends CommonException {
    private static String message;
    static {
        ResourceBundle rs = ResourceBundle.getBundle("messages.validation");
        message = rs.getString("Duplicate.postConfig.gid");
    }

    public DuplicatePostGidException() { super(message, HttpStatus.BAD_REQUEST); }
}
