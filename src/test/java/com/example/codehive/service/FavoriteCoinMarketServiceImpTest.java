package com.example.codehive.service;

import com.example.codehive.entity.FavoriteMarket;
import com.example.codehive.repository.FavoriteMarketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@SpringBootTest
@Transactional
class FavoriteCoinMarketServiceImpTest {

    @Autowired
    private FavoriteMarketRepository favoriteMarketRepository;
    @Autowired
    private FavoriteCoinMarketService favoriteCoinMarketService;

    @Test
    void readByUserNo() {
    }

    @Test
    void regiseter() {
    }

    @Test
    void remove() {
    }

    @Test
    void testReadByUserNo() {
        System.out.println(favoriteCoinMarketService.readByUserNo(1));
    }

    @Test
    void readDtoByUserNo() {

    }
}