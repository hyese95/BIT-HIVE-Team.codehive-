package com.example.codehive.service;

import com.example.codehive.dto.MyAssetDto;
import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.repository.CoinTransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CoinTransactionServiceImp implements CoinTransactionService {
}
