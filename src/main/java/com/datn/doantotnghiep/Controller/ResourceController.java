package com.datn.doantotnghiep.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ResourceController {

    @GetMapping("/public")
    public String publicResource() {
        return "This is a public resource";
    }

    @GetMapping("/protected")
    @PreAuthorize("hasRole('USER')")
    public String protectedResource() {
        return "This is a protected resource";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminResource() {
        return "This is an admin resource";
    }
}
