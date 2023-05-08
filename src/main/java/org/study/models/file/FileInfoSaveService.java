package org.study.models.file;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.study.entities.FileInfo;
import org.study.entities.User;
import org.study.models.user.UserInfo;
import org.study.repositories.FileInfoRepository;
import org.study.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileInfoSaveService {

    private final FileInfoRepository repository;
    private final UserRepository userRepository;
    private final HttpSession session;

    private final EntityManager em; // 안전하게 사용하기 위해 분리!

    public FileInfo save(MultipartFile file, String gid, String location) {
        List<FileInfo> items = save(new MultipartFile[] {file}, gid, location);

        return items.size() > 0 ? items.get(0):null;
    }
    
    public List<FileInfo> save(MultipartFile[] files) {
        return save(files, null);
    }

    public List<FileInfo> save(MultipartFile[] files, String gid) {

        return save(files, gid, null);
    }

    public List<FileInfo> save(MultipartFile[] files, String gid, String location) {
        // gid( 그룹 ID )가 없는 경우 자동 생성
        gid = gid == null ? UUID.randomUUID().toString() : gid;

        User user = null;
        // 로그인한 회원 정보 조회
        UserInfo userInfo = (UserInfo) session.getAttribute("userInfo");
        if(userInfo != null) {
            user = userRepository.findByUserEmail(userInfo.getUserEmail()); // 이메일로 회원 조회
        }

        // MultipartFile에 있는 파일 정보 넣어주기
        List<FileInfo> items = new ArrayList<>();
        for(MultipartFile file : files) {
            FileInfo item = FileInfo.builder()
                    .fileName(file.getOriginalFilename())
                    .contentType(file.getContentType())
                    .gid(gid)
                    .location(location)
                    .user(user)
                    .build();

            items.add(item);
        }

        items = repository.saveAllAndFlush(items);
        items.stream().forEach(em::detach);
        return items;
    }
}
