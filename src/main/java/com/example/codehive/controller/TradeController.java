package com.example.codehive.controller;

import com.example.codehive.dto.CoinDetailDto;
import com.example.codehive.dto.ProfitResultDto;
import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.entity.FavoriteMarket;
import com.example.codehive.entity.FavoriteMarketId;
import com.example.codehive.service.*;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/trade")
@AllArgsConstructor
public class TradeController {
    private final FavoriteCoinMarketService favoriteCoinMarketService;
    private final MyAssetService myAssetService;
    private final CoinTransactionService coinTransactionService;
    private final PriceService priceService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final CoinNameService coinNameService;

    @GetMapping("main.do")
    public String main(Model model) {
        Map<String, Double> myAssetMap = myAssetService.readAssetByUserNo(1);
        model.addAttribute("myAssetMap", myAssetMap);

        Map<String, Double> currentPriceMap = new HashMap<>();
        for (String market : myAssetMap.keySet()) {
            if ("KRW-KRW".equalsIgnoreCase(market)) {
                continue;
            }
            String upbitMarket = market;
            double currentPrice = priceService.getCoinPrice(upbitMarket);
            currentPriceMap.put(market, currentPrice);
        }

        ProfitResultDto profitResultDto = coinTransactionService.calculateProfit(1, currentPriceMap);
        model.addAttribute("profitResult", profitResultDto);

        return "trade/main";
    }

    @GetMapping("buy.do")
    public String coinOrderBuy(Model model) {
        return "trade/buy";
    }

    @GetMapping("/favorite_coin.do")
    public String favoriteMarket(Model model) {
    List<String> favList = favoriteCoinMarketService.readByUserNo(1);
    model.addAttribute("favList", favList);
    return "trade/favorite_coin";
    }

    // 즐겨찾기 추가
    @PostMapping("/favorite_action.do")
    public ResponseEntity<Void> createAction(
            @RequestBody FavoriteMarket favoriteMarket
    ){
        try{
            favoriteCoinMarketService.regiseter(favoriteMarket);
        }catch (IllegalArgumentException e){
            logger.error(e.getMessage());
            return ResponseEntity.status(409).build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(201).build();
    }

    //즐겨찾기 제거
    @DeleteMapping("/favorite_action.do")
    public ResponseEntity<Void> deleteAction(
            @RequestBody FavoriteMarketId favoriteMarketId
    ){
        try{
            favoriteCoinMarketService.remove(favoriteMarketId);
        }catch (IllegalArgumentException e){
            logger.error(e.getMessage());
            return ResponseEntity.notFound().build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.status(202).build();
    }

    @GetMapping("/holding_coin.do")
    public String holdingCoin(Model model) {
        Map<String, Double> myAssetMap = myAssetService.readAssetByUserNo(1);
        Map<String, Double> currentPriceMap = new HashMap<>();

        Map<String, String> coinNameMap = coinNameService.getMarketToKoreanNameMap();
        model.addAttribute("coinNameMap", coinNameMap);

        for (String market : myAssetMap.keySet()) {
            if (!"KRW-KRW".equalsIgnoreCase(market)) {
                String upbitMarket = market.startsWith("KRW-") ? market : "KRW-" + market;
                double price = priceService.getCoinPrice(upbitMarket);
                currentPriceMap.put(market, price);
            }
        }

        ProfitResultDto result = coinTransactionService.calculateProfit(1, currentPriceMap);
        model.addAttribute("coinDetails", result.getCoinDetails());
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
    @GetMapping("sell.do")
    public String coinOrderSell(Model model) {
        return "trade/sell";
    }

    @GetMapping("/history.do")
    public String historyView(
            @RequestParam("coin") String coin,
            @RequestParam(value = "status", required = false, defaultValue = "COMPLETED") String transactionState,
            @RequestParam(value = "user", required = false, defaultValue = "user1") String user,
            Model model) {
                return "trade/history";
            }
}
