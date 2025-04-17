package com.example.codehive.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyAssetServiceImpTest {
    @Autowired
    MyAssetService myAssetService;

    @Test

    void readAssetByUserNo() {
        System.out.println(myAssetService.readAssetByUserNo(1));



    }

    @Test
    void readHoldingCoinListByUserNo() {
        System.out.println(myAssetService.readHoldingCoinListByUserNo(1));
    }
}