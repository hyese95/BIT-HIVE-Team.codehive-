package com.example.codehive.service;

import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CoinKoreanNameService {
    private final RestTemplate restTemplate = new RestTemplate();

    public Map<String, String> getCoinKoreanName() {
        Map<String, String> map = new HashMap<>();
        try {
            String url = "https://api.upbit.com/v1/market/all?is_details=true";
            ResponseEntity<List<Map<String, Object>>> resp = restTemplate.exchange(
                    url, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Map<String, Object>>>() {
                    });
            List<Map<String, Object>> data = resp.getBody();

            if (data != null) {
                for (Map<String, Object> item : data){
                    String market = (String) item.get("market");
                    String koreanName = (String) item.get("korean_name");
                    if (market.startsWith("KRW-")){
                        map.put(market, koreanName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
