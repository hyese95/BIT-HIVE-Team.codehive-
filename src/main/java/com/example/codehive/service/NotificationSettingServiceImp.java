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
    public NotificationSetting getSettingsForUser() {
        int userNo = getCurrentUserNo(); // 사용자 정보 획득 로직 필요
        return notificationSettingRepository.findById(userNo);
    }

    @Override
    public void saveSettings(NotificationSetting settings) {
        notificationSettingRepository.save(settings);
    }

    // 현재 로그인한 사용자 번호를 가져오는 메서드 구현 필요
    private int getCurrentUserNo() {
        // 임시로 사용자 번호 1 반환
        return 1;
    }
}
