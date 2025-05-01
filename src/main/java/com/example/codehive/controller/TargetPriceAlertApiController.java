package com.example.codehive.controller;

import com.example.codehive.entity.TargetPriceAlert;
import com.example.codehive.service.TargetPriceAlertsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api")
@AllArgsConstructor
public class TargetPriceAlertApiController {

    private TargetPriceAlertsService alertsService;

    @GetMapping("/target_price_alerts/me")
    public Map<String, Object> getTargetPriceAlerts() {
        int userId = 1;
        List<TargetPriceAlert> alerts = alertsService.getAlertsForUser(userId);

        Map<String, Object> response = new HashMap<>();
        response.put("targetPriceAlerts", alerts);
        return response;
    }

    @PostMapping("/target_price_alerts/me")
    public Map<String, Object> saveAlert(@RequestBody Map<String, Object> body) {
        Integer alertId = (Integer) body.get("id");
        Boolean enabled = (Boolean) body.get("enabled");
        Map<String, Object> response = new HashMap<>();
        try {
            alertsService.updateMarketEnabled(alertId, enabled);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
        }

        return response;
    }

}
