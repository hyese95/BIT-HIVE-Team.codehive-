package com.example.codehive.controller;

import com.example.codehive.dto.AssetDto;
import com.example.codehive.dto.CoinTransactionResponseDto;
import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.repository.UserRepository;
import com.example.codehive.security.CustomUserDetails;
import com.example.codehive.service.CoinTransactionService;
import com.example.codehive.service.MyAssetService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/transaction")
@CrossOrigin("http://localhost:5173")
public class CoinTransactionApiController {

    private final UserRepository userRepository;
    private final CoinTransactionService coinTransactionService;
    private final MyAssetService myAssetService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("")
    public Page<CoinTransactionResponseDto> getFilteredTransactions(
            Authentication authentication,
            @RequestParam(required = false) String transactionType,
            @RequestParam(required = false) String transactionState,
            @RequestParam(required = false) String market,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
            @PageableDefault(size = 20, sort = "transactionDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            throw new IllegalArgumentException("인증되지 않은 사용자입니다.");
        }

        int userNo = ((CustomUserDetails) authentication.getPrincipal()).getUserNo();
        Instant start = startDate != null ? startDate.atZone(ZoneId.of("Asia/Seoul")).toInstant() : null;
        Instant end = endDate != null ? endDate.atZone(ZoneId.of("Asia/Seoul")).toInstant() : null;
        return coinTransactionService.getFilteredTransactionDtos(userNo, transactionType, transactionState, market, start, end, pageable);
    }

    // 선택 삭제
    @DeleteMapping("/openOrder/{id}")
    public ResponseEntity<Void> remove(@PathVariable int id) {
        try {
            coinTransactionService.remove(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("삭제 실패: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    // 미체결 전체 삭제
    @DeleteMapping("/openOrder/user")
    public ResponseEntity<Void> removeAllPending(Authentication authentication) {
        int userNo = extractUserNo(authentication);
        try {
            coinTransactionService.removeTransactionPendingByUserNo(userNo);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("미체결 삭제 실패: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<List<AssetDto>> getUserAssets(Authentication authentication) {
        int userNo = extractUserNo(authentication);
        List<AssetDto> assetDtoList = myAssetService.readHoldingCoinListByUserNo(userNo);
        return ResponseEntity.ok(assetDtoList);
    }

    @DeleteMapping("/me")
    public ResponseEntity<Void> clearUserAssets(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        int userNo = userDetails.getUserNo();

        try {
            coinTransactionService.removeAllTransactionsByUserNo(userNo);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("보유자산 초기화 실패: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> registerInitialAsset(
            @RequestBody CoinTransaction coinTransaction,
            Authentication authentication
    ) {
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        int userNo = userDetails.getUserNo();
        coinTransaction.setUserNo(userNo);

        // 유효성 검사
        String market = coinTransaction.getMarket();
        double price = coinTransaction.getPrice();
        double cnt = coinTransaction.getTransactionCnt();

        if ("KRW-KRW".equals(market)) {
            // KRW 입금 등록일 경우
            if (cnt < 1_000_000 || cnt > 100_000_000) {
                return ResponseEntity.badRequest().body(null);
            }
            if (price != 1.0) {
                return ResponseEntity.badRequest().body(null);
            }
        } else {
            // 일반 코인 거래일 경우
            if (price <= 0 || cnt <= 0) {
                return ResponseEntity.badRequest().body(null);
            }
        }

        logger.info("자산 등록 요청: {}", coinTransaction);
        try {
            coinTransactionService.register(coinTransaction);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("자산 등록 중 오류 발생: {}", e.getMessage());
            return ResponseEntity.internalServerError().build();
        }
    }

    // 🔐 유저 번호 추출 공통 함수
    private int extractUserNo(Authentication authentication) {
        if (authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails)) {
            throw new IllegalArgumentException("인증되지 않은 사용자입니다.");
        }
        return ((CustomUserDetails) authentication.getPrincipal()).getUserNo();
    }

}
