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
public class MyAssetDto {
    private String market;
    private Double quantity;

    private List<CoinTransactionDto> buyTransactions;
    private List<CoinTransactionDto> sellTransactions;
}

