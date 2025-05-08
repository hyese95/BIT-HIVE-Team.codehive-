package com.example.codehive.controller;


import com.example.codehive.entity.FavoriteMarket;
import com.example.codehive.entity.FavoriteMarketId;
import com.example.codehive.entity.User;
import com.example.codehive.service.FavoriteCoinMarketService;
import com.example.codehive.service.FavoriteCoinMarketServiceImp;
import com.example.codehive.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/favorites")
@CrossOrigin("http://localhost:5173")
@AllArgsConstructor
public class FavoriteMarketApiController {

    private final FavoriteCoinMarketService favoriteCoinMarketService;
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<List<String>> getMyFavorites(@AuthenticationPrincipal UserDetails loginUser) {
        String userId = loginUser.getUsername(); // 로그인한 사용자 ID 가져오기
        User user = userService.readByUserId(userId)
                .orElseThrow(() -> new RuntimeException("로그인된 유저를 찾을 수 없습니다."));
        int userNo = user.getId();
        List<String> FavoriteMarketList=favoriteCoinMarketService.readByUserNo(userNo);
        return ResponseEntity.ok(FavoriteMarketList);

    }

    @PostMapping("/me")
    public ResponseEntity<Void> registerFavorites(
            @RequestBody FavoriteMarket favoriteMarket,
            @AuthenticationPrincipal UserDetails loginUser
    ){
        String userId = loginUser.getUsername(); // 로그인한 사용자 ID 가져오기
        User user = userService.readByUserId(userId)
                .orElseThrow(() -> new RuntimeException("로그인된 유저를 찾을 수 없습니다."));
        int userNo = user.getId();
        try{
            favoriteMarket.setUserNo(userNo);//하드코딩
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
            @RequestBody FavoriteMarketId favoriteMarketId,
            @AuthenticationPrincipal UserDetails loginUser
    ){
        String userId = loginUser.getUsername(); // 로그인한 사용자 ID 가져오기
        User user = userService.readByUserId(userId)
                .orElseThrow(() -> new RuntimeException("로그인된 유저를 찾을 수 없습니다."));
        int userNo = user.getId();
        try{
            favoriteMarketId.setUserNo(userNo);//하드코딩
            favoriteCoinMarketService.remove(favoriteMarketId);
        }catch (IllegalArgumentException e){
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(202).build();
    }



}
