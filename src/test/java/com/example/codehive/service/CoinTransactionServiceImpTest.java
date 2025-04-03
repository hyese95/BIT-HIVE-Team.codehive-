package com.example.codehive.service;

import com.example.codehive.entity.CoinTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoinTransactionServiceImpTest {
    @Autowired
    private CoinTransactionService coinTransactionService;

    @Test
    @Rollback(false)
    void removeAllByUserNo() {
        coinTransactionService.removeAllByUserNo(1);
    }
}