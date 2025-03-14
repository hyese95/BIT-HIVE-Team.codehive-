package com.example.codehive.repository;

import com.example.codehive.entity.CoinTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoinTransactionRepositoryTest {

    @Autowired
    CoinTransactionRepository coinTransactionRepository;

    @Test
    @Transactional
    public void testFindByUserNo() {
        int userNo = 1;
        List<CoinTransaction> transactions = coinTransactionRepository.findByUserNo(userNo);
        System.out.println("User " + userNo + "의 거래 내역:");
        transactions.stream()
                .filter(tx -> "KRW-KRW".equals(tx.getMarket()) && "BUY".equals(tx.getTransactionType()))
                .forEach(System.out::println);
    }

    @Test
    @Transactional
    void findSumCoinTransactionsByUserNo() {
        System.out.println(coinTransactionRepository.findSumCoinTransactionsByUserNo(1));
    }
}