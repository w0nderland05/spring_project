package org.study.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.study.entities.FileInfo;

import java.util.List;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long>, QuerydslPredicateExecutor<FileInfo> {
    FileInfo findByFileNo(Long fileNo);

    List<FileInfo> findByGidOrderByCreatedAtAsc(String gid);
}
