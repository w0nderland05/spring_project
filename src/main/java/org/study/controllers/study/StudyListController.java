package org.study.controllers.study;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.study.commons.Pagination;
import org.study.entities.Category;
import org.study.entities.Studies;
import org.study.models.category.CateListService;
import org.study.models.studies.ListService;

import java.util.List;

@Controller("frontStudyListController")
@RequestMapping("/study/list")
@RequiredArgsConstructor
public class StudyListController {

    private final CateListService cateListService;
    private final ListService listService;

    @GetMapping
    public String list(@ModelAttribute  StudySearch search, Model model) {
        commonProcess(model);

        Page<Studies> data = listService.gets(search);

        model.addAttribute("items", data.getContent());

        String url = "/study/list";
        Pagination<Studies> pagination = new Pagination<>(data, url);
        model.addAttribute("pagination", pagination);

        return "front/study2/list";
    }

    private void commonProcess(Model model) {
       model.addAttribute("addCss", new String[] { "study/list"} );
       model.addAttribute("addScript", new String[] { "study/list" });

       List<Category> categories = cateListService.getAll();
       model.addAttribute("categories", categories);
       model.addAttribute("menuCode", "study_list");
       model.addAttribute("pageTitle", "스터디 함께해요");
    }
}
