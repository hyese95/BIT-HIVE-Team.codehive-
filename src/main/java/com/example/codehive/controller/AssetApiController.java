package com.example.codehive.controller;


import com.example.codehive.dto.AssetDto;
import com.example.codehive.dto.MyAssetDto;
import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.service.CoinNameService;
import com.example.codehive.service.CoinTransactionService;
import com.example.codehive.service.MyAssetService;
import com.example.codehive.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
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
    private CoinTransactionService coinTransactionService;
    private CoinNameService coinNameService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("coinTransactions.do")
    public Map<String,Object> coinTransactions() {
        List<CoinTransaction> coinTransactions = coinTransactionService.findByUserNo(1);

        Map<String, String> coinNameMap = coinNameService.getMarketToKoreanNameMap();
        Map<String, Object> map = new HashMap<>();
        map.put("coinTransactions",coinTransactions);
        map.put("coinNameMap",coinNameMap);
        System.out.println(map.toString());

        return map;
    }

    @GetMapping("openOrders")
    public Map<String,Object> openOrders() {
        List<CoinTransaction> coinTransactions = coinTransactionService.findTransactionStateByUserNo(1);

        Map<String, Object> map = new HashMap<>();
        map.put("coinTransactions",coinTransactions);
        return map;
    }

    // 선택 삭제
    @DeleteMapping("openOrders/id/{id}")
    public ResponseEntity<Void> remove(@PathVariable int id) {
        try{
            coinTransactionService.remove(id);
        }catch (IllegalArgumentException e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    // 전체 삭제
    @DeleteMapping("openOrders/user/{userNo}")
    public ResponseEntity<Void> removeAllPending(@PathVariable int userNo) {
        try{
            coinTransactionService.removeTransactionPendingByUserNo(userNo);
        }catch (IllegalArgumentException e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }



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
    public List<AssetDto> me() {
        List<AssetDto> assetDtoList = myAssetService.readHoldingCoinListByUserNo(1);
        return  assetDtoList;
    }
}
