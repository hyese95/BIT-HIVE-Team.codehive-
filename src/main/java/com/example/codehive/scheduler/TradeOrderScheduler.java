package com.example.codehive.scheduler;

import com.example.codehive.entity.CoinTransaction;
import com.example.codehive.service.CoinTransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 미체결 주문을 처리하는 스케줄러
 * 업비트 API를 사용하여 현재 시세를 확인하고 주문 체결 조건을 만족하면 상태를 업데이트
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class TradeOrderScheduler {

    private final CoinTransactionService coinTransactionService;
    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 시세 정보를 저장할 맵 (마켓 코드 -> 현재가)
    private final Map<String, Double> currentPrices = new HashMap<>();


//      1초마다 스케줄러

    @Scheduled(fixedRate = 1000) // 1초마다 실행
    public void processOrders() {
        log.info("주문 처리 스케줄러 실행 중...");

        try {
            // 모든 미체결 주문 조회
            List<CoinTransaction> pendingTransactions = coinTransactionService.findAllPendingTransactions();

            if (pendingTransactions.isEmpty()) {
                log.info("처리할 미체결 주문이 없습니다.");
                return;
            }

            // 필요한 마켓 코드 리스트 추출
            List<String> markets = pendingTransactions.stream()
                    .map(CoinTransaction::getMarket)
                    .distinct()
                    .filter(market -> !market.equals("KRW-KRW"))
                    .toList();

            // 업비트 API에서 시세 정보 가져오기
            fetchCurrentPrices(markets);

            // 각 미체결 주문 처리
            processPendingTransactions(pendingTransactions);

        } catch (Exception e) {
            log.error("주문 처리 중 오류 발생: {}", e.getMessage(), e);
        }
    }

    /**
     * 업비트 API에서 시세 정보를 가져와 맵에 저장
     */
    private void fetchCurrentPrices(List<String> markets) throws JsonProcessingException {
        if (markets.isEmpty()) {
            return;
        }

        // 마켓 코드 리스트를 쉼표로 구분된 문자열로 변환 (예: "KRW-BTC,KRW-ETH")
        String marketCodesParam = String.join(",", markets);
        String url = "https://api.upbit.com/v1/ticker?markets=" + marketCodesParam;

        try {
            // API 호출
            String response = restTemplate.getForObject(url, String.class);

            // 응답 파싱
            JsonNode jsonArray = objectMapper.readTree(response);

            // 현재가 맵 업데이트
            currentPrices.clear();
            for (JsonNode item : jsonArray) {
                String market = item.get("market").asText();
                double tradePrice = item.get("trade_price").asDouble();
                currentPrices.put(market, tradePrice);
                log.debug("시세 정보 업데이트: {} -> {}", market, tradePrice);
            }

            log.info("{} 개 마켓의 시세 정보를 업데이트했습니다.", currentPrices.size());

        } catch (Exception e) {
            log.error("시세 정보 조회 중 오류 발생: {}", e.getMessage(), e);
            throw e;
        }
    }

    /**
     * 미체결 주문을 처리
     */
    private void processPendingTransactions(List<CoinTransaction> pendingTransactions) {//미체결 주문처리
        for (CoinTransaction transaction : pendingTransactions) {
            // KRW-KRW 마켓은 건너뜀 (디파짓 처리는 별도로 함)
            if ("KRW-KRW".equals(transaction.getMarket())) {
                continue;
            }

            String market = transaction.getMarket();
            String type = transaction.getTransactionType();
            double orderPrice = transaction.getPrice();

            // 현재가 조회
            Double currentPrice = currentPrices.get(market);
            if (currentPrice == null) {
                log.warn("마켓 {} 의 현재가를 찾을 수 없습니다.", market);
                continue;
            }

            log.debug("주문 처리 중: 마켓={}, 타입={}, 주문가={}, 현재가={}",
                    market, type, orderPrice, currentPrice);

            // 주문 조건 확인 및 처리
            if ("BUY".equals(type) && currentPrice <= orderPrice) {
                // 매수 주문: 현재가가 주문가보다 같거나 낮으면 체결
                log.info("매수 주문 체결: {} (주문가: {}, 현재가: {})", market, orderPrice, currentPrice);
                coinTransactionService.updateTransactionToCompleted(transaction);
            } else if ("SELL".equals(type) && currentPrice >= orderPrice) {
                // 매도 주문: 현재가가 주문가보다 같거나 높으면 체결
                log.info("매도 주문 체결: {} (주문가: {}, 현재가: {})", market, orderPrice, currentPrice);
                coinTransactionService.updateTransactionToCompleted(transaction);

                // 매도 주문이 체결되면 관련 디파짓 요청도 체결 처리
                coinTransactionService.completeRelatedDeposit(transaction);
            }
        }
    }
} 