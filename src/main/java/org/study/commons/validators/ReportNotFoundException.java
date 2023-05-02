package org.study.commons.validators;


import org.springframework.http.HttpStatus;

import java.util.ResourceBundle;

public class ReportNotFoundException extends CommonException {

    private static String message;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("messages.validation");
        message = bundle.getString("NotFound.csConfig.notExists");
    }

    public ReportNotFoundException() {
        super(message, HttpStatus.NOT_FOUND);
    }
}
