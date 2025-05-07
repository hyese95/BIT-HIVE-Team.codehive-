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

    @GetMapping("/me/deposit")
    public double getRemainDeposit() {
        int userNo=1;//하드코딩
        double deposit = 0;
        deposit=coinTransactionService.getAvailableDeposit(userNo);
        return deposit;
    }
    @GetMapping("/me/remainCnt")
    public double getRemainCnt(@RequestParam String market) {
        int userNo=1;
        double remainCnt = 0;
        remainCnt=coinTransactionService.getAvailableCoinQuantity(userNo,market);
        return remainCnt;
    }


    @PostMapping("/me")
    public ResponseEntity<Void> me(@RequestBody TradeRequestDto tradeRequestDto) {
        Integer userNo = 1;
        tradeRequestDto.setUserNo(userNo);
        double deposit=0.0;
        deposit=coinTransactionService.getAvailableDeposit(userNo);
        if(tradeRequestDto.transactionType.equals("BUY") && deposit<tradeRequestDto.getTransactionCnt()*tradeRequestDto.getPrice()){
            throw new RuntimeException("잔액부족");
        }
        coinTransactionService.submitTrade(tradeRequestDto);

        return ResponseEntity.ok().build();
    }


}
