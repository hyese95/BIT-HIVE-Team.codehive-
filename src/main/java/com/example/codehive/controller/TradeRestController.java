package com.example.codehive.controller;

import com.example.codehive.service.MyAssetService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/trade")
@AllArgsConstructor
public class TradeRestController {
    private final MyAssetService myAssetService;

    // 사용자의 보유 자산(수량) 조회 (예: 유저 번호 1)
    @GetMapping("/my_assets")
    public ResponseEntity<Map<String, Double>> getMyAssets() {
        Map<String, Double> myAssetMap = myAssetService.readAssetByUserNo(1);
        return ResponseEntity.ok(myAssetMap);
    }
}