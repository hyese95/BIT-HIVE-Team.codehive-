package com.example.codehive.controller;

import com.example.codehive.entity.NotificationSetting;
import com.example.codehive.service.NotificationSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/setting/support/notifications")
public class NotificationSettingController {

    @Autowired
    private NotificationSettingService notificationSettingService;

    @GetMapping("/notification_setting.do")
    public String getSettingsPage(Model model) {
        NotificationSetting settings = notificationSettingService.getSettingsForUser(1);
        if (settings == null) {
            settings = new NotificationSetting();

        }
        model.addAttribute("settings", settings);
        return "setting/support/notifications/notification_setting"; // 템플릿 경로 정확히 맞춰줌
    }






    @PostMapping("/save-auto")
    @ResponseBody
    public Map<String, Object> autoSave(@RequestBody Map<String, Object> payload) {
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
            response.put("message", e.getMessage()); // 이 메시지를 프론트에서 받게 됨
        }

        return response;
    }
}