package org.study.controllers.users;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.study.commons.validators.CellPhoneValidator;
import org.study.repositories.UserRepository;

/**
 * 회원 가입 추가 유효성 검사
 *
 */
@Component
@RequiredArgsConstructor
public class UserJoinValidator implements Validator, CellPhoneValidator {

    @NonNull
    private UserRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserJoin.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserJoin userJoin = (UserJoin)target;
        String userId = userJoin.getUserId();
        String userPw = userJoin.getUserPw();
        String userPwCk = userJoin.getUserPwCk();
        String cellPhone = userJoin.getCellPhone();

        /** 1. 아이디 중복 여부 S */
        if (userId != null && !userId.isBlank() && repository.isUserExists(userId)) {
            errors.rejectValue("userId", "user.validation.exists");
        }
        /** 1. 아이디 중복 여부 E */

        /** 2. 비밀번호, 비밀번호 확인 체크 S */
        if (userPw != null && !userPw.isBlank() && userPwCk != null && !userPwCk.isBlank()) {
            errors.rejectValue("userPw", "user.validation.passwordIncorrect");
        }
        /** 2. 비밀번호, 비밀번호 확인 체크 E */

        /** 3. 휴대전화 번호가 있다면 체크 S */
        if (cellPhone != null && !cellPhone.isBlank()) {
            cellPhone = cellPhone.replaceAll("\\D", "");
            userJoin.setCellPhone(cellPhone);

            if (!checkCellPhoneNumber(cellPhone)) {
                errors.rejectValue("cellPhone", "validation.wrongCellphoneType");
            }
        }
        /** 3. 휴대전화 번호가 있다면 체크 E */
    }
}
