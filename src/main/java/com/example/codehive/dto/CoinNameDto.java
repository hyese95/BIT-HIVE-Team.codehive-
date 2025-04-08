package com.example.codehive.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CoinNameDto {
    private String market;
    private String ticker;
    private String koreanName;

    public CoinNameDto(String market, String koreanName) {
        this.market = market;
        this.koreanName = koreanName;
        this.ticker = market.replace("KRW-", "");
    }
}

