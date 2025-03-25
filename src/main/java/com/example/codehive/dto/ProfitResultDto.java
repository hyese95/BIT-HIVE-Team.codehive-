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
public class ProfitResultDto {
    // 코인별 상세 계산 결과 리스트
    private List<CoinDetailDto> coinDetails;
    // 전체 구매 평가금액 합계
    private double totalPurchaseValuation;
    // 전체 현재시세 평가금액 합계
    private double totalCurrentValuation;
    // 전체 평가손익
    private double totalProfit;
    // 전체 수익률
    private double overallProfitRate;
}
