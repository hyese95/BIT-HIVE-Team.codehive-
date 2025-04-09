package com.example.codehive.service;

import com.example.codehive.dto.CoinNameDto;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CoinNameService {
    private final RestTemplate restTemplate;

    public CoinNameService() {
        this.restTemplate = new RestTemplate();
    }
    public List<CoinNameDto> getCoinNames() {
        String url = "https://api.upbit.com/v1/market/all?isDetails=true";
        try {
            ResponseEntity<List<CoinNameDto>> resp = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<CoinNameDto>>() {
                    }
            );
            return resp.getBody();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }
    public Map<String, String> getMarketToKoreanNameMap(){
        return getCoinNames().stream()
                .collect(Collectors.toMap(CoinNameDto::getMarket, CoinNameDto::getKoreanName));
    }
}
