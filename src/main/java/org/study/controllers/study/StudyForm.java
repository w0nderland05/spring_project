package org.study.controllers.study;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.study.commons.constants.RegionType;
import org.study.commons.constants.Status;
import org.study.entities.FileInfo;
import org.study.entities.User;

import java.util.List;
import java.util.UUID;

@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class StudyForm {
    private Long studyNo;

    private String gid = UUID.randomUUID().toString();

    @NotBlank
    private String studyCd; // 스터디 코드

    @NotBlank
    private String cateCd; // 스터디 분류
    
    private String status = Status.APPLY.toString(); // 승인상태

    private String selMaxMember = "all";
    private long maxMember = 0; // 최대 신청횟수
    private int numOfWeek = 1; // 스터디 주당 횟수 - 기본 1회

    @NotBlank
    private String regionType = RegionType.ONLINE.toString();

    private String sido;
    private String sigugun;

    @NotBlank
    private String simpleIntro; // 한줄 소개글

    @NotBlank
    private String introduction; // 소개글

    private List<FileInfo> mainImages;
    private List<FileInfo> editorFiles;

    @ManyToOne
    @JoinColumn(name="userNo")
    private User user;
}
