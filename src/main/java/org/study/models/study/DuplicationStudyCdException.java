package org.study.models.study;

import org.springframework.http.HttpStatus;
import org.study.commons.validators.CommonException;

import java.util.ResourceBundle;

public class DuplicationStudyCdException extends CommonException {

    private static String message;

    static {
        ResourceBundle rs = ResourceBundle.getBundle("messages.validation");
        message = rs.getString("Duplicate.studyConfig.studyCd");
    }
    public DuplicationStudyCdException() {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
