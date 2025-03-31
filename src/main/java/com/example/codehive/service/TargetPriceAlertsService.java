package com.example.codehive.service;

import com.example.codehive.entity.TargetPriceAlert;

import java.util.List;

public interface TargetPriceAlertsService {
    List<TargetPriceAlert> getAlertsForUser(int userId);
    void updateTargetPrice(int alertId, double newPrice);
}