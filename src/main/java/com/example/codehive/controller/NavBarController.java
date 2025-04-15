package com.example.codehive.controller;

import com.example.codehive.dto.ProfitResultDto;
import com.example.codehive.repository.UserRepository;
import com.example.codehive.service.CoinTransactionService;
import com.example.codehive.service.MyAssetService;
import com.example.codehive.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.codehive.entity.User;

import java.util.HashMap;
import java.util.Map;

@Controller
@AllArgsConstructor
public class NavBarController {
    private final CoinTransactionService coinTransactionService;
    private final MyAssetService myAssetService;
    private final PriceService priceService;
    private final UserRepository userRepository;

    @GetMapping("/fragments/nav_bars")
    public String navBars(@AuthenticationPrincipal UserDetails loginUser, Model model) {
        String userId = loginUser.getUsername();
        User user = userRepository.findByUserId(userId);
        int userNo = user.getId();

        Map<String, Double> myAssetMap = myAssetService.readAssetByUserNo(userNo);
        model.addAttribute("myAssetMap", myAssetMap);

        Map<String, Double> currentPriceMap = new HashMap<>();
        for (String market : myAssetMap.keySet()) {
            if( "KRW-KRW".equalsIgnoreCase(market)){
                continue;
            }
            String upbitMarket = market;
            double currentPrice = priceService.getCoinPrice(upbitMarket);
            currentPriceMap.put(market, currentPrice);
        }
        ProfitResultDto profitResultDto = coinTransactionService.calculateProfit(1, currentPriceMap);
        model.addAttribute("profitResultDto", profitResultDto);
        return "fragments/nav_bars";
    }
}
