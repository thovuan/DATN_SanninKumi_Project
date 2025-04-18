package com.datn.doantotnghiep.Controller.Special;

import com.datn.datnai.Model.Users;
import com.datn.datnai.Service.UsersService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
public class AccountController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/profile")
    public String profile(OAuth2AuthenticationToken token, Model model) {
        model.addAttribute("sub", token.getPrincipal().getAttribute("sub"));
        model.addAttribute("name", token.getPrincipal().getAttribute("name"));
        model.addAttribute("email", token.getPrincipal().getAttribute("email"));
        model.addAttribute("photo", token.getPrincipal().getAttribute("picture"));

        String email = token.getPrincipal().getAttribute("email");

        LocalDateTime dt = LocalDateTime.now();
        Users user = new Users();
        user.setUsername("User1");
        user.setEmail(token.getPrincipal().getAttribute("email"));
        user.setName(token.getPrincipal().getAttribute("name"));
        user.setCreate_at(dt);
        user.setUpdate_at(dt);
        //user.setPassword("");
        user.setUsername(email.split("@")[0]);
        user.setProvider(Users.Provider.GOOGLE);
        user.setProvider_id(token.getPrincipal().getAttribute("sub"));
        user.setAvatar_url(token.getPrincipal().getAttribute("picture"));

        try {
            usersService.RegisterUser(user);
            return "/ZenAccountPage/ProfilePage";

        } catch (Exception ex) {
            return "/ErrorPage";
        }

        //return "/ZenAccountPage/ProfilePage";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new Users());
        return "/ZenAccountPage/Shin_LoginPage";
    }

    @PostMapping("/login")
    public String login(@RequestBody @Valid @ModelAttribute("user") Users account, Model model) {
        Users user = usersService.FindByUser(account.getUsername());
        if (user != null) {
            if (user.getPassword().equals(account.getPassword())) {
                httpSession.setAttribute("user", user);

//                // ⚠️ Thiết lập Authentication cho Spring Security
//                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
//                        user.getUsername(), null, List.of() // cấp quyền nếu có
//                );
//                SecurityContextHolder.getContext().setAuthentication(auth);
                return "redirect:/home";
            }
            else {
                model.addAttribute("errorMessage", "Tai khoan hoac mat khau khong dung");
                return "/ZenAccountPage/Shin_LoginPage";
            }
        } else {
            model.addAttribute("errorMessage", "Tai khoan hoac mat khau khong dung");
            return "/ZenAccountPage/Shin_LoginPage";
        }
    }

    @GetMapping("/oauth2/loginSuccess")
    public String handleOAuth2Login(OAuth2AuthenticationToken authentication) {
        Map<String, Object> attributes = authentication.getPrincipal().getAttributes();

        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");

        // Lấy user từ DB nếu có, hoặc tạo mới
        Users user = usersService.FindByUser(email);
        if (user == null) {
            user = new Users();
            user.setUsername(email);
            user.setName(name);
            user.setProvider(Users.Provider.GOOGLE);
            user = usersService.save(user);
        } else {
            // Nếu user đã tồn tại nhưng chưa có provider thì cập nhật (optional)
            if (user.getProvider() == null) {
                user.setProvider(Users.Provider.GOOGLE);
                user = usersService.save(user);
            }
        }

        // Lưu vào session
        httpSession.setAttribute("user", user);

        // Thiết lập Authentication (nếu muốn dùng Spring Security bảo vệ session)
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                user.getUsername(), null, List.of()
        );
        SecurityContextHolder.getContext().setAuthentication(auth);

        return "redirect:/home";
    }


    @GetMapping("/Identity/User/NewUser")
    public String GuestNewUserForm(Model model){
        Users guest = new Users();
        model.addAttribute("guest", guest);
        return "/ZenAccountPage/RegistrationPage";
    }

    @PostMapping("/Identity/User/Register")
    public String Register(@Valid @ModelAttribute("guest") Users account, Model model) {
        try {
            usersService.RegisterUser(account);
            return "redirect:/Identity/User/LoginForm";
        } catch (Exception ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "/ErrorPage/Error";
        }

    }
}
