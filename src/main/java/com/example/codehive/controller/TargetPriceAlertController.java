package com.example.codehive.controller;

import com.example.codehive.entity.TargetPriceAlert;
import com.example.codehive.service.TargetPriceAlertsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/setting/support/notifications")
public class TargetPriceAlertController {

    @Autowired
    private TargetPriceAlertsService alertsService;

    @GetMapping("/target_price_alerts.do")
    public String listTargetAlerts(Model model) {
        int userId = 4;
        List<TargetPriceAlert> alerts = alertsService.getAlertsForUser(userId);
        model.addAttribute("targetPriceAlerts", alerts);
        model.addAttribute("userNo", userId);


        return "setting/support/notifications/target_price_alerts";
    }

    @PostMapping("/save")
    @ResponseBody
    public Map<String, Object> saveAlert(@RequestBody Map<String, Object> body) {
        Integer alertId = (Integer) body.get("id");
        Double newPrice = Double.valueOf(body.get("targetPrice").toString());

        Map<String, Object> response = new HashMap<>();
        try {
            alertsService.updateTargetPrice(alertId, newPrice);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
        }

        return response;
    }

}
