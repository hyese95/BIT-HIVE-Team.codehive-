package com.example.codehive.repository;

import com.example.codehive.dto.CoinTransactionDto;
import com.example.codehive.entity.CoinTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class CoinTransactionRepositoryTest {

    @Autowired
    CoinTransactionRepository coinTransactionRepository;

    @Test
    void findSumCoinTransactionsByUserNoWithBuy() {
        System.out.println(coinTransactionRepository.findSumKRWTransactionsByUserNoWithBuy(1));
    }

    @Test
    void findSumCoinTransactionsByUserNoWithSell() {
    }


    @Test
    void findSumCoinTransactionsByConditions() {
        List<CoinTransactionDto> list=coinTransactionRepository.findSumCoinTransactionsByConditions(1,"KRW-XRP","buy",null);
        System.out.println(list);
    }
}