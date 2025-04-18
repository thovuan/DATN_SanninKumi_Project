package com.datn.doantotnghiep.Controller.Special;

import com.datn.datnai.Service.JapaneseAnalysisService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class MessenjiController {

    private final JapaneseAnalysisService service;

    public MessenjiController(JapaneseAnalysisService service) {
        this.service = service;
    }

    @GetMapping("/analysis")
    public String showAnalysis(Model model) {
        List<Map<String, Object>> tokens = service.getTokens();
        model.addAttribute("tokens", tokens);
        return "/Messenji/TESTONE";
    }

//    @GetMapping("/analysis")
//    public String showAnalysis(Model model) {
//        List<Map<String, Object>> tokens = service.getTokens();
//        model.addAttribute("tokens", tokens);
//        return "analysis"; // Hiển thị trang HTML
//    }
}
