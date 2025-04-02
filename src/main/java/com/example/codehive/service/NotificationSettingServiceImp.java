package com.example.codehive.service;

import com.example.codehive.entity.NotificationSetting;
import com.example.codehive.repository.NotificationSettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationSettingServiceImp implements NotificationSettingService {

    @Autowired
    private NotificationSettingRepository notificationSettingRepository;

    @Override
    public NotificationSetting getSettingsForUser(int userNo) {
        return notificationSettingRepository.findById(userNo);
    }

    @Override
    public void saveSettings(NotificationSetting settings) {
        notificationSettingRepository.save(settings);
    }


}
