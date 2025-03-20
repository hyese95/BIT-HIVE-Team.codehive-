package com.example.codehive.service;



import com.example.codehive.entity.NotificationSetting;

public interface NotificationSettingService {
    NotificationSetting getSettingsForUser();
    void saveSettings(NotificationSetting settings);
}