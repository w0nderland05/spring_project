package org.study.commons.validators;

import org.springframework.http.HttpStatus;

/**
 * 접근권한이 없을때  예외
 */

public class NotAllowedException extends CommonException {
    public NotAllowedException() {
        super(bundleValidation.getString("NotAllowed"), HttpStatus.UNAUTHORIZED);

    }
}
