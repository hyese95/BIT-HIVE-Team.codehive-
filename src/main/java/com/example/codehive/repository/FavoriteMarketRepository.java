package com.example.codehive.repository;

import com.example.codehive.entity.FavoriteMarket;
import com.example.codehive.entity.FavoriteMarketId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteMarketRepository extends JpaRepository <FavoriteMarket, FavoriteMarketId>{

    List<FavoriteMarket> findByUserNo(int userNo);
}
