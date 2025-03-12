package com.example.codehive.controller;

import com.example.codehive.entity.Faq;
import com.example.codehive.entity.Guide;
import com.example.codehive.service.FaqService;
import com.example.codehive.service.GuideService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/setting")
@AllArgsConstructor
public class SettingController {
    private final GuideService guideService;
    private final FaqService faqService;

    @GetMapping("/main.do")
    public String main() {
        return "setting/main";
    }
    @GetMapping("/support/support_main.do")
    public String supportMain() {
        return "setting/support/support_main";
    }
    @GetMapping("/support/notice/notice.do")
    public String notice(Model model) {
        List<Guide> guides=guideService.readAll();
        model.addAttribute("guides", guides);
        return "setting/support/notice/notice";
    }
    @GetMapping("/support/notice/notice_detail.do")
    public String noticeDetail(Model model, @RequestParam long guideNo) {
        Guide guide=guideService.readById(guideNo);
        model.addAttribute("guide", guide);
        return "setting/support/notice/notice_detail";
    }
    @GetMapping("/support/faq/faq.do")
    public String faq(Model model) {
        List<Faq> faq=faqService.readAll();
        model.addAttribute("faq", faq);
        return "setting/support/faq/faq";
    }
    @GetMapping("/support/faq/faq_detail.do")
    public String faqDetail(Model model, @RequestParam long faqNo) {
        Faq faq=faqService.readById(faqNo);
        model.addAttribute("faq", faq);
        return "setting/support/faq/faq_detail";
    }
    @GetMapping("/support/notice/notice_search_result.do")
    public String noticeSearchResult(Model model, @RequestParam String keyword) {
        List<Guide> guides=guideService.readByKeyword(keyword);
        model.addAttribute("guides", guides);
        model.addAttribute("keyword", keyword);
        return "setting/support/notice/notice_search_result";
    }
    @GetMapping("/support/faq/faq_search_result.do")
    public String faqSearchResult(Model model, @RequestParam String keyword) {
        List<Faq> faqs=faqService.readByKeyword(keyword);
        model.addAttribute("faqs", faqs);
        model.addAttribute("keyword", keyword);
        return "setting/support/faq/faq_search_result";
    }
}
