package org.study.controllers.files;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.study.entities.FileInfo;
import org.study.models.file.FileInfoService;

@Controller
@RequiredArgsConstructor
public class FileViewController {

    private final FileInfoService infoService;

    @GetMapping("/file/view/{fileNo}")
    public String view(@PathVariable Long fileNo, Model model) {

        try {
            FileInfo fileInfo = infoService.get(fileNo);
            model.addAttribute("fileURL", fileInfo.getFileURL());
        } catch (Exception e) {
            model.addAttribute("script", "alert('" + e.getMessage() + "');mozip.popup.close();");
            return "commons/execute_script";
        }

        return "file/view";
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 8599cd278c32cbb189dc5157662e7182b7915bbe
