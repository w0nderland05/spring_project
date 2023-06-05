package org.study.admin.Category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.study.controllers.admin.category.CategoryForm;
import org.study.entities.Category;
import org.study.models.category.CateListService;
import org.study.models.category.CateSaveService;

import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations="classpath:application-test.properties")
@DisplayName("카테고리 목록 조회 - CateListService")
public class CategoryListServiceTest {

    @Autowired
    private CateListService listService;

    @Autowired
    private CateSaveService saveService;

    /**
     * 테스트를 위한 데이터 추가
     * - 페이징까지 테스트 해야 하므로 123개 등록
     */

    private int cateCnt = 123;

    void insertData() {
        for (int i = 1; i <= cateCnt; i++) {
            CategoryForm categoryForm = CategoryForm.builder()
                        .use(true)
                        .cateNm(i + "차분류")
                        .cateCd("cate" + i)
                        .location("study")
                        .build();
            saveService.save(categoryForm);
        }
    }

    /** 진열 가중치별 데이터 */
    void insertListOrderData() {
        for (int i = 1; i <= cateCnt; i++) {
            CategoryForm categoryForm = CategoryForm.builder()
                    .use(true)
                    .cateNm(i + "차분류")
                    .cateCd("cate" + i)
                    .location("study")
                    .listOrder(i * 1000)
                    .build();
            saveService.save(categoryForm);
        }
    }

    @Test
    @DisplayName("전체 데이터 조회 - 조회된 데이터가 cateCnt와 갯수가 일치하면 성공")
    void getAllTest() {
        insertData();
        List<Category> categories = listService.getAll();

        assertEquals(cateCnt, categories.size());
    }

    @Test
    @DisplayName("조회된 데이터의 기본 정렬 - 최신 등록순으로 정렬이 되면 성공")
    void getDefaultSortTest() {
        insertData();
        List<Category> categories = listService.getAll();
        // 원래 날짜 조회
        long[] createdAts1 = categories.stream().map(Category::getCreatedAt)
                .mapToLong(t->t.toInstant(ZoneOffset.UTC).getEpochSecond()).toArray();
        // 최신순으로 정렬
        long[] createdAts2 = createdAts1 = categories.stream().map(Category::getCreatedAt)
                .sorted().sorted(Comparator.reverseOrder()).mapToLong(t->t.toInstant(ZoneOffset.UTC).getEpochSecond()).toArray();

        boolean result = Arrays.equals(createdAts1, createdAts2);
        assertTrue(result);

        assertEquals(createdAts2[0], createdAts1[0]);
        assertEquals(createdAts2[createdAts2.length - 1], createdAts1[createdAts1.length - 1]);
    }

    @Test
    @DisplayName("조회된 데이터의 listOrder(진열 가중치) + 기본 정렬 - 진열 가중치 내림차순 + 기본정렬(최신순)이 되면 성공")
    void getListOrderSortTest() {
        insertListOrderData();
        List<Category> categories = listService.getAll();
        // 원래 날짜 조회
        long[] listOrders = categories.stream().mapToLong(Category::getListOrder).toArray();
        long startNum = cateCnt * 1000;
        for (long num : listOrders) {
            if (num != startNum) { // 일치하지 않는 숫자가 있다면 실패
                fail();
            }

            startNum -= 1000;
        }
    }

    @Test
    @DisplayName("조회된 페이징 동작 여부 체크")
    void getListPagingTest() {
        
    }
}
