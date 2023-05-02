package org.study.admin.Category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.study.controllers.admin.category.CategoryForm;
import org.study.entities.Category;
import org.study.repositories.CategoryRepository;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
public class CateListTest {

    private CateListService service;

    private CategoryRepository repository;


    @BeforeEach
    void insert(){

        for(int i=1;i<=5; i++) {
            Category category = Category.builder()
                                .cateCd("분류코드"+i)
                                .use(true)
                                .location("Study")
                                .cateNm("분류1\n분류2\n분류3\n")
                                .listOrder(3L)
                                .build();

            repository.save(category);
        }
    }

    @Test
    @DisplayName("카테고리 목록 조회 - 리스트 나타나면 성공 ")
    void gets(){

       List<CategoryForm> lists = service.gets();

        System.out.println(lists);
    }
}
