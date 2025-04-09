package com.example.codehive.service;

import com.example.codehive.dto.CoinTransactionDto;
import com.example.codehive.dto.ProfitResultDto;
import com.example.codehive.entity.CoinTransaction;

import java.util.List;
import java.util.Map;

public interface CoinTransactionService {
    List<CoinTransaction> findByUserNo(int userNo);
    ProfitResultDto calculateProfit(int userNo, Map<String, Double> currentPriceMap);
    void saveCoinTransaction(CoinTransaction coinTransaction);
    List<CoinTransactionDto> getSumCoinTransactionsByConditions(int userNo, String market, String transactionType, String transactionState);
    CoinTransactionDto getAvailableQuantity(int userNo, String market);
    void removeAllByUserNo(int userNo);

    List<CoinTransactionDto> getUserCoinHistory(int userNo);
}

