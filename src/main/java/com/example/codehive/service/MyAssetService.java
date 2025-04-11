package com.example.codehive.service;

import com.example.codehive.dto.AssetDto;

import java.util.List;
import java.util.Map;

public interface MyAssetService {
    Map<String,Double> readAssetByUserNo(int userNo);
    List<AssetDto> readHoldingCoinListByUserNo(int userNo);
}
