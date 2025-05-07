package com.example.codehive.controller;


import com.example.codehive.dto.AssetDto;
import com.example.codehive.dto.CoinTransactionDto;
import com.example.codehive.dto.MyAssetDto;
import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.entity.User;
import com.example.codehive.service.CoinNameService;
import com.example.codehive.service.CoinTransactionService;
import com.example.codehive.service.MyAssetService;
import com.example.codehive.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/asset")
@CrossOrigin("http://localhost:5173")
public class AssetApiController {

    private MyAssetService myAssetService;
    private final UserService userService;

    /*
    {
    market:"KRW-BTC"
    holdingAmount:"1"
    averagePrice:"30000"
    }

    {
    market:"KRW-BTC"
    korean_name:"비트코인"
    holdingAmount:"1"
    averagePrice:"30000"
    currentPrice:"40000"
    change:"RISE" 값비교해서 높으면 RISE 낮으면 FALL 0 EVEN
    change_price:"" averagePrice-currentPrice or 반대
    change_rate:"" //chP/avgP

    }
     */

    @GetMapping("/me")
    public List<AssetDto> me(@AuthenticationPrincipal UserDetails loginUser) {
        String userId = loginUser.getUsername(); // 로그인한 사용자 ID 가져오기
        User user = userService.readByUserId(userId)
                .orElseThrow(() -> new RuntimeException("로그인된 유저를 찾을 수 없습니다."));
        int userNo = user.getId();
        List<AssetDto> assetDtoList = myAssetService.readHoldingCoinListByUserNo(userNo);
        return  assetDtoList;
    }
}
