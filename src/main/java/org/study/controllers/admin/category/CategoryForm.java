package org.study.controllers.admin.category;


import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.study.entities.Category;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class CategoryForm {

    @NotBlank
    private String cateCd;

    private boolean use;

    @NotBlank
    private String location;

    @NotBlank
    private String cateNm;

    private Integer listOrder;

    private String parentCateCd;

    /**
     * Category Entity 변환
     * @param categoryForm
     * @return
     */
    public static Category of (CategoryForm categoryForm) {
        return new ModelMapper().map(categoryForm, Category.class);
    }
}
