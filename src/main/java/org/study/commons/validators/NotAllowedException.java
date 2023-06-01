package org.study.commons.validators;

import org.springframework.http.HttpStatus;

/**
 * 접근 권한이 없을 때 예외
 *
 */
public class NotAllowedException extends CommonException {
    public NotAllowedException() {
        super(bundleValidation.getString("NotAllowed"), HttpStatus.UNAUTHORIZED);

    }
}
