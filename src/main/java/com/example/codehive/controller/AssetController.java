package com.example.codehive.controller;

import com.example.codehive.dto.ProfitResultDto;
import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.service.CoinNameService;
import com.example.codehive.service.CoinTransactionService;
import com.example.codehive.service.MyAssetService;
import com.example.codehive.service.PriceService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = {"http://localhost:5173"})
@RestController
@RequestMapping("/asset")
@AllArgsConstructor
public class AssetController {
    private final CoinTransactionService coinTransactionService;
    private final MyAssetService myAssetService;
    private final PriceService priceService;
    private final CoinNameService coinNameService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/user/myAsset.do")
    public String myAsset(Model model) {
        Map<String, Double> myAssetMap = myAssetService.readAssetByUserNo(1);
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
        ProfitResultDto profitResultDto = coinTransactionService.calculateProfit(1, currentPriceMap);
        model.addAttribute("profitResultDto", profitResultDto);

        return "asset/my_asset";
    }

    @GetMapping("myAsset")
    public Map<String, Object> myAsset() {
        int userNo = 1;

        Map<String, Double> myAssetMap = myAssetService.readAssetByUserNo(userNo);

        Map<String, Double> currentPriceMap = new HashMap<>();
        for (String market : myAssetMap.keySet()) {
            if( "KRW-KRW".equalsIgnoreCase(market)){
                continue;
            }
            double currentPrice = priceService.getCoinPrice(market);
            currentPriceMap.put(market, currentPrice);
        }
        ProfitResultDto profitResultDto = coinTransactionService.calculateProfit(userNo, currentPriceMap);

        Map<String, Object> result = new HashMap<>();
        result.put("myAssetMap", myAssetMap);
        result.put("currentPriceMap", currentPriceMap);
        result.put("profitResultDto", profitResultDto);
        return result;
    }

    @GetMapping("transaction.do")
    public String transaction(Model model) {
        List<CoinTransaction> coinTransactions = coinTransactionService.findByUserNo(1);
        model.addAttribute("coinTransactions", coinTransactions);

        Map<String, String> coinNameMap = coinNameService.getMarketToKoreanNameMap();
        model.addAttribute("coinNameMap", coinNameMap);
        return "asset/transaction";
    }

    @GetMapping("coinTransactions")
//    @ResponseBody
    public Map<String,Object> coinTransactions() {
        List<CoinTransaction> coinTransactions = coinTransactionService.findByUserNo(1);

        Map<String, Object> map = new HashMap<>();
        map.put("coinTransactions",coinTransactions);
        System.out.println(map.toString());

        return map;
    }

    @GetMapping("open_orders.do")
    public String openOrders(Model model) {
        List<CoinTransaction> coinTransactions = coinTransactionService.findTransactionStateByUserNo(1);
        model.addAttribute("coinTransactions", coinTransactions);

        Map<String, String> coinNameMap = coinNameService.getMarketToKoreanNameMap();
        model.addAttribute("coinNameMap", coinNameMap);
        return "asset/open_orders";
    }

    @GetMapping("openOrders")
    public Map<String,Object> openOrders() {
        List<CoinTransaction> coinTransactions = coinTransactionService.findTransactionStateByUserNo(1);

        Map<String, Object> map = new HashMap<>();
        map.put("coinTransactions",coinTransactions);
        return map;
    }

    @DeleteMapping("openOrders/id/{id}")
    public ResponseEntity<Void> remove(@PathVariable int id) {
        try{
            coinTransactionService.remove(id);
        }catch (IllegalArgumentException e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("openOrders/user/{userNo}")
    public ResponseEntity<Void> removeAllPending(@PathVariable int userNo) {
        try{
            coinTransactionService.removeTransactionPendingByUserNo(userNo);
        }catch (IllegalArgumentException e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }catch (Exception e){
            logger.error(e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

}
