package com.example.codehive.controller;

import com.example.codehive.dto.ProfitResultDto;
import com.example.codehive.service.CoinTransactionService;
import com.example.codehive.service.MyAssetService;
import com.example.codehive.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/asset")
@AllArgsConstructor
public class AssetController {
    private final CoinTransactionService coinTransactionService;
    private final MyAssetService myAssetService;
    private final PriceService priceService;

    @GetMapping("my_asset.do")
    public String myAsset(Model model) {
        Map<String, Double> myAssetMap = myAssetService.readAssetByUserNo(1);
        Map<String, Double> currentMap = new HashMap<>();

        for (String market : myAssetMap.keySet()) {
            double price = priceService.getCoinPrice(market);
            currentMap.put(market, price);
        }
        ProfitResultDto profitResultDto = coinTransactionService.calculateProfit(1, currentMap);
        model.addAttribute("profitResultDto", profitResultDto);

        return "asset/my_asset";
    }

}
