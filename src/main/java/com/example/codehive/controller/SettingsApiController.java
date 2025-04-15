package com.example.codehive.controller;

import com.example.codehive.entity.Guide;
import com.example.codehive.service.GuideService;
import com.example.codehive.entity.Faq;
import com.example.codehive.service.FaqService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/settings")
@AllArgsConstructor
public class SettingsApiController {
    GuideService guideService;
    @GetMapping("/notice")
    public List<Guide> readNotice() {
        List<Guide> noticeList = null;
        noticeList = guideService.readAll();
        return noticeList;
    }
    FaqService faqService;
    @GetMapping("/faq")
    public List<Faq> readFaq(){
        List<Faq> faqList=null;
        faqList=faqService.readAll();
        return faqList;
    }
}
