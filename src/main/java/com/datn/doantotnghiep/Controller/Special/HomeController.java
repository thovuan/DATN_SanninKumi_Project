package com.datn.doantotnghiep.Controller.Special;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home"})
    public String HomePage() {
        return "/Home/homePage";
    }

    @GetMapping("/next")
    public String NextPage() {
        return "/Next/next";
    }
}
