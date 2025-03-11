package com.example.codehive.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FavoriteMarketRepositoryTest {

    @Autowired
    FavoriteMarketRepository favoriteMarketRepository;

    @Test
    @Transactional
    void findAll() {
        System.out.println(favoriteMarketRepository.findByUserNo(1));
    }
}