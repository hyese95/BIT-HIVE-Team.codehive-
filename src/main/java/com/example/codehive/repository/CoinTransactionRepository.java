package com.example.codehive.repository;

import com.example.codehive.dto.CoinTransactionDto;
import com.example.codehive.dto.MyAssetDto;
import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinTransactionRepository extends JpaRepository<CoinTransaction, Integer> {
    List<CoinTransaction> findByUserNo(int userNo);

    // BUY 총 합계
    @Query("SELECT new com.example.codehive.dto.CoinTransactionDto( c.market, SUM(c.transactionCnt)) FROM CoinTransaction c WHERE c.userNo=:userNo AND c.transactionType = 'BUY' GROUP BY c.market")
    List<CoinTransactionDto> findSumCoinTransactionsByUserNoWithBuy(int userNo);

    // SELL 총 합계
    @Query("SELECT new com.example.codehive.dto.CoinTransactionDto( c.market, SUM(c.transactionCnt)) FROM CoinTransaction c WHERE c.userNo=:userNo AND c.transactionType = 'SELL'  GROUP BY c.market")
    List<CoinTransactionDto> findSumCoinTransactionsByUserNoWithSell(int userNo);
}
