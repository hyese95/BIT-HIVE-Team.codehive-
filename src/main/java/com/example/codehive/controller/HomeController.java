package com.example.codehive.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
//    @GetMapping(
//            value = {
//                    "/",                           // 루트
//                    "/{path:[^\\.]}",           // 1단 경로  ( /community )
//                    "/**/{path:[^\\.]}"         // 다단 경로 ( /trade/123 )
//            },
//            produces = MediaType.TEXT_HTML_VALUE
//    )
//    public String fowardSpa() {
//        return "forward:/index.html";
//    }

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
