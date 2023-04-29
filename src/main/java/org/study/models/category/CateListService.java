package org.study.models.category;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import static org.springframework.data.domain.Sort.Order.desc;

import org.springframework.stereotype.Service;
import org.study.controllers.admin.category.CategorySearch;
import org.study.entities.Category;
import org.study.repositories.CategoryRepository;

import java.util.List;

/**
 * 분류 목록 조회 서비스
 *
 */
@Service
@RequiredArgsConstructor
public class CateListService {

    private final CategoryRepository repository;

    public List<Category> getAll() { // 분류 전체 조회
        return gets(null, 0, 0);
    }

    public List<Category> gets(CategorySearch search, int page, int limit) {

        Sort sort = Sort.by(desc("listOrder"), desc("createdAt")); // 기본 정렬은 최신 등록 순으로
        List<Category> categories = repository.findAll(sort);

        return categories;
    }
}
