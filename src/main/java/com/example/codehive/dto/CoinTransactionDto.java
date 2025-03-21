package com.example.codehive.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CoinTransactionDto { // BUY, SELL 에서의 각 마켓별 거래량의 총합을 저장
    private String market;
    private Double quantity;
}
