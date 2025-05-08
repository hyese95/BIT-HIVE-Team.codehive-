package com.example.codehive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * 스케줄러 관련 설정 클래스
 */
@Configuration
@EnableScheduling
public class SchedulerConfig {

    /**
     * RestTemplate 빈 생성
     * API 호출에 사용됨
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
} 