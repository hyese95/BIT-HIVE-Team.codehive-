package com.example.codehive.service;

import com.example.codehive.dto.CoinTransactionDto;
import com.example.codehive.dto.CoinTransactionResponseDto;
import com.example.codehive.dto.ProfitResultDto;
import com.example.codehive.dto.TradeRequestDto;
import com.example.codehive.entity.CoinTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface CoinTransactionService {
    List<CoinTransaction> findByUserNo(int userNo);
    List<CoinTransaction> findTransactionStateByUserNo(int userNo); // 유저 미체결 transaction
    Page<CoinTransactionResponseDto> getFilteredTransactionDtos(int userNo, String transactionType, String transactionState, String market, Instant startDate, Instant endDate, Pageable pageable);
    ProfitResultDto calculateProfit(int userNo, Map<String, Double> currentPriceMap);
    void saveCoinTransaction(CoinTransaction coinTransaction);
    List<CoinTransactionDto> getSumCoinTransactionsByConditions(int userNo, String market, String transactionType, String transactionState);
    CoinTransactionDto getAvailableQuantity(int userNo, String market);
    void removeAllByUserNo(int userNo);

    List<CoinTransactionDto> getUserCoinHistory(int userNo);

    void remove(int id);
    void removeTransactionPendingByUserNo(int userNo);
    void removeAllTransactionsByUserNo(int userNo);
    void register(CoinTransaction coinTransaction);
    void submitTrade(TradeRequestDto tradeRequestDto);

    /**
     * 특정 유저의 특정 코인의 가용 수량을 확인하는 메서드
     * @param userNo 유저 번호
     * @param market 마켓 코드 (예: BTC-KRW)
     * @return 가용 수량
     */
    double getAvailableCoinQuantity(int userNo, String market);
    
    /**
     * 특정 유저의 KRW 디파짓 잔액을 확인하는 메서드
     * (COMPLETED BUY - (COMPLETED SELL + PENDING SELL))
     * @param userNo 유저 번호
     * @return 가용 KRW 디파짓 잔액
     */
    double getAvailableDeposit(int userNo);
    
    /**
     * 특정 마켓의 PENDING 상태인 거래 내역을 조회하는 메서드
     * @param market 마켓 코드 (예: KRW-BTC)
     * @return PENDING 상태인 거래 내역 목록
     */
    List<CoinTransaction> findPendingTransactionsByMarket(String market);
    
    /**
     * 모든 PENDING 상태인 거래 내역을 조회하는 메서드
     * @return PENDING 상태인 거래 내역 목록
     */
    List<CoinTransaction> findAllPendingTransactions();
    
    /**
     * PENDING 주문 상태를 COMPLETED로 업데이트하는 메서드
     * @param transaction 업데이트할 트랜잭션
     */
    void updateTransactionToCompleted(CoinTransaction transaction);
    
    /**
     * 매도 거래가 체결되었을 때 관련된 KRW-KRW 디파짓 요청도 체결 상태로 변경
     * @param sellTransaction 체결된 매도 거래
     */
    void completeRelatedDeposit(CoinTransaction sellTransaction);
}

