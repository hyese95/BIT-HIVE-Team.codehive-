package com.example.codehive.service;

import com.example.codehive.dto.NewsDto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
    @Value("${my.api.deepsearch-key}")
    private String deepsearchApiKey;

    public List<NewsDto> fetchNewsByKeyword(String keyword) throws Exception {
        String url = "https://api-v2.deepsearch.com/v1/articles?keyword=" +
                URLEncoder.encode(keyword, StandardCharsets.UTF_8) +
                "&api_key=" + deepsearchApiKey;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode data = mapper.readTree(response.getBody()).get("data");

        List<NewsDto> result = new ArrayList<>();
        for (JsonNode item : data) {
            result.add(new NewsDto(
                    item.get("title").asText(),
                    item.get("summary").asText(),
                    item.get("content_url").asText(),
                    item.get("published_at").asText(),
                    item.get("publisher").asText(),
                    item.get("thumbnail_url").asText()
            ));
        }
        return result;
    }
}