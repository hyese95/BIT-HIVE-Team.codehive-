package com.example.codehive.service;

import com.example.codehive.dto.ProfitResultDto;
import com.example.codehive.entity.CoinTransaction;

import java.util.List;
import java.util.Map;

public interface CoinTransactionService {
    ProfitResultDto calculateProfit(int userNo, Map<String, Double> currentPriceMap);
    void saveCoinTransaction(CoinTransaction coinTransaction);
}

