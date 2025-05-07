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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsService {
    @Value("${my.api.deepsearch-key}")
    private String deepsearchApiKey;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<NewsDto> fetchNewsByKeyword(String keyword) throws Exception {
        String url = buildUrlWithParams(keyword, null, null);
        return fetchNewsFromApi(url);
    }

    public List<NewsDto> fetchNewsByDateRange(String keyword, LocalDate startDate, LocalDate endDate) throws Exception {
        String url = buildUrlWithParams(keyword, startDate, endDate);
        return fetchNewsFromApi(url);
    }

    public List<NewsDto> fetchNewsForYesterday() throws Exception {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        String keyword = "title:(비트코인 OR 암호화폐 OR 나스닥)";
        return fetchNewsByDateRange(keyword, yesterday, yesterday);
    }

    private String buildUrlWithParams(String keyword, LocalDate startDate, LocalDate endDate) {
        StringBuilder url = new StringBuilder("https://api-v2.deepsearch.com/v1/articles?keyword=");
        url.append(URLEncoder.encode(keyword, StandardCharsets.UTF_8));
        if (startDate != null && endDate != null) {
            url.append("&date_from=").append(startDate);
            url.append("&date_to=").append(endDate);
        }
        url.append("&api_key=").append(deepsearchApiKey);
        return url.toString();
    }

    private List<NewsDto> fetchNewsFromApi(String url) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        JsonNode data = objectMapper.readTree(response.getBody()).get("data");

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