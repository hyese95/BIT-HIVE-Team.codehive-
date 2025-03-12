package com.example.codehive.controller;

import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.entity.FavoriteMarket;
import com.example.codehive.service.CoinTransactionService;
import com.example.codehive.service.FavoriteCoinMarketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String coinOrder() {
        return "trade/buy";
    }

    @GetMapping("/favorite_coin.do")
    public String favoriteMarket(Model model) {
                List<FavoriteMarket> favoriteMarketList = favoriteCoinMarketService.readByUserNo(1);
        model.addAttribute("favoriteMarketList", favoriteMarketList);

    return "trade/favorite_coin";
    }
}
