package com.example.codehive.controller;

import com.example.codehive.dto.AssetDto;
import com.example.codehive.dto.CoinTransactionResponseDto;
import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.repository.UserRepository;
import com.example.codehive.service.CoinTransactionService;
import com.example.codehive.service.MyAssetService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
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
    private final UserRepository userRepository;
    private CoinTransactionService coinTransactionService;
    private MyAssetService myAssetService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @GetMapping("")
    public List<CoinTransactionResponseDto> getFilteredTransactions(
            @RequestParam(required = false, defaultValue = "1") int userNo,
            @RequestParam(required = false) String transactionType,
            @RequestParam(required = false) String transactionState,
            @RequestParam(required = false) String market,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate
    ) {
        return coinTransactionService.getFilteredTransactionDtos(userNo, transactionType, transactionState, market, startDate, endDate);
    }
    // 주석쓰
    // 선택 삭제
    @DeleteMapping("/openOrder/id/{id}")
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

    // 미체결 전체 삭제
    @DeleteMapping("/openOrder/user/{userNo}")
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

    @GetMapping("{userNo}")
    public ResponseEntity<List<AssetDto>> getUserAssets(@PathVariable int userNo) {
        List<AssetDto> assetDtoList = myAssetService.readHoldingCoinListByUserNo(userNo);
        return ResponseEntity.ok(assetDtoList);
    }

    // 보유 자산 초기화
    @DeleteMapping("/{userNo}")
    public ResponseEntity<Void> clearUserAssets(@PathVariable int userNo) {
        try {
            coinTransactionService.removeAllTransactionsByUserNo(userNo);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("보유자산 초기화 실패: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> registerInitialAsset(@RequestBody CoinTransaction coinTransaction) {
        logger.info("자산 등록 요청: {}", coinTransaction);

        if (!userRepository.existsById(coinTransaction.getUserNo())) {
            logger.warn("user_no={} 은 존재하지 않습니다", coinTransaction.getUserNo());
            return ResponseEntity.badRequest().build();
        }

        try {
            coinTransactionService.register(coinTransaction);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("자산 등록 중 오류 발생: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

}
