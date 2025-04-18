package com.datn.doantotnghiep.Controller.Special;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CallSpeechTextController {

    @GetMapping("/PTNN")
    public String CSTC (Model model) {
        return "/DATA/PTNN";
    }
}
