package com.example.codehive.service;

import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.repository.CoinTransactionRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface CoinTransactionService {
    List<CoinTransaction> readByUserNo(int userNo);
    List<CoinTransaction> readByUserNoAndMarketAndTransactionTypeAndTransactionState
            (int userNo, String market, String transactionType, String transactionState);





//    BigDecimal sumTransactionCntByUserNoAndMarketAndTransactionTypeAndPriceAndTransactionCntAndTransactionState
//            (int userNo, String market, String transactionType, Long price, Double transactionCnt, String transactionState);
}
