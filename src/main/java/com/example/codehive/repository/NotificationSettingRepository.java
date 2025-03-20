package com.example.codehive.repository;

import com.example.codehive.entity.NotificationSetting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationSettingRepository extends JpaRepository<NotificationSetting, Integer> {
    NotificationSetting findById(int userNo);
}