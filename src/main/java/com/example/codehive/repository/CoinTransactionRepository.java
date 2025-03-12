package com.example.codehive.repository;

import com.example.codehive.entity.CoinTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoinTransactionRepository extends JpaRepository<CoinTransaction, Integer> {
    List<CoinTransaction> findByUserNo(int userNo);
}
