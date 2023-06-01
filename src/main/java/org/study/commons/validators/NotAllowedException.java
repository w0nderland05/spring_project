package org.study.commons.validators;

import org.springframework.http.HttpStatus;

/**
<<<<<<< HEAD
 * 접근 권한이 없을 때 예외
 *
 */
=======
 * 접근권한이 없을때  예외
 */

>>>>>>> 8599cd278c32cbb189dc5157662e7182b7915bbe
public class NotAllowedException extends CommonException {
    public NotAllowedException() {
        super(bundleValidation.getString("NotAllowed"), HttpStatus.UNAUTHORIZED);

    }
}
