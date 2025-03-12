package com.example.codehive.service;

import com.example.codehive.entity.FavoriteMarket;
import com.example.codehive.repository.FavoriteMarketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FavoriteCoinMarketServiceImp implements FavoriteCoinMarketService {
    private final FavoriteMarketRepository favoriteMarketRepository;


    @Override
    public List<FavoriteMarket> readAll() {
        return favoriteMarketRepository.findByUserNo(1);
    }

    @Override
    public List<FavoriteMarket> readByUserNo(int userNo) {
        return favoriteMarketRepository.findByUserNo(userNo);
    }

}
