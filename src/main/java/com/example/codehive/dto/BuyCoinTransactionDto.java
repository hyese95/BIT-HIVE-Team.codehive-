package com.example.codehive.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class BuyCoinTransactionDto {

    String market;
    Double sumAmount;
    Long sumPrice;

}
