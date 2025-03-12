package com.example.codehive.service;

import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.repository.CoinTransactionRepository;

import java.util.List;

public interface CoinTransactionService {
    List<CoinTransaction> readByUserNo(int userNo);
}
