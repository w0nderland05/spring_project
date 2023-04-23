package org.study.models.category;

import org.springframework.http.HttpStatus;
import org.study.commons.validators.CommonException;

import java.util.ResourceBundle;

/**
 * 분류 코드 중복 등록시 발생 예외
 *
 */
public class DuplicateCateCdException extends CommonException {
    private static String message;
    static {
        ResourceBundle rs = ResourceBundle.getBundle("messages.validation");
        message = rs.getString("Duplicate.categoryForm.cateCd");
    }
    public DuplicateCateCdException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
