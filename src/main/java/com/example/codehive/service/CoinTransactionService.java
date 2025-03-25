package com.example.codehive.service;

import com.example.codehive.dto.ProfitResultDto;

import java.util.Map;

public interface CoinTransactionService {
    ProfitResultDto calculateProfit(int userNo, Map<String, Double> currentPriceMap);
}
