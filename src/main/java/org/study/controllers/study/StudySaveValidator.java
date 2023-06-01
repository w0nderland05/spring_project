package org.study.controllers.study;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.study.repositories.StudiesRepository;

/**
 * 스터디 저장 처리 추가 유효성 검사
 *
 */
@Component
@RequiredArgsConstructor
public class StudySaveValidator implements Validator {

    private final StudiesRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return StudyForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        StudyForm studyForm = (StudyForm)target;
        String studyCd = studyForm.getStudyCd();

        /** 스터디 코드 중복 여부 체크 */
        if (studyForm.getStudyNo() == null && studyCd != null && repository.exists(studyCd)) {
            errors.rejectValue("studyCd", "Duplicated");
        }


    }
}
