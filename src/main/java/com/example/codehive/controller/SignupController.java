package com.example.codehive.controller;

import com.example.codehive.model.UsersModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {
    @GetMapping("/signup.do")
    public String signup() {
        return "signup";
    }
    @PostMapping("/signup.do")
    public String signup(@ModelAttribute UsersModel usersModel) {
        return "signup";
    }
}

