package com.example.codehive.controller;


import com.example.codehive.service.FavoriteCoinMarketService;
import com.example.codehive.service.FavoriteCoinMarketServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin("http://localhost:5173")
@AllArgsConstructor
public class FavoriteMarketApiController {

    private final FavoriteCoinMarketService favoriteCoinMarketService;

    @GetMapping("/me")
    public ResponseEntity<List<String>> getMyFavorites() {
        //일단 유저넘버1
        List<String> FavoriteMarketList=favoriteCoinMarketService.readByUserNo(1);
        return ResponseEntity.ok(FavoriteMarketList);

    }



}
