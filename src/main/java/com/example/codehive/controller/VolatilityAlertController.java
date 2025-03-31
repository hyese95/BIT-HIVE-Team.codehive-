package com.example.codehive.controller;

import com.example.codehive.entity.VolatilityAlert;
import com.example.codehive.service.VolatilityAlertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/setting/support/notifications")
public class VolatilityAlertController {

    @Autowired
    private VolatilityAlertsService service;

    @GetMapping("/volatility_alerts.do")
    public String listVolatilityAlerts(Model model) {
        int userId = 2;
        List<VolatilityAlert> alerts = service.getAlertsForUser(userId);
        model.addAttribute("volatilityAlerts", alerts);
        model.addAttribute("userNo", userId);
        return "setting/support/notifications/volatility_alerts";
    }

    @PostMapping("/volatility_alerts/save")
    @ResponseBody
    public Map<String, Object> updateStatus(@RequestBody Map<String, Object> body) {
        Integer alertId = (Integer) body.get("id");
        Boolean enabled = (Boolean) body.get("enabled");

        Map<String, Object> response = new HashMap<>();
        try {
            service.toggleAlert(alertId, enabled);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
        }

        return response;
    }
}
