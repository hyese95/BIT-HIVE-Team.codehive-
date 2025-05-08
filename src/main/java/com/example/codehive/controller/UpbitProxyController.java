package com.example.codehive.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/proxy/upbit")
public class UpbitProxyController {

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/ticker/all")
    public ResponseEntity<String> getAllTickers(@RequestParam(required = false) String quote_currencies) {
        String url = "https://api.upbit.com/v1/ticker/all";
        if (quote_currencies != null && !quote_currencies.isEmpty()) {
            url += "?quote_currencies=" + quote_currencies;
        }

        return restTemplate.getForEntity(url, String.class);
    }

    @GetMapping("/market/all")
    public ResponseEntity<String> getAllMarkets(@RequestParam(required = false) boolean is_details) {
        String url = "https://api.upbit.com/v1/market/all";
        if (is_details) {
            url += "?is_details=true";
        }

        return restTemplate.getForEntity(url, String.class);
    }

    @GetMapping("/ticker")
    public ResponseEntity<String> getTicker(@RequestParam(required = false) String markets) {
        String url = "https://api.upbit.com/v1/ticker?markets=" + markets;
        return restTemplate.getForEntity(url, String.class);
    }
    @GetMapping("/orderbook")
    public ResponseEntity<String> getOrderBook(@RequestParam(required = false) String markets) {
        String url = "https://api.upbit.com/v1/orderbook?markets=" + markets;
        return restTemplate.getForEntity(url, String.class);
    }

    @GetMapping("/candles/seconds")
    public ResponseEntity<String> getCandles(@RequestParam(required = false) String markets, @RequestParam(required = false) String count) {
        String url = "https://api.upbit.com/v1/candles/seconds?markets=" + markets + "&count=" + count;
        return restTemplate.getForEntity(url, String.class);
    }
    @GetMapping("/candles/days")
    public ResponseEntity<String> getcandlesdays(@RequestParam(required = false) String markets, @RequestParam(required = false) String count) {
        String url= "https://api.upbit.com/v1/candles/days?markets=" + markets + "&count=" + count;
        return restTemplate.getForEntity(url, String.class);
    }

    @GetMapping("/trades/ticks")
    public ResponseEntity<String> getTrades(@RequestParam(required = false) String markets, @RequestParam(required = false) String count) {
        String url="https://api.upbit.com/v1/trades/ticks?markets=" + markets + "&count=" + count;
        return restTemplate.getForEntity(url, String.class);
    }


}