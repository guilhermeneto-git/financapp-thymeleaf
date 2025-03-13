package com.gneto.financapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "/authorization/login";
    }

    // add request mapping for /access-denied
    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "/authorization/access-denied";
    }

}
