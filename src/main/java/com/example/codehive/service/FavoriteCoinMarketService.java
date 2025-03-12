package com.example.codehive.service;

import com.example.codehive.entity.FavoriteMarket;

import java.util.List;

public interface FavoriteCoinMarketService {
    List<FavoriteMarket> readAll();
    List<FavoriteMarket> readByUserNo(int userNo);
}
