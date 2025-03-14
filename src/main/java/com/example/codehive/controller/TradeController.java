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
    // user 가 매수한 KRW 리스트
    @GetMapping("/{userNo}/main.do") // URL 경로에서 {userNo} 값을 받아서 요청 처리
    @ResponseBody // 반환 값을 뷰가 아닌 HTTP 응답 본문(JSON)으로 전송
    public List<CoinTransaction> main(@PathVariable int userNo) { // URL의 {userNo} 값을 메소드 파라미터로 매핑
        // CoinTransaction 리스트를 저장할 변수 선언 (초기값 null, 이후 service 호출로 값 대입)
        List<CoinTransaction> KrwHoldingList = null;
        // service 메소드를 호출하여 조건에 맞는 코인 거래 내역을 조회
        // 조건: userNo, 시장이 "KRW-KRW", 거래 유형이 "BUY", 거래 상태가 "COMPLETED"
        KrwHoldingList = coinTransactionService.readByUserNoAndMarketAndTransactionTypeAndTransactionState(
                userNo, "KRW-KRW", "BUY", "COMPLETED"
        );
        // 조회된 코인 거래 내역 리스트를 JSON 형태로 반환 (@ResponseBody에 의해 자동 변환)
        return KrwHoldingList;
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
        List<CoinTransaction> myAssetList =coinTransactionService.readByUserNo(1);
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
