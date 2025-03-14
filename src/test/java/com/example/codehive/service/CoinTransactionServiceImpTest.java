package com.example.codehive.service;

import com.example.codehive.entity.CoinTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoinTransactionServiceImpTest {
    @Autowired
    private CoinTransactionService coinTransactionService;

    @Test
    @Transactional
    void readByUserNoAndMarketAndTransactionTypeAndTransactionState() {
        CoinTransaction ct = new CoinTransaction();
        ct.setUserNo(1);
        ct.setMarket("KRW-KRW");
        ct.setTransactionType("BUY");
        ct.setTransactionState("COMPLETED");
        List<CoinTransaction> KrwHoldingList = coinTransactionService.readByUserNoAndMarketAndTransactionTypeAndTransactionState
                (1,"KRW-KRW","BUY","COMPLETED");
        System.out.println(KrwHoldingList);
    }

//    @Test
//    void sumTransactionCntByUserNoAndMarketAndTransactionTypeAndPriceAndTransactionCntAndTransactionState() {
//        CoinTransaction ct = new CoinTransaction();
//        ct.setUserNo(1);
//        ct.setMarket("KRW-KRW");
//        ct.setTransactionType("BUY");
//        ct.setTransactionState("COMPLETED");
//        List<CoinTransaction> KrwHoldingList = coinTransactionService.sumTransactionCntByUserNoAndMarketAndTransactionTypeAndPriceAndTransactionCntAndTransactionState(
//                1,"KRW-KRW","BUY",null,null,"COMPLETED"
//        );
//        System.out.println(KrwHoldingList);
//    }
}