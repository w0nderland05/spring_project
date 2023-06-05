package org.study.restcontrollers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.study.commons.Areas;

@RestController
@RequestMapping("/ajax")
public class AjaxController {
    /**
     * 시구군 조회
     * @param sido
     * @return
     */
    @GetMapping("/sigugun/{sido}")
    public String[] sigugun(@PathVariable String sido) {
        return Areas.getSigugun(sido);
    }

    @GetMapping("/sido")
    public String[] sido() {
        return Areas.sido;
    }
}
