package com.example.codehive.repository;

import com.example.codehive.entity.CoinTransaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class CoinTransactionRepositoryTest {

    @Autowired
    CoinTransactionRepository coinTransactionRepository;

}