package com.example.codehive.repository;

import com.example.codehive.dto.CoinTransactionDto;
import com.example.codehive.entity.CoinTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCrypt;
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
        System.out.println(coinTransactionRepository.findTotalSellQuantityByUserNo(1));
    }


    @Test
    void findByUserNo() {
        System.out.println(coinTransactionRepository.findByUserNo(1));
    }

    @Test
    void findSummaryCoinTransactionsByUserNoWithBuy() {
        System.out.println(coinTransactionRepository.findSummaryCoinTransactionsByUserNoWithBuy(1));
    }

    @Test
    void testFindSumCoinTransactionsByUserNoWithSell() {
        System.out.println(coinTransactionRepository.findSumCoinTransactionsByUserNoWithSell(1));
    }

    @Test
    void pw(){
        BCrypt bCrypt = new BCrypt();
        System.out.println(bCrypt.hashpw("pass123", BCrypt.gensalt()));
        boolean check=bCrypt.checkpw("pass123", "$2a$10$3/Sh6Tv3wKS8K.J/EUHAo.UwsVVgU8bhyvyaeqiu.uFrwS4wDKAN6");
        System.out.println(check);
    }

}