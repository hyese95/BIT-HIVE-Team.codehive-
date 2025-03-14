package com.example.codehive.service;

import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.repository.CoinTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CoinTransactionServiceImp implements CoinTransactionService {
    private final CoinTransactionRepository coinTransactionRepository;
    private final RestTemplate restTemplate;

    @Override
    public List<CoinTransaction> readByUserNo(int userNo) {
        return coinTransactionRepository.findByUserNo(userNo);
    }

    @Override
    public List<CoinTransaction> readByUserNoAndMarketAndTransactionTypeAndTransactionState
            (int userNo, String market, String transactionType, String transactionState) {
        return coinTransactionRepository.findByUserNoAndMarketAndTransactionTypeAndTransactionState(
                userNo, market, transactionType, transactionState
        );
    }
}
