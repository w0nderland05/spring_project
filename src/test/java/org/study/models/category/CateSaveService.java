package org.study.models.category;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.study.commons.validators.RequiredCheckValidator;
import org.study.controllers.admin.category.CategoryForm;

/**
 * 카테고리 저장(추가, 등록)
 *
 *
 */
@Service
@RequiredArgsConstructor
public class CateSaveService {
    private final CateSaveValidator validator;

    public void save(CategoryForm categoryForm) {
        validator.check(categoryForm);

    }
}
