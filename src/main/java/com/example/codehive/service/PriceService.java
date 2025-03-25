package com.example.codehive.service;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PriceService {
    private final RestTemplate restTemplate;

    public PriceService() {
        this.restTemplate = new RestTemplate();
    }
    public double getCoinPrice(String market) {
        try {
            // Upbit API 호출 URL (실제 API 주소 사용)
            String url = "https://api.upbit.com/v1/ticker?markets=" + market;
            ResponseEntity<List<Map<String, Object>>> response =
                    restTemplate.exchange(url, HttpMethod.GET, null,
                            new ParameterizedTypeReference<List<Map<String, Object>>>() {});
            List<Map<String, Object>> result = response.getBody();
            if (result != null && !result.isEmpty()) {
                Object tradePriceObj = result.get(0).get("trade_price");
                if (tradePriceObj != null) {
                    return ((Number) tradePriceObj).doubleValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0.0; // 오류 발생 시 기본값 0.0 반환
    }
}