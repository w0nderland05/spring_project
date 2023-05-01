package org.study.admin.Category;

import com.sun.jdi.IntegerValue;
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
public class CateListService {

    private final CategoryRepository repository;

    public List<CategoryForm> gets(){
       List<Category> categories = repository.findAll(Sort.by(desc("regDt")));
       List<CategoryForm> forms = categories.stream().map(this::toForm).toList();

       return forms;

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
