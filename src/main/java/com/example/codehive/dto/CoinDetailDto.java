package com.example.codehive.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CoinDetailDto {
    // 코인명
    private String market;
    // 가중평균 매수 단가 (구매 수량*가격 총합 / 총 구매 수량)
    private double buyAvgPrice;
    // 잔여 수량 = 총 구매 수량 - 총 판매 수량
    private double holdingQty;
    // 구매 평가금액 = 가중평균 매수 단가 * 잔여 수량
    private double purchaseValuation;
    // 현재시세 평가금액 = 현재가 * 잔여 수량
    private double currentValuation;
    // 평가손익 = 현재시세 평가금액 - 구매 평가금액
    private double profit;
    // 수익률 = (평가손익 / 구매 평가금액) * 100
    private double profitRate;
}