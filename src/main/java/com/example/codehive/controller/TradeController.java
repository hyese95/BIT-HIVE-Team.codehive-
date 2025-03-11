package com.example.codehive.controller;

import com.example.codehive.entity.FavoriteMarket;
import com.example.codehive.service.FavoriteCoinMarketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/trade")
@AllArgsConstructor
public class TradeController {
    private final FavoriteCoinMarketService favoriteCoinMarketService;

    @GetMapping("main.do")
    public String main() {
        return "trade/main";
    }

    @GetMapping("buy.do")
    public String coinOrder() {
        return "trade/buy";
    }

    @GetMapping("/{id}/favorite_coin.do")
    public String favoriteCoin(
            @PathVariable int id,
            Model model
    ) {
        // 해당 사용자 id에 맞는 모든 관심 코인 목록을 조회합니다.
        List<FavoriteMarket> favoriteList = favoriteCoinMarketService.readAll(id);
        FavoriteMarket fav = favoriteList.get(0);
        if (fav==null){
            return "redirect:/trade/main.do";
        }else {
            model.addAttribute("favorite", fav);
            return "trade/favorite_coin";
        }
    }
}
