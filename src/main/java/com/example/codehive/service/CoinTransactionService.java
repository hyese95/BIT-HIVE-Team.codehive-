package com.example.codehive.service;

import com.example.codehive.dto.CoinTransactionDto;
import com.example.codehive.dto.CoinTransactionResponseDto;
import com.example.codehive.dto.ProfitResultDto;
import com.example.codehive.entity.CoinTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface CoinTransactionService {
    List<CoinTransaction> findByUserNo(int userNo);
    List<CoinTransaction> findTransactionStateByUserNo(int userNo); // 유저 미체결 transaction
    Page<CoinTransactionResponseDto> getFilteredTransactionDtos(int userNo, String transactionType, String transactionState, String market, Instant startDate, Instant endDate, Pageable pageable);
    ProfitResultDto calculateProfit(int userNo, Map<String, Double> currentPriceMap);
    void saveCoinTransaction(CoinTransaction coinTransaction);
    List<CoinTransactionDto> getSumCoinTransactionsByConditions(int userNo, String market, String transactionType, String transactionState);
    CoinTransactionDto getAvailableQuantity(int userNo, String market);
    void removeAllByUserNo(int userNo);

    List<CoinTransactionDto> getUserCoinHistory(int userNo);

    void remove(int id);
    void removeTransactionPendingByUserNo(int userNo);
    void removeAllTransactionsByUserNo(int userNo);
    void register(CoinTransaction coinTransaction);
}

