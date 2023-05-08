package org.study.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(indexes = {
        @Index(name = "idx_fileInfo_gid", columnList = "gid, createdAt"), // 방향성 createdAt
        @Index(name = "idx_fileInfo_gid_location", columnList = "gid, location, createdAt")  // 방향성 createdAt
})
public class FileInfo extends BaseEntity{

    @Id @GeneratedValue
    private Long fileNo; // 실제 서버 fileNo.확장자

    @Column(length = 60, nullable = false)
    private String gid; // 그룹 id - location과 함께 조회 용도

    @Column(length = 60)
    private String location; // 그룹내 위치

    @Column(length = 100,nullable = false)
    private String fileName; // 원본 파일 이름

    @Column(length = 60, nullable = false)
    private String contentType; // 파일 형식

    private boolean success; // 그룹 작업 완료 여부

    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩
    @JoinColumn(name="userNo")
    private User user; // 파일을 올린 사용자

/** 나중에 다시 설정 ? 없어도 될지도 몰라용

    // 서버에서 바로 확인할 수 있는 URL ( 파일을 웹페이지에서 확인할 수 있는 URL )
    public String getFileURL() {

    }

    // 실제 파일 업로드 경로
    public String getFilePath() {

    }
*/
}
