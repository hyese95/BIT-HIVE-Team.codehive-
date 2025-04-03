package com.example.codehive.repository;

import com.example.codehive.entity.FavoriteMarket;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class FavoriteMarketRepositoryTest {

    @Autowired
    FavoriteMarketRepository favoriteMarketRepository;


    @Test
    @Transactional
    void findByUserNo() {
        List<FavoriteMarket> list = favoriteMarketRepository.findByUserNo(1);
        List<String> markets = list.stream()
                        .map(FavoriteMarket::getMarket).toList();
        System.out.println(markets);
    }
}