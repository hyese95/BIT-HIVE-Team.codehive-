package com.example.codehive.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/news")
public class NewsController {

    @GetMapping("main.do")
    public String main() {
        return "news/main";
    }

    @GetMapping("crypto_news.do")
    public String cryptoNews() {
        return "news/crypto_news";
    }

    @GetMapping("global_stock_news.do")
    public String globalStock() {
        return "news/global_stock_news";
    }
    // 환율/금리
    @GetMapping("forex_rates_news.do")
    public String forexRatesNews() {
        return "news/forex_rates_news";
    }
    // 뉴스 상단 공포 탐욕 지수
    @GetMapping("/nav_menu/fear_greed_index.do")
    public String fearGreedIndex() {
        return "news/nav_menu/fear_greed_index";
    }
    // 뉴스 상단 네비 김프
    @GetMapping("/nav_menu/kimchi_premium.do")
    public String kimchiPremium() {
        return "news/nav_menu/kimchi_premium";
    }
}
