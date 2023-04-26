package org.study.commons.validators;


import org.springframework.http.HttpStatus;

public class ReportNotFoundException extends CommonException {

    public ReportNotFoundException(String message, HttpStatus status) {
        super(message, status);
    }
}
