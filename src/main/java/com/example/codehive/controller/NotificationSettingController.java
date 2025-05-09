package com.example.codehive.controller;

import com.example.codehive.entity.NotificationSetting;
import com.example.codehive.service.NotificationSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/setting/support/notifications")
public class NotificationSettingController {

    @Autowired
    private NotificationSettingService notificationSettingService;

    @GetMapping("/notification_setting_json.do")
    public NotificationSetting getSettingsJson() {
        NotificationSetting settings = notificationSettingService.getSettingsForUser(1);
        if (settings == null) {
            settings = new NotificationSetting();
        }
        return settings;
    }

    @PostMapping("/save-auto")
    public Map<String, Object> autoSave(@RequestBody Map<String, Object> payload) {
        return handleSave(payload);
    }

    private Map<String, Object> handleSave(Map<String, Object> payload) {
        Map<String, Object> response = new HashMap<>();
        try {
            String field = (String) payload.get("field");
            boolean value = (Boolean) payload.get("value");

            NotificationSetting settings = notificationSettingService.getSettingsForUser(1);
            if (settings == null) {
                settings = new NotificationSetting();
            }

            updateSettingsField(settings, field, value);
            notificationSettingService.saveSettings(settings);

            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }

    private void updateSettingsField(NotificationSetting settings, String field, boolean value) {
        switch (field) {
            case "volatilityYn": settings.setVolatilityYn(value); break;
            case "portfolioYn": settings.setPortfolioYn(value); break;
            case "targetPriceYn": settings.setTargetPriceYn(value); break;
            case "tradeYn": settings.setTradeYn(value); break;
            case "likeYn": settings.setLikeYn(value); break;
            case "commentYn": settings.setCommentYn(value); break;
            case "replyYn": settings.setReplyYn(value); break;
            case "followerYn": settings.setFollowerYn(value); break;
            case "allNotifications":
                settings.setVolatilityYn(value);
                settings.setPortfolioYn(value);
                settings.setTargetPriceYn(value);
                settings.setTradeYn(value);
                settings.setLikeYn(value);
                settings.setCommentYn(value);
                settings.setReplyYn(value);
                settings.setFollowerYn(value);
                break;
            default: throw new IllegalArgumentException("알 수 없는 필드: " + field);
        }
    }
}
