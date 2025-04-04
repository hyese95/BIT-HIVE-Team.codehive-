package com.example.codehive.service;

import com.example.codehive.entity.FavoriteMarket;

import java.util.List;
import java.util.Optional;

public interface FavoriteCoinMarketService {
    List<FavoriteMarket> readByUserNo(int userNo);

    void regiseter(FavoriteMarket favoriteMarket);
    void remove(FavoriteMarket favoriteMarket);
}
