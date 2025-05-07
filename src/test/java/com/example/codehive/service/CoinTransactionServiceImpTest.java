package com.example.codehive.service;

import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.repository.CoinTransactionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoinTransactionServiceImpTest {
    @Autowired
    private CoinTransactionService coinTransactionService;
    @Autowired
    private CoinTransactionRepository coinTransactionRepository;

    @Test
    @Rollback(false)
    void removeAllByUserNo() {
        coinTransactionService.removeAllByUserNo(1);
    }

    @Test
    @Rollback(false)
    void handleBuy() {
        CoinTransaction coinTransaction = new CoinTransaction();
        coinTransaction.setUserNo(1);
        coinTransaction.setTransactionType("BUY");
        coinTransaction.setPrice(1.00);
        coinTransaction.setTransactionCnt(3.00);
        coinTransaction.setMarket("XRP-KRW");
        coinTransactionRepository.save(coinTransaction);
    }
}