package com.datn.doantotnghiep.Controller.Special;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CameraController {

    @GetMapping("/cam")
    public String cam(Model model) {
        return "/CameraPage/cameraform";

    }
}
