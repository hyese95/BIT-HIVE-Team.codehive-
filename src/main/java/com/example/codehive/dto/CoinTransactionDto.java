package com.example.codehive.dto;

import com.example.codehive.entity.CoinTransaction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CoinTransactionDto {
    private int transNo;
    private String market;
    private String transactionType;
    private double price;
    private double transactionCnt;
    private String transactionState;
    private LocalDateTime transactionDate;
    private Double quantity;

    // 전체 거래 내역 조회 시 사용하는 변환 메서드
    public static CoinTransactionDto fromEntity(CoinTransaction entity) {
        CoinTransactionDto dto = new CoinTransactionDto();
        dto.transNo = entity.getId();
        dto.market = entity.getMarket();
        dto.transactionType = entity.getTransactionType();
        dto.price = entity.getPrice();
        dto.transactionCnt = entity.getTransactionCnt();
        dto.transactionState = entity.getTransactionState();
        dto.transactionDate = entity.getTransactionDate()
                != null ? entity.getTransactionDate().atZone(java.time.ZoneId.systemDefault()).toLocalDateTime() : null;
        return dto;
    }

    // 총합 DTO 용 생성자
    public CoinTransactionDto(String market, Double quantity) {
        this.market = market;
        this.quantity = quantity;
    }
}