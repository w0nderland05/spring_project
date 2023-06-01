package org.study.models.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.study.entities.FileInfo;
import org.study.repositories.FileInfoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileUpdateSuccessService {

    private final FileInfoRepository repository;
    private final FileInfoService infoService;

    public void process(String gid) {
        List<FileInfo> files = repository.findByGidOrderByCreatedAtAsc(gid);

        if (files == null || files.size() == 0) {
            throw new FileNotFoundException();
        }

        files.stream().forEach(file -> file.setSuccess(true));

        repository.flush();

    }
}