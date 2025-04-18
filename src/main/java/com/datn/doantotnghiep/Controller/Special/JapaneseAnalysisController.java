package com.datn.doantotnghiep.Controller.Special;

import com.datn.datnai.Service.JapaneseAnalysisService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class JapaneseAnalysisController {

    private final JapaneseAnalysisService service;

    public JapaneseAnalysisController(JapaneseAnalysisService service) {
        this.service = service;
    }

    @PostMapping("/japanese-analysis")
    public Map<String, Object> receiveAnalysis(@RequestBody Map<String, Object> data) {
        System.out.println("Received data: " + data);

        // Lưu dữ liệu vào service
        if (data.containsKey("tokens")) {
            service.saveAnalysis((List<Map<String, Object>>) data.get("tokens"));
        }

        return data; // Trả về dữ liệu để kiểm tra
    }
}
