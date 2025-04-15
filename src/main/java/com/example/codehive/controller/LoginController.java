package com.example.codehive.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
public class LoginController {
    @GetMapping("/login/login.do")
    public String login() {
        return "login/login";
    }
}
