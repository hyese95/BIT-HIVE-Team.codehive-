package com.example.codehive.service;

import com.example.codehive.entity.VolatilityAlert;
import com.example.codehive.repository.VolatilityAlertsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VolatilityAlertsServiceImp implements VolatilityAlertsService {

    @Autowired
    private VolatilityAlertsRepository repository;

    @Override
    public List<VolatilityAlert> getAlertsForUser(int userId) {
        return repository.findByUserNo_Id(userId);
    }

    @Override
    @Transactional
    public void toggleAlert(int alertId, boolean enabled) {
        VolatilityAlert alert = repository.findById(alertId)
                .orElseThrow(() -> new IllegalArgumentException("알림이 존재하지 않습니다."));
        String baseMarket = alert.getMarket().replace(" (OFF)", "");
        alert.setMarket(enabled ? baseMarket : baseMarket + " (OFF)");
        repository.save(alert);
    }
}
