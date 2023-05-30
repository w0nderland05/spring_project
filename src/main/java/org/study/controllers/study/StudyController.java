package org.study.controllers.study;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.study.models.studies.StudySaveService;


@Controller("frontStudyController")
@RequestMapping("/study")
@RequiredArgsConstructor
public class StudyController {

    private final StudySaveService saveService;
    @GetMapping
    public String register(@ModelAttribute StudyForm studyForm) {

        return "front/study2/register";
    }

    @GetMapping("/{studyNo}/edit")
    public String update(@PathVariable Long studyNo, Model model) {

        return "front/study2/update";
    }

    /**
     * 스터디 등록, 수정
     *
     * @return
     */
    @PostMapping
    public String save(@Valid StudyForm studyForm, Errors errors) {

        Long studyNo = studyForm.getStudyNo();
        if (errors.hasErrors()) {
            String tpl = studyNo == null ? "register" : "update";
            return "study/" + tpl;
        }

        saveService.save(studyForm);

        return "redirect:/study/view/" + studyForm.getStudyNo();
    }

    @GetMapping("/view/{studyNo}")
    public String view(@PathVariable Long studyNo) {

        return "study2/view";
    }
}
