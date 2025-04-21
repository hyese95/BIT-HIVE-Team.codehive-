package com.example.codehive.controller;

import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.service.CoinTransactionService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transaction")
@CrossOrigin("http://localhost:5173")
public class CoinTransactionApiController {
    private CoinTransactionService coinTransactionService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("coinTransactions")
    public List<CoinTransaction> getFilteredTransactions(
            @RequestParam int userNo,
            @RequestParam(required = false) String transactionType, // BUY, SELL
            @RequestParam(required = false) String transactionState, // COMPLETED, PENDING
            @RequestParam(required = false) String market, // KRW-BTC 등
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        return coinTransactionService.getFilteredTransactions(userNo, transactionType, transactionState, market, startDate, endDate);
    }
    //api/transactions/{userNo}
    @GetMapping("openOrders")
    public Map<String,Object> openOrders() {
        List<CoinTransaction> coinTransactions = coinTransactionService.findTransactionStateByUserNo(1);

        Map<String, Object> map = new HashMap<>();
        map.put("coinTransactions",coinTransactions);
        return map;
    }

    // 선택 삭제
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

    // 전체 삭제
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
