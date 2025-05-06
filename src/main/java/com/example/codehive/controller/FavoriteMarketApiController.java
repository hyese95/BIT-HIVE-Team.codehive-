package com.example.codehive.controller;


import com.example.codehive.entity.FavoriteMarket;
import com.example.codehive.entity.FavoriteMarketId;
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

    @PostMapping("/me")
    public ResponseEntity<Void> registerFavorites(
            @RequestBody FavoriteMarket favoriteMarket
    ){
        try{
            favoriteMarket.setUserNo(1);//하드코딩
            favoriteCoinMarketService.regiseter(favoriteMarket);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(409).build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(201).build();
    }
    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteFavorite(
            @RequestBody FavoriteMarketId favoriteMarketId
    ){
        try{
            favoriteMarketId.setUserNo(1);//하드코딩
            favoriteCoinMarketService.remove(favoriteMarketId);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(202).build();
    }



}
