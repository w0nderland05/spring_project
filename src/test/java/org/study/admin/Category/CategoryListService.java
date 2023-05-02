package org.study.admin.Category;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.study.controllers.admin.category.CategoryForm;
import org.study.entities.Category;
import org.study.repositories.CategoryRepository;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class CategoryListService {

    private final CategoryRepository repository;

    public List<Category> gets(){
       List<Category> categories = repository.findAll(Sort.by(desc("createdAt")));

       return categories;

    }

    private CategoryForm toForm(Category category){
        return CategoryForm.builder()
                .cateCd(category.getCateCd())
                .location(category.getLocation())
                .cateNm(category.getCateNm())
                .use(category.isUse())
                .listOrder(category.getListOrder().intValue())
                .parentCateCd(category.getParentCateCd())
                .build();


    }
}
