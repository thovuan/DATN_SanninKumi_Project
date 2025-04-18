package com.datn.doantotnghiep.Controller;
import com.datn.doantotnghiep.Model.User;
import com.datn.doantotnghiep.Service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class HomeController {



    @Autowired
    private HttpSession httpSession;

    @GetMapping("/home")
    @PreAuthorize("hasRole('USER')")
    public String homePage () {
        return "/home";
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("user", new User());
        return "/Shin_LoginPage";
    }



    @GetMapping("/dashboard")
    public String dashboardPage() {
        return "dashboard";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "profile";
    }
}
