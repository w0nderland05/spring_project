package org.study.commons.validators;

/**
 * 휴대전화 번호 검증
 *
 */
public interface CellPhoneValidator {
    default boolean checkCellPhoneNumber(String cellPhone) {
        // 문자열에서 숫자를 제외한 다른 문자를 모두 제거하는 코드
        cellPhone = cellPhone.replaceAll("\\D", "");
        String pattern = "^01[016]\\d{3,4}\\d{4}$";
        return cellPhone.matches(pattern);
    }
}
