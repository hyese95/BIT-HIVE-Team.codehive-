package com.example.codehive.service;



import com.example.codehive.entity.NotificationSetting;

public interface NotificationSettingService {
    NotificationSetting getSettingsForUser(int userNo);
    void saveSettings(NotificationSetting settings);
}