package com.example.codehive.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("korean_name")
    private String koreanName;

    public CoinNameDto(String market, String koreanName) {
        this.market = market;
        this.koreanName = koreanName;
        this.ticker = market.replace("KRW-", "");
    }
}

