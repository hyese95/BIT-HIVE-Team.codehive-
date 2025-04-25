package com.example.codehive.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CoinTransactionResponseDto {
    private Integer id;
    private String market;
    private String transactionType;
    private Double price;
    private Double transactionCnt;
    private String transactionState;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime transactionDate;
}