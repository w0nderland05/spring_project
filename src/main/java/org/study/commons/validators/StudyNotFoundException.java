package org.study.commons.validators;

import org.springframework.http.HttpStatus;

public class StudyNotFoundException extends CommonException{

    public StudyNotFoundException() {
        super(bundleValidation.getString("NotRegister.study"), HttpStatus.NOT_FOUND);
    }

}
