package com.example.codehive.service;

import com.example.codehive.dto.AssetDto;
import com.example.codehive.dto.BuyCoinTransactionDto;
import com.example.codehive.dto.CoinTransactionDto;
import com.example.codehive.dto.SellQuantityDto;
import com.example.codehive.repository.CoinTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public List<AssetDto> readHoldingCoinListByUserNo(int userNo) {
        List<BuyCoinTransactionDto> buySummaries = coinTransactionRepository.findSummaryCoinTransactionsByUserNoWithBuy(userNo);
        List<SellQuantityDto> sellList = coinTransactionRepository.findTotalSellQuantityByUserNo(userNo);

        Map<String, Double> sellMap = sellList.stream()
                .collect(Collectors.toMap(SellQuantityDto::getMarket, SellQuantityDto::getTotalSellAmount));

        List<AssetDto> assetList = new ArrayList<>();
        for (BuyCoinTransactionDto buy : buySummaries) {
            String market = buy.getMarket();
            double buyAmount=buy.getSumAmount();
            double sellAmount=sellMap.getOrDefault(market, 0.0);
            double holdingAmount=buyAmount-sellAmount;
            if(holdingAmount<=0) continue;
            double averagePrice = (buyAmount == 0) ? 0.0 : buy.getSumPrice() / buyAmount;

            assetList.add(new AssetDto(market,holdingAmount,averagePrice));
        }
        return assetList;
    }
}
