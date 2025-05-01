package com.example.codehive.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/proxy/coingecko")
@CrossOrigin(origins = "http://localhost:5173")
public class CoingeckoProxyController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/coins/list")
    public ResponseEntity<String> getAllCoins() {
        String url="https://api.coingecko.com/api/v3/coins/list";
        try{
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return ResponseEntity.ok(response.getBody());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }



}
