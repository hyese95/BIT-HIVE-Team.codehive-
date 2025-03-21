package com.example.codehive.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class MyAssetDto { // 한 사용자에 대한 전체 자산을 나타내기 위해 매수 내역과 매도 내역을 별도의 리스트로 보유
    private String market;
    private Double quantity;

    private List<CoinTransactionDto> buyTransactions;
    private List<CoinTransactionDto> sellTransactions;
}

