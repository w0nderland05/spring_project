package org.study.models.category;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.study.controllers.admin.category.CategoryForm;
import org.study.entities.Category;
import org.study.repositories.CategoryRepository;

/**
 * 카테고리 저장(추가, 등록)
 *
 *
 */
@Service
@RequiredArgsConstructor
public class CateSaveService {
    private final CateSaveValidator validator;
    private final CategoryRepository repository;

    /**
     * 컨트롤러 Bean Validation 대응
     * @param categoryForm
     */
    public void save(CategoryForm categoryForm) {
        save(categoryForm, null);
    }

    public void save(CategoryForm categoryForm, Errors errors) {
        if (errors != null && errors.hasErrors()) {
            return;
        }

        validator.check(categoryForm, errors);

        /**
         * 엔티티가 이미 등록된 분류라면 기존 엔티티 가져오고
         * 없다면 새로운 엔티티로 변환 CategoryForm.of(categoryForm);
         *
         */
        String cateCd = categoryForm.getCateCd();
        Category category = null;
        if (cateCd != null && repository.exists(cateCd)) { // 이미 등록된 것이 있다면
            category = repository.findById(cateCd).orElseGet(() -> CategoryForm.of(categoryForm));
            category.setCateCd(cateCd);
            category.setCateNm(categoryForm.getCateNm());
            category.setLocation(categoryForm.getLocation());
        }
        
        if (category == null) {
            // CategoryForm -> category 엔티티로 변환
            category = CategoryForm.of(categoryForm);
        }

        // 엔티티 저장 또는 수정 처리
        repository.saveAndFlush(category);
    }


}
