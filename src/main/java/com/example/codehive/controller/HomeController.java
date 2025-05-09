package com.example.codehive.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {


@GetMapping("/")
    public String home() {
        return "forward:index.html";
    }
}
