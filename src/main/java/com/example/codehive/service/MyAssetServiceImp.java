package com.example.codehive.service;

import com.example.codehive.dto.CoinTransactionDto;
import com.example.codehive.repository.CoinTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class MyAssetServiceImp implements MyAssetService {

    CoinTransactionRepository coinTransactionRepository;

    @Override
    public Map<String, Double> readAssetByUserNo(int userNo) {
        Map<String, Double> myAsset = new HashMap<>();
        List<CoinTransactionDto> buyTransactions = coinTransactionRepository.findSumCoinTransactionsByUserNoWithBuy(userNo);
        List<CoinTransactionDto> sellTransactions = coinTransactionRepository.findSumCoinTransactionsByUserNoWithSell(userNo);

        for (CoinTransactionDto buy : buyTransactions) {
            myAsset.put(buy.getMarket(), buy.getQuantity());
        }
        for (CoinTransactionDto sell : sellTransactions) {
            myAsset.put(sell.getMarket(), myAsset.getOrDefault(sell.getMarket(), 0.0) - sell.getQuantity());
        }
        return myAsset;
    }


}
