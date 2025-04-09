package com.example.codehive.service;

import com.example.codehive.entity.FavoriteMarket;
import com.example.codehive.entity.FavoriteMarketId;

import java.util.List;

public interface FavoriteCoinMarketService {
    List<String> readByUserNo(int userNo);

    void regiseter(FavoriteMarket favoriteMarket);
    void remove(FavoriteMarketId favoriteMarketid);
}
