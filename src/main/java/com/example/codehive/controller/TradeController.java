package com.example.codehive.controller;

import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.entity.FavoriteMarket;
import com.example.codehive.service.CoinTransactionService;
import com.example.codehive.service.FavoriteCoinMarketService;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/trade")
@AllArgsConstructor
public class TradeController {
    private final FavoriteCoinMarketService favoriteCoinMarketService;
    private final CoinTransactionService coinTransactionService;

    // user 1이 로그인 했다고 가정한 상태
    @GetMapping("main.do")
    public String main(Model model) {
        List<CoinTransaction> myAsset = coinTransactionService.readByUserNo(1);
        model.addAttribute("myAsset", myAsset);
        return "trade/main";
    }

    // user 1이 로그인 했다고 가정한 상태
    @GetMapping("/{userNo}/main.do")
    @ResponseBody
    public List<FavoriteMarket> main(@PathVariable int userNo) {
        List<FavoriteMarket> favoriteMarketList = favoriteCoinMarketService.readByUserNo(1);
        return favoriteMarketList;
    }

    @GetMapping("buy.do")
    public String coinOrder(Model model) {
        return "trade/buy";
    }

    @GetMapping("/favorite_coin.do")
    public String favoriteMarket(Model model) {
                List<FavoriteMarket> favoriteMarketList = favoriteCoinMarketService.readByUserNo(1);
        model.addAttribute("favoriteMarketList", favoriteMarketList);

    return "trade/favorite_coin";
    }

    @GetMapping("/holding_coin.do")
    public String holdingCoin(Model model) {
        List<FavoriteMarket> myAssetList = favoriteCoinMarketService.readByUserNo(1);
        model.addAttribute("myAssetList", myAssetList);
        return "trade/holding_coin";
    }

    @GetMapping("/orderbook/{market}")
    @ResponseBody
    public Map<String, Object> getOrderBookData(@PathVariable String market) {
        String orderbookUrl = "https://api.upbit.com/v1/orderbook?markets=" + market;
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<List<Map<String, Object>>> response = restTemplate.exchange(
                    orderbookUrl, HttpMethod.GET, null,
                    new ParameterizedTypeReference<List<Map<String, Object>>>() {}
            );
            if (response.getBody() != null && !response.getBody().isEmpty()) {
                return response.getBody().get(0);
            } else {
                throw new RuntimeException("No data found for market: " + market);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to fetch orderbook data for market: " + market);
        }
    }}
