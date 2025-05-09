package com.example.codehive.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {


//    @GetMapping("/")
@GetMapping({
        "/",                             // ①
        "/{path:[^\\.]+}",               // ②  (점이 없는 1단)
        "/**/{path:[^\\.]+}"             // ③  (점이 없는 다단)
})
    public String home() {
        return "forward:index.html";
    }
}
