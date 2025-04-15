package com.example.codehive.controller;

import com.example.codehive.dto.ProfitResultDto;
import com.example.codehive.entity.CoinTransaction;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.codehive.repository.UserRepository;
import com.example.codehive.entity.User;
import com.example.codehive.service.CoinNameService;
import com.example.codehive.service.CoinTransactionService;
import com.example.codehive.service.MyAssetService;
import com.example.codehive.service.PriceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/asset")
@AllArgsConstructor
public class AssetController {
    private final CoinTransactionService coinTransactionService;
    private final MyAssetService myAssetService;
    private final PriceService priceService;
    private final CoinNameService coinNameService;
    private final UserRepository userRepository;

    @GetMapping("my_asset.do")
    public String myAsset(@AuthenticationPrincipal UserDetails loginUser, Model model) {
        User user = userRepository.findByUserId(loginUser.getUsername());

        Map<String, Double> myAssetMap = myAssetService.readAssetByUserNo(user.getId());
        model.addAttribute("myAssetMap", myAssetMap);

        // 한글명 매핑
        Map<String, String> coinNameMap = coinNameService.getMarketToKoreanNameMap();
        model.addAttribute("coinNameMap", coinNameMap);

        Map<String, Double> currentPriceMap = new HashMap<>();
        for (String market : myAssetMap.keySet()) {
            if( "KRW-KRW".equalsIgnoreCase(market)){
                continue;
            }
            String upbitMarket = market;
            double currentPrice = priceService.getCoinPrice(upbitMarket);
            currentPriceMap.put(market, currentPrice);
        }
        ProfitResultDto profitResultDto = coinTransactionService.calculateProfit(user.getId(), currentPriceMap);
        model.addAttribute("profitResultDto", profitResultDto);

        return "asset/my_asset";
    }

    @GetMapping("transaction.do")
    public String transaction(@AuthenticationPrincipal UserDetails loginUser, Model model) {
        User user = userRepository.findByUserId(loginUser.getUsername());

        List<CoinTransaction> coinTransactions = coinTransactionService.findByUserNo(user.getId());
        model.addAttribute("coinTransactions", coinTransactions);

        Map<String, String> coinNameMap = coinNameService.getMarketToKoreanNameMap();
        model.addAttribute("coinNameMap", coinNameMap);
        return "asset/transaction";
    }
    @GetMapping("coinTransactions.do")
    @ResponseBody
    public Map<String,Object> coinTransactions(@AuthenticationPrincipal UserDetails loginUser) {
        User user = userRepository.findByUserId(loginUser.getUsername());
        List<CoinTransaction> coinTransactions = coinTransactionService.findByUserNo(user.getId());

        Map<String, String> coinNameMap = coinNameService.getMarketToKoreanNameMap();
        Map<String, Object> map = new HashMap<>();
        map.put("coinTransactions",coinTransactions);
        map.put("coinNameMap",coinNameMap);
        System.out.println(map.toString());

        return map;
    }

    @GetMapping("open_orders.do")
    public String openOrders(@AuthenticationPrincipal UserDetails loginUser, Model model) {
        // 로그인한 유저 아이디 가져오기
        String userId = loginUser.getUsername();

        // userId로 User 객체 가져오기
        User user = userRepository.findByUserId(userId);
        List<CoinTransaction> coinTransactions = coinTransactionService.findTransactionStateByUserNo(user.getId());
        model.addAttribute("coinTransactions", coinTransactions);

        Map<String, String> coinNameMap = coinNameService.getMarketToKoreanNameMap();
        model.addAttribute("coinNameMap", coinNameMap);
        return "asset/open_orders";
    }
}
