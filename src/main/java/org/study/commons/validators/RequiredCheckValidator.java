package org.study.commons.validators;

public interface RequiredCheckValidator {
    /**
     * null 값 체크
     *
     * @param o
     * @param e
     */
    default void nullCheck(Object o, CommonException e) {
        if (o == null) {
            throw e;
        }
    }

    /**
     * 문자열 필수 항목 체크
     *
     * @param str
     * @param e
     */
    default void requiredCheck(String str, CommonException e) {
        if (str == null || str.isBlank()) {
            throw e;
        }
    }
}
