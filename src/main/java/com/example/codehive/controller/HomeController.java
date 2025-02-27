package com.example.codehive.controller;

import com.example.codehive.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import javax.sql.DataSource;

@Controller

public class HomeController {
    @Autowired
    private UsersMapper usersMapper;
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("user", usersMapper.findAll());
        return "index";
    }
}
