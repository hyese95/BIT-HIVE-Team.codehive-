package com.example.codehive.controller;

import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.service.CoinTransactionService;
import com.example.codehive.service.MyAssetService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/trade")
@AllArgsConstructor
public class TradeRestController {
    private final MyAssetService myAssetService;
    private final CoinTransactionService coinTransactionService;

    @GetMapping("/my_assets")
    public ResponseEntity<Map<String, Double>> getMyAssets() {
        Map<String, Double> myAssetMap = myAssetService.readAssetByUserNo(1);
        return ResponseEntity.ok(myAssetMap);
    }

    @PostMapping("/api/transaction")
    public ResponseEntity<Void> buy(@RequestBody CoinTransaction coinTransaction) {
        log.info("Received request data: {}", coinTransaction);
        try {
            coinTransaction.setTransactionDate(Instant.now());
            log.info("Setting transaction date: {}", coinTransaction.getTransactionDate());
            coinTransactionService.saveCoinTransaction(coinTransaction);
            log.info("Transaction saved successfully");
            return ResponseEntity.ok().build();
        }
        catch (Exception e) {
            log.error("Error occurred while saving transaction: ", e);
            return ResponseEntity.badRequest().build();
        }
    }


}