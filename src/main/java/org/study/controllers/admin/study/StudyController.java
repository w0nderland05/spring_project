package org.study.controllers.admin.study;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/admin/study")
public class StudyController {

    /**
    @Autowired
    private StudyListService listService;

    @Autowired
    private StudyRepository studyRepository;

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserUtils userUtils;

    @Autowired
    private StudySaveService saveService;

    /**
     * <스터디관리> 클릭시 나오는 페이지
     * ==스터디 목록
     *
     * @return
     */
    /*
    @GetMapping
    public String index(Model model, StudySearch studySearch) {
        String url = request.getContextPath() + "/admin/study";
        studySearch.setApproveStatus(new String[]{"APPROVE", "DISAPPROVE"});
        Study study = new Study();
        study.setUser(userUtils.getEntity());
        //Page<Study> data = listService.gets(studySearch); // 조회
        Pagination<Study> pagination = new Pagination<>(data, url);
        model.addAttribute("studies", data.getContent()); //조회된 엔티티 클래스를 직접 커맨드로 사용
        model.addAttribute("studySearch", studySearch);
        model.addAttribute("pagination", pagination);


        return "admin/study/index";
    }


    /**
     * 스터디 개설 신청 관리
     *
     * @return
     */
    /**
    @GetMapping("/approvals")
    public String approvals(Model model, StudySearch studySearch) {
        String url = request.getContextPath() + "/admin/study/approvals";
        studySearch.setApproveStatus(new String[]{"APPLY"});
        Study study = new Study();
        study.setUser(userUtils.getEntity());
        Page<Study> data = listService.gets(studySearch);
        Pagination<Study> pagination = new Pagination<>(data,url);
        data.getContent().stream().forEach(System.out::println);
        model.addAttribute("studies", data.getContent());
        model.addAttribute("pagination", pagination);

        return "admin/study/approvals";
    }

    /**
     * 관리자 승인/미승인 페이지
     * => 개설신청관리에서 '처리하기'버튼 클릭시 나오는 관리자 페이지
     * ( approve.html에서 th:action="@{/admin/study/approve}" 갈 예정
     *
     * @return
     */
    /**
    @GetMapping("/approve/update/{studyCode}")
    public String approve(@PathVariable Long studyCode, Model model, HttpServletResponse response) {
        model.addAttribute("sidoList", Areas.sido);
        model.addAttribute("mode", "update");
        try {
            StudyConfig studyConfig = listService.get(studyCode);
            studyConfig.setUser(userUtils.getEntity());
            model.addAttribute("studyConfig", studyConfig);
        } catch (CommonException e) {
            response.setStatus(e.getStatus().value());
            model.addAttribute("script", "alert('" + e.getMessage() + "');history.back();");
            return "common/execute_script";
        }
        return "admin/study/approve";
    }

    @PostMapping("/save")
    public String studySave(@Valid StudyConfig studyConfig, Errors errors, Model model) {

        model.addAttribute("sidoList", Areas.sido);
        String addressDo = studyConfig.getAddressDo();
        if (addressDo != null) {
            String[] siguguns = Areas.getSigugun(addressDo);
            model.addAttribute("siguguns", siguguns);
        }
        try {
            listService.get(studyConfig.getStudyCode());
        } catch (RuntimeException e) {
            errors.reject("studySaveError", e.getMessage());
        }

        String mode = studyConfig.getMode();

        if (errors.hasErrors()) {
            System.out.println(errors);
            String tpl = "admin/study/approve/";
            if (mode != null && mode.equals("update")) {
                tpl += "update/"+"{"+studyConfig.getStudyCode() +"}";
            } else {
                throw new StudyNotFoundException(); //관리자에서는 스터디등록 불가하여 수정만 되도록
            }

            return tpl;
        }
        saveService.save(studyConfig);
        return "redirect:/admin/study/approve/update/{studyCode}";// 수정등록후, 승인 미승인 처리하도록 동일 페이지로 이동
    }

    */
}
