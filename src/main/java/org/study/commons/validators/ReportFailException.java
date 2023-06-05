package org.study.commons.validators;

import org.springframework.http.HttpStatus;

import java.util.ResourceBundle;

public class ReportFailException extends CommonException{

    private static String message;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("messages.validation");
        message = bundle.getString("Report.register.fail");
    }

    public ReportFailException() {
        super(message, HttpStatus.EXPECTATION_FAILED);
    }
}
