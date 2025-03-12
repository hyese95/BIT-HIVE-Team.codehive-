package com.example.codehive.service;

import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.repository.CoinTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CoinTransactionServiceImp implements CoinTransactionService {
    private final CoinTransactionRepository coinTransactionRepository;

    @Override
    public List<CoinTransaction> readByUserNo(int userNo) {
        return coinTransactionRepository.findByUserNo(userNo);
    }
}
