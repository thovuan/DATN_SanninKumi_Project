package com.datn.doantotnghiep.Controller;

import com.datn.doantotnghiep.Model.DTO.AuthRequest;
import com.datn.doantotnghiep.Model.DTO.AuthResponse;
import com.datn.doantotnghiep.Model.DTO.RegisterRequest;
import com.datn.doantotnghiep.Model.User;
import com.datn.doantotnghiep.Repository.UserRepository;
import com.datn.doantotnghiep.WebConfig.JwtUtil;
import com.datn.doantotnghiep.WebConfig.PasswordValidator;
import com.datn.doantotnghiep.WebConfig.ValidPassword;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    private JwtUtil jwtUtil;

    // Thời gian sống của cookie (giống với thời gian sống của JWT)
    @Value("${jwt.expiration}")
    private int jwtExpirationMs;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request, HttpServletResponse response) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtil.generateToken(authentication);

            // Tạo cookie chứa JWT
            Cookie jwtCookie = new Cookie("jwt", jwt);
            jwtCookie.setMaxAge(jwtExpirationMs / 1000); // Chuyển từ ms sang giây
            jwtCookie.setHttpOnly(true); // Bảo mật hơn, không cho JS truy cập
            jwtCookie.setPath("/"); // Áp dụng cho toàn bộ website
            jwtCookie.setSecure(true); // Chỉ gửi qua HTTPS (bỏ dòng này nếu bạn đang phát triển local)

            response.addCookie(jwtCookie);

            return ResponseEntity.ok(new AuthResponse("Đăng nhập thành công!", jwt, true));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("Tên đăng nhập hoặc mật khẩu không đúng!", null, false));
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        // Xóa cookie JWT khi logout
        Cookie jwtCookie = new Cookie("jwt", null);
        jwtCookie.setMaxAge(0); // Xóa cookie
        jwtCookie.setHttpOnly(true);
        jwtCookie.setPath("/");
        jwtCookie.setSecure(true); // Bỏ nếu đang phát triển local

        response.addCookie(jwtCookie);

        return ResponseEntity.ok(new AuthResponse("Đăng xuất thành công!", null, false));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request, BindingResult bindingResult) {
        // Kiểm tra lỗi validation
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        // Kiểm tra username đã tồn tại chưa
        if (userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body(
                    new AuthResponse("Tên đăng nhập đã tồn tại!", null, false));
        }

        // Mật khẩu đã được xác thực bởi @ValidPassword
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.getRoles().add("USER");

        userRepository.save(user);

        return ResponseEntity.ok(new AuthResponse("Đăng ký thành công!", null, false));
    }

    @GetMapping("/auth/status")
    public ResponseEntity<?> checkAuthStatus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            Map<String, Object> response = new HashMap<>();
            response.put("message", "Đã xác thực");
            response.put("authenticated", true);
            response.put("username", userDetails.getUsername());
            response.put("roles", userDetails.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList()));

            return ResponseEntity.ok(response);
        } else {
            Map<String, Object> response = new HashMap<>();
            response.put("message", "Chưa xác thực");
            response.put("authenticated", false);

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

    @Getter
    @Setter
    @Data
    public static class ChangePasswordRequest {
        @NotBlank
        private String currentPassword;

        @ValidPassword
        private String newPassword;

        // Getters và setters
    }

    @PostMapping("/change-password")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> changePassword(
            @Valid @RequestBody ChangePasswordRequest request,
            BindingResult bindingResult,
            Authentication authentication) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errors);
        }

        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Kiểm tra mật khẩu hiện tại
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body(
                    new AuthResponse("Mật khẩu hiện tại không đúng", null, false));
        }

        // Cập nhật mật khẩu mới
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);

        return ResponseEntity.ok(
                new AuthResponse("Cập nhật mật khẩu thành công", null, true));
    }
}
