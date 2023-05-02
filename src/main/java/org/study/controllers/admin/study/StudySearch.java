package org.study.controllers.admin.study;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudySearch {

    private int page = 1;
    private int limit = 20;
    private Long StudyCode;

    private String sopt;//선택옵션

    private String skey;//키워드

    private String[] approveStatus;

    private String[] regionType;


}
