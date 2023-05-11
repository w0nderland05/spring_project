package org.study.models.cs;

import org.springframework.http.HttpStatus;
import org.study.commons.validators.CommonException;

import java.util.ResourceBundle;

public class DuplicateQsCodeException extends CommonException {

    private static String message;
    static {
        ResourceBundle rs = ResourceBundle.getBundle("messages.validation");
        message = rs.getString("Duplicate.question.qsCode");
    }
    public DuplicateQsCodeException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
