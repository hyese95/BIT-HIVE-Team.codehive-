package com.example.codehive.service;

import com.example.codehive.entity.TargetPriceAlert;
import com.example.codehive.repository.TargetPriceAlertsRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TargetPriceAlertsServiceImp implements TargetPriceAlertsService {

    @Autowired
    private TargetPriceAlertsRepository alertsRepository;

    @Override
    public List<TargetPriceAlert> getAlertsForUser(int userId) {
        return alertsRepository.findByUserNo_Id(userId);
    }

    @Override
    @Transactional
    public void updateTargetPrice(int alertId, double newPrice) {
        TargetPriceAlert alert = alertsRepository.findById(alertId)
                .orElseThrow(IllegalArgumentException::new);
        alert.setTargetPrice(newPrice);
        alertsRepository.save(alert);
    }
}