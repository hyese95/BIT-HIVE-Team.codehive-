package com.example.codehive.controller;


import com.example.codehive.dto.CoinTransactionDto;
import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.repository.CoinTransactionRepository;
import com.example.codehive.service.CoinTransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class CoinTransactionApiController {


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
}