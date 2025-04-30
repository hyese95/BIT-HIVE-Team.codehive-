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

    @GetMapping("/crypto")
    public ResponseEntity<List<NewsDto>> getCryptoNews() {
        try {
            return ResponseEntity.ok(newsService.fetchNewsByKeyword("암호화폐%20AND%20코인"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/finance")
    public ResponseEntity<List<NewsDto>> getFinanceNews() {
        try{
            return ResponseEntity.ok(newsService.fetchNewsByKeyword("금리인상%20AND%20금리인하"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/global")
    public ResponseEntity<List<NewsDto>> getGlobalNews() {
        try {
            return ResponseEntity.ok(newsService.fetchNewsByKeyword("글로벌증시%20AND%20나스닥"));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}