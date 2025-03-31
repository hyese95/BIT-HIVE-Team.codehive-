package com.example.codehive.repository;

import com.example.codehive.entity.TargetPriceAlert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TargetPriceAlertsRepository extends JpaRepository<TargetPriceAlert, Integer> {
    List<TargetPriceAlert> findByUserNo_Id(Integer userNo);
}