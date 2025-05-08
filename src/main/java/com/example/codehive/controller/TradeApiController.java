package com.example.codehive.controller;

import com.example.codehive.dto.TradeRequestDto;
import com.example.codehive.entity.User;
import com.example.codehive.service.CoinTransactionService;
import com.example.codehive.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@CrossOrigin("http://localhost:5173")
@RequestMapping("/api/trade")
public class TradeApiController {

    private final CoinTransactionService coinTransactionService;
    private final UserService userService;

    @GetMapping("/me/deposit")
    public double getRemainDeposit(@AuthenticationPrincipal UserDetails loginUser) {
        String userId = loginUser.getUsername(); // 로그인한 사용자 ID 가져오기
        User user = userService.readByUserId(userId)
                .orElseThrow(() -> new RuntimeException("로그인된 유저를 찾을 수 없습니다."));
        int userNo = user.getId();
//        int userNo=1;//하드코딩
        double deposit = 0;
        deposit=coinTransactionService.getAvailableDeposit(userNo);
        return deposit;
    }
    @GetMapping("/me/remainCnt")
    public double getRemainCnt(@RequestParam String market,@AuthenticationPrincipal UserDetails loginUser) {
        String userId = loginUser.getUsername(); // 로그인한 사용자 ID 가져오기
        User user = userService.readByUserId(userId)
                .orElseThrow(() -> new RuntimeException("로그인된 유저를 찾을 수 없습니다."));
        int userNo = user.getId();
        double remainCnt = 0;
        remainCnt=coinTransactionService.getAvailableCoinQuantity(userNo,market);
        return remainCnt;
    }


    @PostMapping("/me")
    public ResponseEntity<Void> me(
            @RequestBody TradeRequestDto tradeRequestDto,
            @AuthenticationPrincipal UserDetails loginUser
    ) {
        String userId = loginUser.getUsername(); // 로그인한 사용자 ID 가져오기
        User user = userService.readByUserId(userId)
                .orElseThrow(() -> new RuntimeException("로그인된 유저를 찾을 수 없습니다."));
        int userNo = user.getId();
        tradeRequestDto.setUserNo(userNo);
        double deposit=0.0;
        double remainCnt = 0.0;
        String market= tradeRequestDto.getMarket();
        deposit=coinTransactionService.getAvailableDeposit(userNo);
        remainCnt=coinTransactionService.getAvailableCoinQuantity(userNo,market);

        if(tradeRequestDto.transactionType.equals("BUY") && deposit<tradeRequestDto.getTransactionCnt()*tradeRequestDto.getPrice()){
            throw new RuntimeException("잔액부족");
        }
        if(tradeRequestDto.transactionType.equals("SELL") && remainCnt<tradeRequestDto.getTransactionCnt() ){
            throw new RuntimeException("남은코인 부족");
        }

        coinTransactionService.submitTrade(tradeRequestDto);
        return ResponseEntity.ok().build();
    }


}
