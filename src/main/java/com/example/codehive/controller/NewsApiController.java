package com.example.codehive.controller;

import com.example.codehive.dto.NewsDto;
import com.example.codehive.service.NewsService;
import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/news")
@CrossOrigin(origins = "http://localhost:5173")
public class NewsApiController {

    private final NewsService newsService;

    @GetMapping
    public ResponseEntity<List<NewsDto>> getNewsByCategory(@RequestParam(required = false) String category) {
        try {
            String keyword;
            switch (category) {
                case "crypto": keyword = "암호화폐 AND 코인"; break;
                case "finance": keyword = "금리 AND 환율"; break;
                case "global": keyword = "미증시 AND 나스닥"; break;
                default: keyword = "암호화폐"; // fallback or throw error
            }
            return ResponseEntity.ok(newsService.fetchNewsByKeyword(keyword));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/yesterday")
    public ResponseEntity<List<NewsDto>> getYesterdayNews() {
        try {
            return ResponseEntity.ok(newsService.fetchNewsForYesterday());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}