package org.study.models.studies;

import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.study.commons.UserUtils;
import org.study.commons.constants.Status;
import org.study.commons.validators.NotAllowedException;
import org.study.commons.validators.StudyNotFoundException;
import org.study.controllers.study.StudySearch;
import org.study.entities.Category;
import org.study.entities.FileInfo;
import org.study.entities.QStudies;
import org.study.entities.Studies;
import org.study.models.file.FileListService;
import org.study.repositories.CategoryRepository;
import org.study.repositories.StudiesRepository;

import java.util.List;

import static org.springframework.data.domain.Sort.Order.desc;

@Service
@RequiredArgsConstructor
public class InfoService {

    private final CategoryRepository categoryRepository;
    private final StudiesRepository studiesRepository;
    private final FileListService fileListService;

    private final UserUtils userUtils;


    public Studies get(Long studyNo) {
        return get(studyNo, false);
    }

    /**
     * 스터디 번호로 조회
     * @param studyNo
     * @param isMine
     * @return
     */
    public Studies get(Long studyNo, boolean isMine) {

        Studies study = studiesRepository.findById(studyNo).orElseThrow(StudyNotFoundException::new);
        String gid = study.getGid();

        /** 본인이 작성한 스터디 이거나 관리자인 경우는 미승인 전이라도 허용처리 S */
        if (isMine && userUtils.isLogin() && !userUtils.isAdmin() && study.getUser() != null)  {
            String userEmail = userUtils.getUser().getUserEmail();
            String userEmail2 = study.getUser().getUserEmail();
            if (!userEmail.equals(userEmail2)) {
                throw new NotAllowedException();
            }
        }
        /** 본인이 작성한 스터디 이거나 관리자인 경우만 허용처리 E */

        /** 업로드한 파일 목록 S */
        List<FileInfo> mainImages = fileListService.getAll(gid, "main");
        List<FileInfo> editorFiles = fileListService.getAll(gid, "editor");
        study.setEditorFiles(editorFiles);
        study.setMainImages(mainImages);
        /** 업로드한 파일 목록 E */

        /** 분류명 조회 S */
        String cateCd = study.getCateCd();
        Category category = categoryRepository.findById(cateCd).orElse(null);
        study.setCategory(category);
        /** 분류명 조회 E */

        return study;
    }
}
