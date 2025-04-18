package com.datn.doantotnghiep.Controller.Special;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SpeechTextController {
    @PostMapping("/submit-text")
    public String receiveText(@RequestBody TextPayload payload) throws JsonProcessingException {
        String original = payload.getText();
        System.out.println("Đã nhận văn bản tiếng Nhật: " + original);
        // Tại đây bạn có thể lưu vào DB hoặc xử lý khác
//        return "サーバーが受け取りました: " + payload.getText();

        // ⚙️ Xử lý văn bản tại đây (ví dụ: sửa lỗi ngữ pháp, dịch, đánh giá phát âm...)
        // Ví dụ đơn giản: phản hồi lại với chữ in đậm

        // Có thể bổ sung thêm phản hồi như sửa lỗi, ghi chú, dịch nghĩa,...
        return "<strong>サーバーが受信した内容:</strong><br>" + original;

//        RestTemplate restTemplate = new RestTemplate();
//        String pythonApiUrl = "http://localhost:5000/analyze";
//
//        // Gửi đoạn text sang Python
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        String json = "{\"text\": \"" + payload.getText() + "\"}";
//        HttpEntity<String> request = new HttpEntity<>(json, headers);
//
//        ResponseEntity<String> response = restTemplate.postForEntity(pythonApiUrl, request, String.class);
//
//        ObjectMapper mapper = new ObjectMapper();
//        JsonNode result = mapper.readTree(response.getBody());
//
//        StringBuilder html = new StringBuilder();
//        html.append("<strong>📜 Văn bản gốc:</strong><br>").append(result.get("original").asText()).append("<br><br>");
//        html.append("<strong>🔍 Phân tích ngữ pháp:</strong><ul>");
//        for (JsonNode issue : result.get("grammar_issues")) {
//            html.append("<li>").append(issue.asText()).append("</li>");
//        }
//        html.append("</ul><br>");
//        html.append("<strong>🌐 Dịch tiếng Việt:</strong><br>").append(result.get("translation").asText());
//
//        return html.toString();
    }

    // Lớp để nhận JSON
    @Setter
    @Getter
    public static class TextPayload {
        private String text;

    }
}
