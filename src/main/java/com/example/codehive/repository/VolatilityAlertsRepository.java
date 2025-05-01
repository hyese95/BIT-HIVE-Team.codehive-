package com.example.codehive.repository;

import com.example.codehive.entity.VolatilityAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface VolatilityAlertsRepository extends JpaRepository<VolatilityAlert, Integer> {
    List<VolatilityAlert> findByUserNo_Id(Integer userId);
}
