package com.example.codehive.controller;

import com.example.codehive.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/trade")
@AllArgsConstructor
public class PriceRestController {
    private final PriceService priceService;

    // 요청 파라미터로 받은 markets 리스트에 대해 실시간 가격 조회
    @GetMapping("/coin_prices")
    public ResponseEntity<Map<String, Double>> getCoinPrices(@RequestParam List<String> markets) {
        Map<String, Double> priceMap = new HashMap<>();
        for (String market : markets) {
            double price = priceService.getCoinPrice(market);
            priceMap.put(market, price);
        }
        return ResponseEntity.ok(priceMap);
    }
}