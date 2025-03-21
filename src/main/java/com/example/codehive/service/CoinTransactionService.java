package com.example.codehive.service;

import com.example.codehive.dto.MyAssetDto;
import com.example.codehive.dto.ProfitResult;
import com.example.codehive.entity.CoinTransaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CoinTransactionService {
    ProfitResult calculateProfit(int userNo, Map<String, Double> currentPriceMap);
}
