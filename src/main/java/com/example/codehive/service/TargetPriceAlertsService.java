package com.example.codehive.service;

import com.example.codehive.entity.TargetPriceAlert;
import java.util.List;

public interface TargetPriceAlertsService {
    List<TargetPriceAlert> getAlertsForUser(int userId);
    void updateMarketEnabled(int alertId, boolean enabled); // 핵심 메서드
}
