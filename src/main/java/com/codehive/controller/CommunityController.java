package com.codehive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CommunityController {

    @GetMapping("/community/free_post")
    public String freePost() {
        return "community/free_post";
    }
} 