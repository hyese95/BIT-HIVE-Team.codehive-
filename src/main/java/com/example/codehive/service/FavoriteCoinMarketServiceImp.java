package com.example.codehive.service;

import com.example.codehive.entity.FavoriteMarket;
import com.example.codehive.repository.FavoriteMarketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FavoriteCoinMarketServiceImp implements FavoriteCoinMarketService {
    private final FavoriteMarketRepository favoriteMarketRepository;

    @Override
    public List<FavoriteMarket> readByUserNo(int userNo) {
        return favoriteMarketRepository.findByUserNo(userNo);
    }

    @Override
    public void regiseter(FavoriteMarket favoriteMarket) {

    }

    @Override
    public void remove(FavoriteMarket favoriteMarket) {

    }
}
