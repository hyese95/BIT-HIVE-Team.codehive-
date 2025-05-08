package com.example.codehive.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/proxy/binance")
public class BinanceproxyController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/v3/ticker/price")
    public ResponseEntity<String> getTicker(@RequestParam(required = false) String markets) {
        String url = "https://api.binance.com/api/v3/ticker/price";
        return restTemplate.getForEntity(url, String.class);
    }
}
