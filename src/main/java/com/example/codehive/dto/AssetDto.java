package com.example.codehive.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AssetDto {
    private String market;
    private Double holdingAmount;
    private Double averagePrice;

}
