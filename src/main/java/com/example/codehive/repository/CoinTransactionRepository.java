package com.example.codehive.repository;

import com.example.codehive.dto.CoinTransactionAmountDto;
import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CoinTransactionRepository extends JpaRepository<CoinTransaction, Integer> {
    List<CoinTransaction> findByUserNo(int userNo);
    List<CoinTransaction> findByUserNoAndMarketAndTransactionTypeAndTransactionState
            (int userNo, String market, String transactionType, String transactionState);
//    @Query("SELECT new com.example.codehive.dto.CoinTransactionAmountDto( c.market, SUM(c.transactionCnt)) FROM CoinTransaction c WHERE c.userNo=:userNo GROUP BY c.market")
//    List<CoinTransactionAmountDto> findSumCoinTransactionsByUserNo(int userNo);
    @Query("SELECT new com.example.codehive.dto.CoinTransactionAmountDto( c.market, SUM(c.transactionCnt)) FROM CoinTransaction c WHERE c.userNo=:userNo AND c.transactionType = 'BUY' AND c.market = 'KRW-KRW' GROUP BY c.market")
    List<CoinTransactionAmountDto> findSumCoinTransactionsByUserNo(int userNo);
}
