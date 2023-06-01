package org.study.models.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.study.entities.FileInfo;
import org.study.repositories.FileInfoRepository;

@Service
@RequiredArgsConstructor
public class FileInfoService {

    private final FileInfoRepository repository;
    private final FileInfoSaveService saveService;

    public FileInfo get(Long fileNo) {

        FileInfo fileInfo = repository.findById(fileNo).orElseThrow(FileNotFoundException::new);
        fileInfo.setFileURL(saveService.getFileURL(fileInfo.getFileNo()));
        fileInfo.setFilePath(saveService.getFilePath(fileInfo.getFileNo()));

        return fileInfo;
    }

}
