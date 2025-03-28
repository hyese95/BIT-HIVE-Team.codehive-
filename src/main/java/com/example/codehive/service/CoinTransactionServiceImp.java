package com.example.codehive.service;

import com.example.codehive.dto.CoinDetailDto;
import com.example.codehive.dto.ProfitResultDto;
import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.repository.CoinTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CoinTransactionServiceImp implements CoinTransactionService {

    private final CoinTransactionRepository coinTransactionRepository;

    @Override
    public ProfitResultDto calculateProfit(int userNo, Map<String, Double> currentPriceMap) {
        // 모든 거래 내역 조회 후, COMPLETED 상태인 BUY/SELL 거래만 필터링
        List<CoinTransaction> allTransactions = coinTransactionRepository.findByUserNo(userNo);

        List<CoinTransaction> buyTransactions = allTransactions.stream()
                .filter(tx -> "BUY".equalsIgnoreCase(tx.getTransactionType()) &&
                        "COMPLETED".equalsIgnoreCase(tx.getTransactionState()))
                .collect(Collectors.toList());

        List<CoinTransaction> sellTransactions = allTransactions.stream()
                .filter(tx -> "SELL".equalsIgnoreCase(tx.getTransactionType()) &&
                        "COMPLETED".equalsIgnoreCase(tx.getTransactionState()))
                .collect(Collectors.toList());

        // 코인별 그룹화 (BUY, SELL 각각)
        Map<String, List<CoinTransaction>> buyMap = buyTransactions.stream()
                .collect(Collectors.groupingBy(CoinTransaction::getMarket));
        Map<String, List<CoinTransaction>> sellMap = sellTransactions.stream()
                .collect(Collectors.groupingBy(CoinTransaction::getMarket));

        List<CoinDetailDto> coinDetails = new ArrayList<>();
        double totalPurchaseValuation = 0;
        double totalCurrentValuation = 0;

        // 각 코인별 계산 (단, KRW-KRW는 제외)
        for (String market : buyMap.keySet()) {
            if ("KRW-KRW".equalsIgnoreCase(market)) {
                continue;
            }
            List<CoinTransaction> coinBuys = buyMap.get(market);
            double totalBuyQty = coinBuys.stream().mapToDouble(CoinTransaction::getTransactionCnt).sum();
            double totalBuyAmount = coinBuys.stream().mapToDouble(tx -> tx.getPrice() * tx.getTransactionCnt()).sum();
            double weightedAvgPrice = totalBuyAmount / totalBuyQty;

            double totalSellQty = 0;
            if (sellMap.containsKey(market)) {
                totalSellQty = sellMap.get(market).stream().mapToDouble(CoinTransaction::getTransactionCnt).sum();
            }
            double remainingQty = totalBuyQty - totalSellQty;
            if (remainingQty <= 0) continue;  // 보유 수량이 없으면 건너뜀

            double purchaseValuation = weightedAvgPrice * remainingQty;
            Double currentPrice = currentPriceMap.get(market);
            if (currentPrice == null) continue;  // 현재가 정보가 없으면 계산 제외

            double currentValuation = currentPrice * remainingQty;
            double profit = currentValuation - purchaseValuation;
            double profitRate = purchaseValuation != 0 ? (profit / purchaseValuation) * 100 : 0;

            coinDetails.add(new CoinDetailDto(
                    market,
                    weightedAvgPrice,
                    remainingQty,
                    purchaseValuation,
                    currentValuation,
                    profit,
                    profitRate
            ));

            totalPurchaseValuation += purchaseValuation;
            totalCurrentValuation += currentValuation;
        }

        double totalProfit = totalCurrentValuation - totalPurchaseValuation;
        double overallProfitRate = totalPurchaseValuation != 0 ? (totalProfit / totalPurchaseValuation) * 100 : 0;

        return new ProfitResultDto(coinDetails, totalPurchaseValuation, totalCurrentValuation, totalProfit, overallProfitRate);
    }

    @Override
    public void saveCoinTransaction(CoinTransaction coinTransaction) {
        coinTransactionRepository.save(coinTransaction);
    }
}
