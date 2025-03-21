package com.example.codehive.controller;

import com.example.codehive.dto.CoinTransactionDto;
import com.example.codehive.dto.ProfitResult;
import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.entity.FavoriteMarket;
import com.example.codehive.repository.CoinTransactionRepository;
import com.example.codehive.service.CoinTransactionService;
import com.example.codehive.service.FavoriteCoinMarketService;
import com.example.codehive.service.MyAssetService;
import com.example.codehive.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/trade")
@AllArgsConstructor
public class TradeController {
    private final FavoriteCoinMarketService favoriteCoinMarketService;
    private final MyAssetService myAssetService;
    private final CoinTransactionService coinTransactionService;
    private final PriceService priceService;

    // user 1이 로그인한 상태를 가정
    @GetMapping("main.do")
    public String main(Model model) {
        Map<String, Double> myAssetMap = myAssetService.readAssetByUserNo(1);
        model.addAttribute("myAssetMap", myAssetMap);

        Map<String, Double> currentPriceMap = new HashMap<>();
        for (String market : myAssetMap.keySet()) {
            if ("KRW-KRW".equalsIgnoreCase(market)) {
                continue;
            }
            // 만약 key가 이미 "KRW-"로 시작하면 그대로 사용, 그렇지 않으면 접두어 추가
            String upbitMarket = market.startsWith("KRW-") ? market : "KRW-" + market;
            double currentPrice = priceService.getCoinPrice(upbitMarket);
            currentPriceMap.put(market, currentPrice);
        }

        ProfitResult profitResult = coinTransactionService.calculateProfit(1, currentPriceMap);
        model.addAttribute("profitResult", profitResult);

        return "trade/main";
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
    // 거래소 홈 화면 보유자산 표시
    @GetMapping("/holding_coin.do")
    public String holdingCoin(Model model) {


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
    }
}
