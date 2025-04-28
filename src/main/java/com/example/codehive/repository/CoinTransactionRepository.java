package com.example.codehive.repository;


import com.example.codehive.dto.CoinTransactionDto;
import com.example.codehive.dto.BuyCoinTransactionSummaryDto;
import com.example.codehive.dto.SellQuantityDto;
import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CoinTransactionRepository extends JpaRepository<CoinTransaction, Integer> {
    @Query("""
    SELECT c
    FROM CoinTransaction c
    WHERE c.userNo = :userNo
      AND (:transactionType IS NULL OR c.transactionType = :transactionType)
      AND (:transactionState IS NULL OR c.transactionState = :transactionState)
      AND (:market IS NULL OR c.market = :market)
      AND (:startDate IS NULL OR c.transactionDate >= :startDate)
      AND (:endDate IS NULL OR c.transactionDate <= :endDate)
      AND (c.market != 'KRW-KRW')
""")
    Page<CoinTransaction> findFilteredTransactions(
            @Param("userNo") int userNo,
            @Param("transactionType") String transactionType,
            @Param("transactionState") String transactionState,
            @Param("market") String market,
            @Param("startDate") Instant startDate,
            @Param("endDate") Instant endDate,
            Pageable pageable
    );
    List<CoinTransaction> findByUserNo(int userNo);

    List<CoinTransaction> findTransactionStateByUserNo(int userNo);

    // BUY 총 합계
    @Query("SELECT new com.example.codehive.dto.CoinTransactionDto( c.market, SUM(c.transactionCnt)) FROM CoinTransaction c WHERE c.userNo=:userNo AND c.transactionType = 'BUY' AND c.transactionState = 'COMPLETED' GROUP BY c.market")
    List<CoinTransactionDto> findSumCoinTransactionsByUserNoWithBuy(int userNo);

    //BUT 금액합계까지포함
    @Query("SELECT new com.example.codehive.dto.BuyCoinTransactionSummaryDto( c.market, SUM(c.transactionCnt), SUM(c.price * c.transactionCnt)) FROM CoinTransaction c WHERE c.userNo=:userNo AND c.transactionType = 'BUY' AND c.transactionState = 'COMPLETED' GROUP BY c.market")
    List<BuyCoinTransactionSummaryDto> findSummaryCoinTransactionsByUserNoWithBuy(int userNo);

    // SELL 총 합계
    @Query("SELECT new com.example.codehive.dto.CoinTransactionDto( c.market, SUM(c.transactionCnt)) FROM CoinTransaction c WHERE c.userNo=:userNo AND c.transactionType = 'SELL' AND c.transactionState = 'COMPLETED' GROUP BY c.market")
    List<CoinTransactionDto> findSumCoinTransactionsByUserNoWithSell(int userNo);

    @Query("SELECT new com.example.codehive.dto.CoinTransactionDto( c.market, SUM(c.transactionCnt)) FROM CoinTransaction c WHERE c.userNo=:userNo AND c.transactionType = 'BUY' AND c.market = 'KRW-KRW' GROUP BY c.market")
    List<CoinTransactionDto> findSumKRWTransactionsByUserNoWithBuy(int userNo);

    @Query("SELECT new com.example.codehive.dto.CoinTransactionDto( c.market, SUM(c.transactionCnt)) FROM CoinTransaction c WHERE c.userNo=:userNo AND c.transactionType = 'SELL' AND c.market = 'KRW-KRW' GROUP BY c.market")
    List<CoinTransactionDto> findSumKRWTransactionsByUserNoWithSell(int userNo);

    //SELL 수량합만
    @Query("""
            SELECT new com.example.codehive.dto.SellQuantityDto(c.market, SUM(c.transactionCnt))
                        FROM CoinTransaction c
                        WHERE c.userNo = :userNo
                        AND c.transactionType = 'SELL'
                        AND c.transactionState = 'COMPLETED'
                        GROUP BY c.market
            """)
    List<SellQuantityDto> findTotalSellQuantityByUserNo(int userNo);

    //특정코인거래내역찾기
    @Query("""
    SELECT new com.example.codehive.dto.CoinTransactionDto(
        c.market, 
        SUM(c.transactionCnt)
    ) 
    FROM CoinTransaction c 
    WHERE c.userNo = :userNo 
    AND c.market = :market 
    AND c.transactionType = :transactionType 
    AND (:transactionState IS NULL OR c.transactionState = :transactionState) 
    GROUP BY c.market
    """)
    List<CoinTransactionDto> findSumCoinTransactionsByConditions(
            @Param("userNo") int userNo,
            @Param("market") String market,
            @Param("transactionType") String transactionType,
            @Param("transactionState") String transactionState
    );

    @Query("DELETE FROM CoinTransaction c where c.userNo=:userNo")
    @Modifying
    void deleteAllByUserNo(int userNo);

    List<CoinTransaction> findByUserNoAndMarketNotOrderByTransactionDateDesc(int userNo, String market);

    int user(User user);
}