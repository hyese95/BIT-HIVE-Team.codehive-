package com.example.codehive.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TradeRequestDto {

    @JsonIgnore
    public Integer userNo;

    public String market;
    public String transactionType;
    public Double price;
    public Double transactionCnt;

}
