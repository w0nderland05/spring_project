package org.study.models.studies;

import org.springframework.http.HttpStatus;
import org.study.commons.validators.CommonException;

/**
 * 스터디 코드 중복 예외
 * 
 */
public class DuplicateStudyException extends CommonException {

    public DuplicateStudyException() {
        super(bundleValidation.getString("Duplicated.studyCd"), HttpStatus.BAD_REQUEST);
    }
}
