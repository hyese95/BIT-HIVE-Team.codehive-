package com.example.codehive.service;

import com.example.codehive.entity.VolatilityAlert;

import java.util.List;

public interface VolatilityAlertsService {
    List<VolatilityAlert> getAlertsForUser(int userId);
    void toggleAlert(int alertId, boolean enabled);
}
