package com.example.codehive.controller;

import com.example.codehive.entity.NotificationSetting;
import com.example.codehive.service.NotificationSettingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api")
@AllArgsConstructor
public class NotificationSettingApiController {

    private NotificationSettingService notificationSettingService;

    @GetMapping("/notifications/me")
    public NotificationSetting getNotificationSettings() {
        int userId = 1; // 예시 userId 고정
        NotificationSetting settings = notificationSettingService.getSettingsForUser(userId);
        if (settings == null) {
            settings = new NotificationSetting();
        }
        return settings;
    }

    @PostMapping("/notifications/me")
    public Map<String, Object> saveNotificationSetting(@RequestBody Map<String, Object> payload) {
        Map<String, Object> response = new HashMap<>();

        try {
            String field = (String) payload.get("field");
            boolean value = (Boolean) payload.get("value");

            NotificationSetting settings = notificationSettingService.getSettingsForUser(1);
            if (settings == null) {
                settings = new NotificationSetting();
            }

            switch (field) {
                case "volatilityYn":
                    settings.setVolatilityYn(value);
                    break;
                case "portfolioYn":
                    settings.setPortfolioYn(value);
                    break;
                case "targetPriceYn":
                    settings.setTargetPriceYn(value);
                    break;
                case "tradeYn":
                    settings.setTradeYn(value);
                    break;
                case "likeYn":
                    settings.setLikeYn(value);
                    break;
                case "commentYn":
                    settings.setCommentYn(value);
                    break;
                case "replyYn":
                    settings.setReplyYn(value);
                    break;
                case "followerYn":
                    settings.setFollowerYn(value);
                    break;
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
                default:
                    throw new IllegalArgumentException("알 수 없는 필드: " + field);
            }

            notificationSettingService.saveSettings(settings);
            response.put("success", true);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }

        return response;
    }
}
