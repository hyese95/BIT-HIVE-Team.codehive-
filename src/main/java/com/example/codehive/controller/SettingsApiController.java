package com.example.codehive.controller;

import com.example.codehive.entity.Faq;
import com.example.codehive.service.FaqService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/settings")
@AllArgsConstructor
public class SettingsApiController {

    FaqService faqService;

    @GetMapping("/faq")
    public List<Faq> readFaq(){
        List<Faq> faqList=null;
        faqList=faqService.readAll();

        return faqList;
    }
}
