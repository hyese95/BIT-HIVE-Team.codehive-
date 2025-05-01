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
@CrossOrigin("http://localhost:5173")
public class TargetPriceAlertController {

    @Autowired
    private TargetPriceAlertsService alertsService;

    @GetMapping("/target_price_alerts.do")
    public String listTargetAlerts(Model model) {
        int userId = 1;
        List<TargetPriceAlert> alerts = alertsService.getAlertsForUser(userId);
        model.addAttribute("targetPriceAlerts", alerts);
        return "setting/support/notifications/target_price_alerts";
    }


}
