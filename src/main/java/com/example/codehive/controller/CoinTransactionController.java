package com.example.codehive.controller;


import com.example.codehive.dto.CoinTransactionDto;
import com.example.codehive.repository.CoinTransactionRepository;
import com.example.codehive.service.CoinTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CoinTransactionController {


    private final CoinTransactionService coinTransactionService;
    private CoinTransactionRepository coinTransactionRepository;

    @GetMapping("/api/transaction/{userNo}/KRW/buy")
    public CoinTransactionDto getKRWBuyTransactions(@PathVariable Integer userNo
    ) {
        List<CoinTransactionDto> buyList = coinTransactionRepository.findSumKRWTransactionsByUserNoWithBuy(userNo);
        CoinTransactionDto buyDto = buyList.get(0);
        return buyDto;
    }

    @GetMapping("/api/transaction/{userNo}/KRW/sell")
    public CoinTransactionDto getKRWSellTransactions(@PathVariable Integer userNo
    ) {
        List<CoinTransactionDto> sellList = coinTransactionRepository.findSumKRWTransactionsByUserNoWithSell(userNo);
        CoinTransactionDto sellDto = sellList.get(0);
        return sellDto;
    }

    @GetMapping("/api/transaction/{userNo}/{market}/buy")
    public CoinTransactionDto getSumDtoBuyTransactions(
            @PathVariable Integer userNo, @PathVariable String market,
            @RequestParam (required = false) String transactionState
    ) {
        List<CoinTransactionDto> buyList=coinTransactionService.getSumCoinTransactionsByConditions(userNo,market,"BUY",transactionState);
        CoinTransactionDto buyDto=buyList.get(0);
        if(buyList.isEmpty()){
            return new CoinTransactionDto(market, 0.0);
        }else{
            return buyList.get(0);
        }
    }

    @GetMapping("/api/transaction/{userNo}/{market}/sell")
    public CoinTransactionDto getSumDtoSellTransactions(
            @PathVariable Integer userNo, @PathVariable String market,
            @RequestParam (required = false) String transactionState
    ) {
        List<CoinTransactionDto> sellList=coinTransactionService.getSumCoinTransactionsByConditions(userNo,market,"SELL",transactionState);

        if(sellList.isEmpty()){
            return new CoinTransactionDto(market,0.0);
        }else{
            return sellList.get(0);
        }

    }

    @GetMapping("/api/transaction/{userNo}/{market}/available")
    public ResponseEntity<CoinTransactionDto> getAvailableQuantity(
            @PathVariable int userNo,
            @PathVariable String market) {

        CoinTransactionDto result = coinTransactionService.getAvailableQuantity(userNo, market);
        return ResponseEntity.ok(result);
    }

//    @DeleteMapping("/api/transaction/me")
//    public ResponseEntity<Void> resetAsset(){
//        try {
//            coinTransactionService.removeAllByUserNo(1);
//            return ResponseEntity.ok().build();
//        }
//        catch(Exception e){
//            return ResponseEntity.badRequest().build();
//
//        }
//    }

    @GetMapping("/api/history/{userNo}")
    public ResponseEntity<List<CoinTransactionDto>> getHistory(@PathVariable int userNo) {
        List<CoinTransactionDto> history = coinTransactionService.getUserCoinHistory(userNo);
        return ResponseEntity.ok(history);
    }
}