package com.example.codehive.service;

import com.example.codehive.entity.Guide;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class GuideServiceImpTest {
    @Autowired GuideService guideService;


    @Test
    void readAll() {
        List<Guide> guideList = guideService.readAll();
        for (Guide guide: guideList) {
            System.out.println(guide.getGuideTitle());
        }
    }
}