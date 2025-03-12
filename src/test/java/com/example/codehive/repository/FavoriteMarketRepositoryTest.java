package com.example.codehive.repository;

import com.example.codehive.entity.FavoriteMarket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class FavoriteMarketRepositoryTest {

    @Autowired
    FavoriteMarketRepository favoriteMarketRepository;


    @Test
    @Transactional
    void findById() {
        FavoriteMarket fav = favoriteMarketRepository.findById(3);
        System.out.println(fav.toString());
    }

    @Test
    @Transactional
    void findByUserNo() {
        System.out.println(favoriteMarketRepository.findByUserNo(1));
    }
}