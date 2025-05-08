package com.example.codehive.service;

import com.example.codehive.dto.*;
import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.repository.CoinTransactionRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
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
    public Page<CoinTransactionResponseDto> getFilteredTransactionDtos(
            int userNo, String transactionType, String transactionState,
            String market, Instant startDate, Instant endDate, Pageable pageable) {

        Page<CoinTransaction> page = coinTransactionRepository.findFilteredTransactions(
                userNo, transactionType, transactionState, market, startDate, endDate, pageable
        );

        return page.map(tx -> new CoinTransactionResponseDto(
                tx.getId(),
                tx.getMarket(),
                tx.getTransactionType(),
                tx.getPrice(),
                tx.getTransactionCnt(),
                tx.getTransactionState(),
                LocalDateTime.ofInstant(tx.getTransactionDate(), ZoneId.of("Asia/Seoul"))
        ));
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

    @Override
    @Transactional
    public void register(CoinTransaction coinTransaction) {
        entityManager.persist(coinTransaction);
    }

    @Override
    @Transactional
    public void submitTrade(TradeRequestDto tradeRequestDto) {
        switch (tradeRequestDto.transactionType) {
            case "BUY" -> handleBuy(tradeRequestDto);
            case "SELL" -> handleSell(tradeRequestDto);
            default -> throw new IllegalArgumentException("주문유형 오류");

        }

    }

    public void handleBuy(TradeRequestDto tradeRequestDto) {
        Integer userNo = tradeRequestDto.getUserNo();
        String market = tradeRequestDto.getMarket();
        String transactionType = tradeRequestDto.getTransactionType();
        Double price = tradeRequestDto.getPrice();
        Double transactionCnt = tradeRequestDto.getTransactionCnt();
        Instant now = Instant.now();

        //코인거래요청처리
        CoinTransaction coinTransaction = new CoinTransaction();
        coinTransaction.setUserNo(userNo);
        coinTransaction.setMarket(market);
        coinTransaction.setTransactionType(transactionType);
        coinTransaction.setPrice(price);
        coinTransaction.setTransactionCnt(transactionCnt);
        coinTransaction.setTransactionDate(now);
        coinTransaction.setTransactionState("PENDING");
        coinTransactionRepository.save(coinTransaction);

        //디파짓처리,즉시처리
        CoinTransaction depositTransaction = new CoinTransaction();
        depositTransaction.setUserNo(userNo);
        depositTransaction.setMarket("KRW-KRW");
        depositTransaction.setTransactionType("SELL");
        depositTransaction.setPrice(1.0);
        depositTransaction.setTransactionCnt(transactionCnt * price);
        depositTransaction.setTransactionDate(now);
        depositTransaction.setTransactionState("COMPLETED");
        coinTransactionRepository.save(depositTransaction);
    }

    public void handleSell(TradeRequestDto tradeRequestDto) {
        Integer userNo = tradeRequestDto.getUserNo();
        String market = tradeRequestDto.getMarket();
        String transactionType = tradeRequestDto.getTransactionType();
        Double price = tradeRequestDto.getPrice();
        Double transactionCnt = tradeRequestDto.getTransactionCnt();
        Instant now = Instant.now();

        //코인거래요청처리
        CoinTransaction coinTransaction = new CoinTransaction();
        coinTransaction.setUserNo(userNo);
        coinTransaction.setMarket(market);
        coinTransaction.setTransactionType(transactionType);
        coinTransaction.setPrice(price);
        coinTransaction.setTransactionCnt(transactionCnt);
        coinTransaction.setTransactionDate(now);
        coinTransaction.setTransactionState("PENDING");
        coinTransactionRepository.save(coinTransaction);

        //디파짓처리,즉시처리
        CoinTransaction depositTransaction = new CoinTransaction();
        depositTransaction.setUserNo(userNo);
        depositTransaction.setMarket("KRW-KRW");
        depositTransaction.setTransactionType("BUY");
        depositTransaction.setPrice(1.0);
        depositTransaction.setTransactionCnt(transactionCnt * price);
        depositTransaction.setTransactionDate(now);
        depositTransaction.setTransactionState("PENDING");
        coinTransactionRepository.save(depositTransaction);
    }

    @Override
    public double getAvailableCoinQuantity(int userNo, String market) {
        // "KRW-KRW"가 아닌 코인에 대해서만 적용
        if ("KRW-KRW".equals(market)) {
            throw new IllegalArgumentException("KRW 디파짓 조회는 getAvailableDeposit() 메서드를 사용하세요.");
        }
        
        try {
            // 매수 완료된 수량 조회
            List<CoinTransactionDto> buyList = coinTransactionRepository.findSumCoinTransactionsByConditions(
                    userNo, market, "BUY", "COMPLETED");
            
            // 매도 완료된 수량 조회
            List<CoinTransactionDto> sellCompletedList = coinTransactionRepository.findSumCoinTransactionsByConditions(
                    userNo, market, "SELL", "COMPLETED");
            
            // 매도 대기중인 수량 조회 (PENDING)
            List<CoinTransactionDto> sellPendingList = coinTransactionRepository.findSumCoinTransactionsByConditions(
                    userNo, market, "SELL", "PENDING");
            
            // 매수량 합계 (없으면 0)
            double buySum = (buyList.isEmpty() || buyList.get(0).getQuantity() == null) ? 0.0 : buyList.get(0).getQuantity();
            
            // 매도 완료 합계 (없으면 0) 
            double sellCompletedSum = (sellCompletedList.isEmpty() || sellCompletedList.get(0).getQuantity() == null) ? 
                    0.0 : sellCompletedList.get(0).getQuantity();
            
            // 매도 대기 합계 (없으면 0)
            double sellPendingSum = (sellPendingList.isEmpty() || sellPendingList.get(0).getQuantity() == null) ? 
                    0.0 : sellPendingList.get(0).getQuantity();
            
            // 가용 수량 = 매수 완료 - (매도 완료 + 매도 대기)
            return Math.max(0, buySum - (sellCompletedSum + sellPendingSum));  // 음수 방지
        } catch (Exception e) {
            // 예외 발생 시 로그를 남기고 0 반환
            System.err.println("거래 내역 조회 중 오류 발생: " + e.getMessage());
            return 0.0;
        }
    }
    
    @Override
    public double getAvailableDeposit(int userNo) {
        try {
            // KRW-KRW 마켓의 COMPLETED BUY 조회 (입금 완료)
            List<CoinTransactionDto> buyList = coinTransactionRepository.findSumCoinTransactionsByConditions(
                    userNo, "KRW-KRW", "BUY", "COMPLETED");
            
            // KRW-KRW 마켓의 COMPLETED SELL 조회 (사용 완료)
            List<CoinTransactionDto> completedSellList = coinTransactionRepository.findSumCoinTransactionsByConditions(
                    userNo, "KRW-KRW", "SELL", "COMPLETED");
            
            // KRW-KRW 마켓의 PENDING SELL 조회 (사용 예정)
            List<CoinTransactionDto> pendingSellList = coinTransactionRepository.findSumCoinTransactionsByConditions(
                    userNo, "KRW-KRW", "SELL", "PENDING");
            
            // 입금 완료 합계 (없으면 0)
            double buySum = (buyList.isEmpty() || buyList.get(0).getQuantity() == null) ? 0.0 : buyList.get(0).getQuantity();
            
            // 사용 완료 합계 (없으면 0)
            double completedSellSum = (completedSellList.isEmpty() || completedSellList.get(0).getQuantity() == null) ? 
                    0.0 : completedSellList.get(0).getQuantity();
            
            // 사용 예정 합계 (없으면 0)
            double pendingSellSum = (pendingSellList.isEmpty() || pendingSellList.get(0).getQuantity() == null) ? 
                    0.0 : pendingSellList.get(0).getQuantity();
            
            // 가용 디파짓 = 입금 완료 - (사용 완료 + 사용 예정)
            return Math.max(0, buySum - (completedSellSum + pendingSellSum));  // 음수 방지
        } catch (Exception e) {
            // 예외 발생 시 로그를 남기고 0 반환
            System.err.println("디파짓 조회 중 오류 발생: " + e.getMessage());
            return 0.0;
        }
    }
    
    /**
     * PENDING 주문 상태를 COMPLETED로 업데이트하는 메서드
     * @param transaction 업데이트할 트랜잭션
     */
    @Transactional
    public void updateTransactionToCompleted(CoinTransaction transaction) {
        transaction.setTransactionState("COMPLETED");
        coinTransactionRepository.save(transaction);
    }
    
    /**
     * 특정 마켓의 PENDING 상태인 거래 내역을 조회하는 메서드
     * @param market 마켓 코드 (예: KRW-BTC)
     * @return PENDING 상태인 거래 내역 목록
     */
    public List<CoinTransaction> findPendingTransactionsByMarket(String market) {
        return coinTransactionRepository.findByMarketAndTransactionState(market, "PENDING");
    }
    
    /**
     * 모든 PENDING 상태인 거래 내역을 조회하는 메서드
     * @return PENDING 상태인 거래 내역 목록
     */
    public List<CoinTransaction> findAllPendingTransactions() {
        return coinTransactionRepository.findByTransactionState("PENDING");
    }
    
    /**
     * 매도 거래가 체결되었을 때 관련된 KRW-KRW 디파짓 요청도 체결 상태로 변경
     * @param sellTransaction 체결된 매도 거래
     */
    @Transactional
    public void completeRelatedDeposit(CoinTransaction sellTransaction) {
        if (!"SELL".equals(sellTransaction.getTransactionType()) || !"COMPLETED".equals(sellTransaction.getTransactionState())) {
            return;
        }
        
        // 매도 거래와 연관된 디파짓 트랜잭션 찾기 (동일한 시간대 근처, 동일한 사용자, 금액 일치)
        Instant txTime = sellTransaction.getTransactionDate();
        Instant startTime = txTime.minusSeconds(5); // 5초 전
        Instant endTime = txTime.plusSeconds(5);    // 5초 후
        
        List<CoinTransaction> relatedDeposits = coinTransactionRepository.findRelatedDeposits(
                sellTransaction.getUserNo(),
                "KRW-KRW",
                "BUY",
                "PENDING",
                sellTransaction.getPrice() * sellTransaction.getTransactionCnt(),
                startTime,
                endTime
        );
        
        // 일치하는 디파짓 트랜잭션을 COMPLETED로 업데이트
        for (CoinTransaction deposit : relatedDeposits) {
            deposit.setTransactionState("COMPLETED");
            coinTransactionRepository.save(deposit);
        }
    }
}