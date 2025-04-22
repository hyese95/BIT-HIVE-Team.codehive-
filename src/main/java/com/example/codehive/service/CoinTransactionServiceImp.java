package com.example.codehive.service;

import com.example.codehive.dto.CoinDetailDto;
import com.example.codehive.dto.CoinTransactionDto;
import com.example.codehive.dto.ProfitResultDto;
import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.repository.CoinTransactionRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CoinTransactionServiceImp implements CoinTransactionService {
    private final EntityManager entityManager;
    private final CoinTransactionRepository coinTransactionRepository;

    @Override
    public List<CoinTransaction> findByUserNo(int userNo) {
        return coinTransactionRepository
                .findByUserNo(userNo)
                .stream()
                .filter(tr -> !"KRW-KRW".equals(tr.getMarket()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CoinTransaction> findTransactionStateByUserNo(int userNo) {
        return coinTransactionRepository
                .findTransactionStateByUserNo(userNo)
                .stream()
                .filter(tr -> !"KRW-KRW".equals(tr.getMarket()))
                .filter(tr -> "PENDING".equals(tr.getTransactionState()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CoinTransaction> getFilteredTransactions(int userNo, String type, String state, String market, LocalDateTime start, LocalDateTime end) {
        return coinTransactionRepository.findByUserNo(userNo).stream()
                .filter(tx -> type == null || tx.getTransactionType().equalsIgnoreCase(type))
                .filter(tx -> state == null || tx.getTransactionState().equalsIgnoreCase(state))
                .filter(tx -> market == null || tx.getMarket().equalsIgnoreCase(market))
                .filter(tx -> start == null || !tx.getTransactionDate().isBefore(start.atZone(ZoneId.systemDefault()).toInstant()))
                .filter(tx -> end == null || !tx.getTransactionDate().isAfter(end.atZone(ZoneId.systemDefault()).toInstant()))
                .filter(tx -> !"KRW-KRW".equalsIgnoreCase(tx.getMarket()))
                .collect(Collectors.toList());
    }


    @Override
    public ProfitResultDto calculateProfit(int userNo, Map<String, Double> currentPriceMap) {
        // 1. 해당 유저의 전체 거래 내역 조회
        List<CoinTransaction> allTransactions = coinTransactionRepository.findByUserNo(userNo);

        // 2. 매수 / 매도 거래만 각각 필터링
        List<CoinTransaction> buyTransactions = allTransactions.stream()
                .filter(tx -> "BUY".equalsIgnoreCase(tx.getTransactionType()) &&
                        "COMPLETED".equalsIgnoreCase(tx.getTransactionState()))
                .collect(Collectors.toList());

        List<CoinTransaction> sellTransactions = allTransactions.stream()
                .filter(tx -> "SELL".equalsIgnoreCase(tx.getTransactionType()) &&
                        "COMPLETED".equalsIgnoreCase(tx.getTransactionState()))
                .collect(Collectors.toList());

        // 3. 코인 마켓별로 그룹핑
        Map<String, List<CoinTransaction>> buyMap = buyTransactions.stream()
                .collect(Collectors.groupingBy(CoinTransaction::getMarket));
        Map<String, List<CoinTransaction>> sellMap = sellTransactions.stream()
                .collect(Collectors.groupingBy(CoinTransaction::getMarket));


        List<CoinDetailDto> coinDetails = new ArrayList<>();
        double totalPurchaseValuation = 0.0; // 전체 매입 금액
        double totalCurrentValuation = 0.0;  // 전체 현재 평가 금액

        for (String market : buyMap.keySet()) {
            // 예외 처리: KRW 자체 마켓은 계산 제외
            if ("KRW-KRW".equalsIgnoreCase(market)) continue;
            List<CoinTransaction> coinBuys = buyMap.get(market);


            // 4. 총 매수 수량 계산
            double totalBuyQty = 0.0;
            double totalBuyAmount = 0.0;
            for (CoinTransaction tx : coinBuys) {
                double qty = tx.getTransactionCnt();
                double amount = tx.getPrice() * qty;
                totalBuyQty += qty;
                totalBuyAmount += amount;
            }

            // 5. 평균 매수 단가 계산 (divide by zero 방지)
            if (totalBuyQty == 0) continue;
            double buyAvgPrice = totalBuyAmount / totalBuyQty;

            // 6. 해당 코인의 총 매도 수량 계산
            double totalSellQty = 0.0;
            if (sellMap.containsKey(market)) {
                for (CoinTransaction tx : sellMap.get(market)) {
                    totalSellQty += tx.getTransactionCnt();
                }
            }

            // 7. 현재 보유 수량 = 총 매수 - 총 매도
            double remainingQty = totalBuyQty - totalSellQty;
            if (remainingQty <= 0) continue; // 남은 수량 없으면 스킵

            // 8. 매입 평가 금액 = 평균단가 * 잔여 수량
            double purchaseValuation = buyAvgPrice * remainingQty;

            // 9. 현재 시세가 없는 경우 스킵
            Double currentPrice = currentPriceMap.get(market);
            if (currentPrice == null) continue;

            // 10. 현재 평가 금액 = 현재가 * 잔여 수량
            double currentValuation = currentPrice * remainingQty;

            // 11. 수익 및 수익률 계산
            double profit = currentValuation - purchaseValuation;
            double profitRate = purchaseValuation != 0 ? (profit / purchaseValuation) * 100 : 0;

            // 12. 각 코인별 상세 정보 저장 (필요하다면 Math.round 등 반올림 처리 추가 가능)
            coinDetails.add(new CoinDetailDto(
                    market,
                    buyAvgPrice,
                    remainingQty,
                    purchaseValuation,
                    currentValuation,
                    profit,
                    profitRate
            ));

            // 13. 전체 합산
            totalPurchaseValuation += purchaseValuation;
            totalCurrentValuation += currentValuation;
        }

        // 14. 전체 수익 및 수익률 계산
        double totalProfit = totalCurrentValuation - totalPurchaseValuation;
        double overallProfitRate = totalPurchaseValuation != 0 ? (totalProfit / totalPurchaseValuation) * 100 : 0;

        return new ProfitResultDto(coinDetails, totalPurchaseValuation, totalCurrentValuation, totalProfit, overallProfitRate);
    }

    @Override
    public void saveCoinTransaction(CoinTransaction coinTransaction) {
        coinTransactionRepository.save(coinTransaction);
    }

    @Override
    public List<CoinTransactionDto> getSumCoinTransactionsByConditions(int userNo, String market, String transactionType, String transactionState) {
        return coinTransactionRepository.findSumCoinTransactionsByConditions(userNo, market, transactionType, transactionState);
    }

    @Override
    public CoinTransactionDto getAvailableQuantity(int userNo, String market) {
        List<CoinTransactionDto> buyList = coinTransactionRepository.findSumCoinTransactionsByConditions(
                userNo, market, "BUY", "COMPLETED");

        List<CoinTransactionDto> sellList = coinTransactionRepository.findSumCoinTransactionsByConditions(
                userNo, market, "SELL", "COMPLETED");

        double buySum = (buyList.isEmpty() || buyList.get(0).getQuantity() == null) ? 0.0 : buyList.get(0).getQuantity();
        double sellSum = (sellList.isEmpty() || sellList.get(0).getQuantity() == null) ? 0.0 : sellList.get(0).getQuantity();
        double available = buySum - sellSum;

        return new CoinTransactionDto(market, available);
    }

    @Override
    @Transactional
    public void removeAllByUserNo(int userNo) {
        coinTransactionRepository.deleteAllByUserNo(userNo);

    }

    @Override
    public List<CoinTransactionDto> getUserCoinHistory(int userNo) {
        List<CoinTransaction> list = coinTransactionRepository.findByUserNoAndMarketNotOrderByTransactionDateDesc(userNo, "KRW-KRW");
        return list.stream()
                .map(CoinTransactionDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void remove(int id) {
        CoinTransaction removePending = entityManager.find(CoinTransaction.class, id);
        if (removePending == null) throw new IllegalArgumentException("취소할 미체결 주문이 없습니다.");
        entityManager.remove(removePending);
    }

    @Override
    @Transactional
    public void removeTransactionPendingByUserNo(int userNo) {
        List<CoinTransaction> pendingOrders = entityManager.createQuery(
                        "SELECT ct FROM CoinTransaction ct WHERE ct.userNo = :userNo AND ct.transactionState = 'PENDING'",
                        CoinTransaction.class)
                .setParameter("userNo", userNo)
                .getResultList();

        if (pendingOrders.isEmpty()) {
            throw new IllegalArgumentException("취소할 미체결 주문이 없습니다.");
        }

        for (CoinTransaction tx : pendingOrders) {
            entityManager.remove(tx);
        }
    }

    @Override
    @Transactional
    public void removeAllTransactionsByUserNo(int userNo) {
        List<CoinTransaction> allTransactions = entityManager.createQuery(
                        "SELECT ct FROM CoinTransaction ct WHERE ct.userNo =: userNo",
                        CoinTransaction.class)
                .setParameter("userNo", userNo)
                .getResultList();
        if (allTransactions.isEmpty()) {
            throw new IllegalArgumentException("초기화할 보유자산이 없습니다.");
        }
        for (CoinTransaction tx : allTransactions) {
            entityManager.remove(tx);
        }

    }
}