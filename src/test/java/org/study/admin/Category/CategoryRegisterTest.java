package org.study.admin.Category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.study.commons.validators.BadRequestException;
import org.study.controllers.admin.category.CategoryForm;
import org.study.models.category.CateSaveService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 단위테스트
 * '카테고리를 틍록하는 서비스'에 해당하는 테스트 클래스입니다.
 * 파일명 : 'CateSaveService'
 * 메서드 : save()
 *
 */
@SpringBootTest
@Transactional // 테스트 후 데이터 비우기
@TestPropertySource(locations="classpath:application-test.properties")
public class CategoryRegisterTest {

    @Autowired
    private CateSaveService saveService;

    private CategoryForm categoryForm;


    @BeforeEach
    void init() {
        // 테스트 양식 데이터 추가
        categoryForm = CategoryForm.builder()
                .use(true)
                .cateNm("1차분류")
                .cateCd("1000")
                .location("study")
                .build();
    }

    /**
     * @Test CateSaveService::save()
     */
    @Test
    @DisplayName("카테고리가 성공적으로 등록, 수정 (최종목적)")
    void registerSuccess(){
        assertDoesNotThrow(() -> {
            saveService.save(categoryForm);
        });
    }


    @Test
    @DisplayName("Category Null값이면  예외메세지발생 - BadRequestException")
    void category_Null_Exception(){
        BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
            saveService.save(null);
        });

        // "잘못된 접근입니다." 문구 포함여부 체크
        assertTrue(thrown.getMessage().contains("잘못된 접근"));

        // HttpStatus가 400 - BadRequest로 설정되어 있는지 체크
        assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatus());
    }

    @Test
    @DisplayName("카테고리 등록 필수 입력값 체크- 예외메세지발생")
    void cate_Essential(){
        /**
         * 필수 항목
         *  cateCd : 분류 코드
         *  location : 노출 위치
         *  cateNm : 분류 명
         *
         */

        String[] fields = { "cateCd", "location", "cateNm" };
        String cateCd = categoryForm.getCateCd();
        String cateNm = categoryForm.getCateNm();
        String location = categoryForm.getLocation();

        /** 빈값 체크 S */
        for (String field : fields) {
            String includedWord = null;
            if (field.equals("cateCd")) { // 분류코드
                categoryForm.setCateCd("   ");
                categoryForm.setCateNm(cateNm);
                categoryForm.setLocation(location);
                includedWord = "분류코드";
            } else if (field.equals("location")) { // 노출 위치
                categoryForm.setCateNm(cateNm);
                categoryForm.setCateCd(cateCd);
                categoryForm.setLocation("    ");
                includedWord = "노출위치 코드";
            } else if (field.equals("cateNm")) { // 분류명
                categoryForm.setCateNm("    ");
                categoryForm.setCateCd(cateCd);
                categoryForm.setLocation(location);
                includedWord = "분류명";
            }

            BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
                saveService.save(categoryForm);
            });

            // 예외 메세지에 핵심 키워드가 포함되어 있는지 체크
            assertTrue(thrown.getMessage().contains(includedWord));
            // 상태 코드 검증(HttpStatus.BAD_REQUEST)
            assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatus());

        }
        /** 빈값 체크 E */

        /** NULL 체크 S */
        for (String field : fields) {
            String includedWord = null;
            if (field.equals("cateCd")) { // 분류코드
                categoryForm.setCateCd(null);
                categoryForm.setCateNm(cateNm);
                categoryForm.setLocation(location);
                includedWord = "분류코드";
            } else if (field.equals("location")) { // 노출 위치
                categoryForm.setCateNm(cateNm);
                categoryForm.setCateCd(cateCd);
                categoryForm.setLocation(null);
                includedWord = "노출위치 코드";
            } else if (field.equals("cateNm")) { // 분류명
                categoryForm.setCateNm(null);
                categoryForm.setCateCd(cateCd);
                categoryForm.setLocation(location);
                includedWord = "분류명";
            }

            BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
                saveService.save(categoryForm);
            });

            // 예외 메세지에 핵심 키워드가 포함되어 있는지 체크
            assertTrue(thrown.getMessage().contains(includedWord));

            // 상태 코드 검증(HttpStatus.BAD_REQUEST)
            assertEquals(HttpStatus.BAD_REQUEST, thrown.getStatus());

        }
        /** NULL 체크 E */

        /**
         * 통합 테스트
         */



    }
}
