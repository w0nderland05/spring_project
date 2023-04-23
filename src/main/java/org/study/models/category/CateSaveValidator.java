package org.study.models.category;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.study.commons.validators.BadRequestException;
import org.study.commons.validators.RequiredCheckValidator;
import org.study.commons.validators.ServiceValidator;
import org.study.controllers.admin.category.CategoryForm;
import org.study.repositories.CategoryRepository;

/**
 * 카테고리 저장 validator
 *
 */
@Component
@RequiredArgsConstructor
public class CateSaveValidator implements ServiceValidator<CategoryForm>, RequiredCheckValidator {

    private final CategoryRepository repository;

    @Override
    public void check(CategoryForm categoryForm) {
        /** categoryForm이 null 값일 경우 예외 발생 */
        nullCheck(categoryForm, new BadRequestException("잘못된 접근입니다."));

        /** 필수 항목 체크 - cateCd, location, cateNm */
        requiredCheck(categoryForm.getCateCd(), new BadRequestException("분류코드를 입력하세요."));
        requiredCheck(categoryForm.getLocation(), new BadRequestException("노출위치 코드를 입력하세요."));
        requiredCheck(categoryForm.getCateNm(), new BadRequestException("분류명을 입력하세요."));

        /** cateCd 중복 여부 체크 */
        if (repository.exists(categoryForm.getCateCd())) { // 이미 등록된 카테고리 분류인 경우
            throw new DuplicateCateCdException();
        }
    }
}
