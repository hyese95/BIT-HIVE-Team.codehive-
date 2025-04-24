package com.example.codehive.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoinTransactionServiceTest {
    @Autowired
    private CoinTransactionService coinTransactionService;

    @Test
    @Transactional
    void findTransactionStateByUserNo() {
        System.out.println(coinTransactionService.findTransactionStateByUserNo(1));
    }
}