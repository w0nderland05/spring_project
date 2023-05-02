package org.study.commons.validators;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.util.ResourceBundle;

public class StudyNotFoundException extends CommonException{

    private static String message;
    static {
        ResourceBundle bundle = ResourceBundle.getBundle("messages.validation");
        message = bundle.getString("NotFound.study.studyCode");
    }

    public StudyNotFoundException() {
        super(message, HttpStatus.NOT_FOUND);
    }

}
