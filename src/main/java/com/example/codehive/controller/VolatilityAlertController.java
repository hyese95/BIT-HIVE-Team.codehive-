package com.example.codehive.controller;

import com.example.codehive.entity.VolatilityAlert;
import com.example.codehive.service.VolatilityAlertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/setting/support/notifications")
@CrossOrigin("http://localhost:5173")
public class VolatilityAlertController {

    @Autowired
    private VolatilityAlertsService service;

    // HTML 렌더링용 GET 요청
    @GetMapping("/volatility_alerts.do")
    public String listVolatilityAlerts(Model model) {
        int userId = 1; // 테스트용
        List<VolatilityAlert> alerts = service.getAlertsForUser(userId);
        model.addAttribute("volatilityAlerts", alerts);
        return "setting/support/notifications/volatility_alerts";
    }
}
