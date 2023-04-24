package org.study.admin.Category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.study.commons.validators.BadRequestException;
import org.study.controllers.admin.category.CategoryForm;
import org.study.models.category.CateSaveService;
import org.study.models.category.DuplicateCateCdException;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * 단위테스트
 * '카테고리를 틍록하는 서비스'에 해당하는 테스트 클래스입니다.
 * 파일명 : 'CateSaveService'
 * 메서드 : save()
 *
 */
@SpringBootTest
@Transactional // 테스트 후 데이터 비우기
@AutoConfigureMockMvc
@TestPropertySource(locations="classpath:application-test.properties")
public class CategoryRegisterTest {


    @Autowired
    private MockMvc mockMvc;

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
         *  cateCd; : 분류 코드
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
    }

    @Test
    @DisplayName("CateCd 중복 등록시 DuplicateCateCdException 발생 여부")
    void duplicateCateCdTest() {
        // 테스트 전 분류 등록
        saveService.save(categoryForm);

        assertThrows(DuplicateCateCdException.class, () -> {
            // 중복 분류로 등록
            saveService.save(categoryForm);
        });
    }

    /**
     * 통합 테스트
     */
    @Test
    @DisplayName("성공적으로 등록완료되면 /admin/category로 이동")
    void saveSuccessRedirectTest() throws Exception {
        mockMvc.perform(post("/admin/category")
                .param("cateCd", categoryForm.getCateCd())
                .param("cateNm", categoryForm.getCateNm())
                .param("location", categoryForm.getLocation())
                .param("use", String.valueOf(categoryForm.isUse())).with(csrf()))
                .andExpect(redirectedUrl("/admin/category"));
    }

    @Test
    @DisplayName("필수 항목 누락시(cateCd, cateNm, location) Bean Validation 검증 체크")
    void requiredCheckResponseTest() throws Exception {
       String body = mockMvc.perform(post("/admin/category").with(csrf()))
                        .andReturn().getResponse().getContentAsString();

       // 분류코드 검증(cateCd)
       assertTrue(body.contains("분류코드"));

       // 분류명 검증(cateNm)
        assertTrue(body.contains("분류명"));

        // 노출위치 코드(location) 검증
        assertTrue(body.contains("노출위치 코드"));
    }

    @Test
    @DisplayName("분류 코드 중복 등록 Bean Validation 검증 체크")
    void duplicateCateCdCheckResponseTest() throws Exception {
        saveService.save(categoryForm);

        String body = mockMvc.perform(post("/admin/category")
                        .param("cateCd", categoryForm.getCateCd())
                        .param("cateNm", categoryForm.getCateNm())
                        .param("location", categoryForm.getLocation())
                        .param("use", String.valueOf(categoryForm.isUse())).with(csrf()))
                .andReturn().getResponse().getContentAsString();
        assertTrue(body.contains("이미 등록된 분류코드"));
    }
}
