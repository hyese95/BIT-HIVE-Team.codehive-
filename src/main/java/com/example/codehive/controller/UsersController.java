package com.example.codehive.controller;

import com.example.codehive.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {
    private UsersService usersService;
    @GetMapping("/read.do")
    public String readAll(Model model) {
        model.addAttribute("users", usersService.readAll());
        return "users/read";
    }
    @GetMapping("/detail.do")
    public String detail(Model model, String userNo) {
        model.addAttribute("user", usersService.read(userNo));
        return "users/detail";
    }
}
