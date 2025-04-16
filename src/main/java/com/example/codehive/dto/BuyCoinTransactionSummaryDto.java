package com.example.codehive.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class BuyCoinTransactionSummaryDto {

    String market;
    Double sumAmount;//구매거래 수량들의 합
    Double sumPrice;// 거래별 당시 (구매가격 * 수량)의 합

}
