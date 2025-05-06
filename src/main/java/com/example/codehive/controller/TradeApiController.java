package com.example.codehive.controller;

import com.example.codehive.dto.TradeRequestDto;
import com.example.codehive.service.CoinTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/trade")
public class TradeApiController {

    private final CoinTransactionService coinTransactionService;


    @PostMapping("/me")
    public ResponseEntity<Void> me(@RequestBody TradeRequestDto tradeRequestDto) {
        Integer userNo = 1;
        tradeRequestDto.setUserNo(userNo);
        coinTransactionService.submitTrade(tradeRequestDto);

        return ResponseEntity.ok().build();
    }


}
