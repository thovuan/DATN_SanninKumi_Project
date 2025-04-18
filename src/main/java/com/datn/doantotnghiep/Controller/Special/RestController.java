package com.datn.doantotnghiep.Controller.Special;

import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    @RequestMapping({"/", "/home"})
    public String HomePage() {
        return "/Home/homePage";
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

}
