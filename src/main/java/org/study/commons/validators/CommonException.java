package org.study.commons.validators;

import org.springframework.http.HttpStatus;

import java.util.ResourceBundle;

/**
 * 공통 예외
 *
 * 상태 코드와 예외 메세지 함께 처리 가능한 예외
 * 모든 예외의 기준이 되는 예외이며 모든 예외가 상속 받아야 합니다.
 */
public class CommonException extends RuntimeException {

    protected static ResourceBundle bundleValidation;
    protected static ResourceBundle bundleErrors;

    static {
        bundleValidation = ResourceBundle.getBundle("messages.validation");
        bundleErrors = ResourceBundle.getBundle("messages.errors");
    }

    private HttpStatus status;

    public CommonException(String message) {
        this(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public CommonException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
