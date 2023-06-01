package org.study.commons.validators;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;

import java.util.ResourceBundle;

public class StudyNotFoundException extends CommonException{

    public StudyNotFoundException() {
        super(bundleValidation.getString("NotRegister.study"), HttpStatus.NOT_FOUND);
    }

}
