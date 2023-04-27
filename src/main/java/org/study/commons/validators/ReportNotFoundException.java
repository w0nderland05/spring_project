package org.study.commons.validators;


import org.springframework.http.HttpStatus;

public class ReportNotFoundException extends CommonException {

    public ReportNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
