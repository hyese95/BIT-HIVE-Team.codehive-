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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private LocalDateTime transactionDate;
}