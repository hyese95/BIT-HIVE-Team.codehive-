-- users 테이블 (먼저 생성해야 다른 테이블의 외래키 참조 가능)
INSERT INTO users (user_id, password, nickname, email, phone, privacy_agreements, marketing_agreements, nationality,
                   gender, birth_date, name, self_introduction, role)
VALUES ('user1', 'pass123', '비트맨', 'user1@example.com', '010-1111-1111', true, true, 'KR', 'MALE', '1990-01-01', '김철수',
        '비트코인 투자자입니다', 'USER'),
       ('user2', 'pass123', '코인女', 'user2@example.com', '010-2222-2222', true, false, 'KR', 'FEMALE', '1992-02-02',
        '이영희', '알트코인 전문가', 'USER'),
       ('user3', 'pass123', '투자의신', 'user3@example.com', '010-3333-3333', true, true, 'KR', 'MALE', '1988-03-03', '박민수',
        '차트분석가', 'USER'),
       ('user4', 'pass123', '존버킹', 'user4@example.com', '010-4444-4444', true, true, 'KR', 'MALE', '1995-04-04', '정대만',
        '장기투자자', 'USER'),
       ('user5', 'pass123', '단타여신', 'user5@example.com', '010-5555-5555', true, false, 'KR', 'FEMALE', '1993-05-05',
        '최지은', '단타전문', 'USER'),
       ('admin1', 'admin123', '관리자1', 'admin1@example.com', '010-0000-0001', true, true, 'KR', 'MALE', '1985-12-12',
        '관리자', '관리자입니다', 'ADMIN'),
       ('user6', 'pass123', '코인요정', 'user6@example.com', '010-6666-6666', true, true, 'KR', 'FEMALE', '1991-06-06',
        '송미라', '비트코인 마이너', 'USER'),
       ('user7', 'pass123', '황소장', 'user7@example.com', '010-7777-7777', true, true, 'KR', 'MALE', '1987-07-07', '황길동',
        '강세장 전문가', 'USER'),
       ('user8', 'pass123', '곰돌이', 'user8@example.com', '010-8888-8888', true, false, 'KR', 'MALE', '1994-08-08', '곽진태',
        '약세장 전문가', 'USER'),
       ('user9', 'pass123', '알트하는여자', 'user9@example.com', '010-9999-9999', true, true, 'KR', 'FEMALE', '1996-09-09',
        '한미영', '알트코인 분석가', 'USER');

-- notification_settings 테이블
INSERT INTO notification_settings (volatility_yn, portfolio_yn, target_price_yn, trade_yn, like_yn, comment_yn,
                                   reply_yn, follower_yn)
VALUES (true, true, true, true, true, true, true, true),
       (true, false, true, true, false, true, true, false),
       (false, true, true, false, true, true, false, true),
       (true, true, false, true, true, false, true, true),
       (false, false, true, true, true, true, true, false),
       (true, true, true, false, false, true, true, true),
       (false, true, false, true, true, true, false, true),
       (true, false, true, true, true, false, true, false),
       (true, true, true, true, false, true, true, true),
       (false, true, true, false, true, true, true, true);

-- target_price_alerts 테이블
INSERT INTO target_price_alerts (user_no, market, target_price)
VALUES (1, 'BTC-KRW', 50000000),
       (2, 'ETH-KRW', 3000000),
       (3, 'XRP-KRW', 1000),
       (4, 'BTC-KRW', 48000000),
       (5, 'ETH-KRW', 2800000),
       (1, 'XRP-KRW', 1200),
       (2, 'BTC-KRW', 52000000),
       (3, 'ETH-KRW', 3200000),
       (4, 'XRP-KRW', 800),
       (5, 'BTC-KRW', 55000000);

-- volatility_alerts 테이블
INSERT INTO volatility_alerts (user_no, market)
VALUES (1, 'BTC-KRW'),
       (2, 'ETH-KRW'),
       (3, 'XRP-KRW'),
       (4, 'BTC-KRW'),
       (5, 'ETH-KRW'),
       (1, 'DOGE-KRW'),
       (2, 'ADA-KRW'),
       (3, 'SOL-KRW'),
       (4, 'MATIC-KRW'),
       (5, 'DOT-KRW');

-- coin_transactions 테이블
INSERT INTO coin_transactions (user_no, market, transaction_type, price, transaction_cnt, transaction_date,
                               transaction_state)
VALUES (1, 'BTC-KRW', 'BUY', 50000000, 0.1, '2024-03-01 10:00:00', 'COMPLETED'),
       (2, 'ETH-KRW', 'SELL', 3000000, 1.0, '2024-03-01 11:00:00', 'COMPLETED'),
       (3, 'XRP-KRW', 'BUY', 1000, 1000, '2024-03-01 12:00:00', 'COMPLETED'),
       (4, 'BTC-KRW', 'BUY', 48000000, 0.2, '2024-03-01 13:00:00', 'COMPLETED'),
       (5, 'ETH-KRW', 'SELL', 2800000, 2.0, '2024-03-01 14:00:00', 'COMPLETED'),
       (1, 'XRP-KRW', 'BUY', 1200, 500, '2024-03-01 15:00:00', 'PENDING'),
       (2, 'BTC-KRW', 'SELL', 52000000, 0.05, '2024-03-01 16:00:00', 'COMPLETED'),
       (3, 'ETH-KRW', 'BUY', 3200000, 0.5, '2024-03-01 17:00:00', 'COMPLETED'),
       (4, 'XRP-KRW', 'SELL', 800, 2000, '2024-03-01 18:00:00', 'PENDING'),
       (5, 'BTC-KRW', 'BUY', 55000000, 0.1, '2024-03-01 19:00:00', 'COMPLETED');

-- favorite_markets_folders 테이블
INSERT INTO favorite_markets_folders (user_no, list_name)
VALUES (1, '관심코인'),
       (2, '단기투자'),
       (3, '장기투자'),
       (4, '알트코인'),
       (5, '메인코인'),
       (1, '급등코인'),
       (2, '저평가코인'),
       (3, '신규상장'),
       (4, '디파이'),
       (5, 'NFT');

-- favorite_markets 테이블
INSERT INTO favorite_markets (user_no, market, list_no, sort_order)
VALUES (1, 'BTC-KRW', 1, 'ASC'),
       (2, 'ETH-KRW', 2, 'DESC'),
       (3, 'XRP-KRW', 3, 'ASC'),
       (4, 'ADA-KRW', 4, 'ASC'),
       (5, 'DOGE-KRW', 5, 'DESC'),
       (1, 'SOL-KRW', 6, 'ASC'),
       (2, 'DOT-KRW', 7, 'DESC'),
       (3, 'MATIC-KRW', 8, 'ASC'),
       (4, 'LINK-KRW', 9, 'DESC'),
       (5, 'UNI-KRW', 10, 'ASC');

-- follows 테이블
INSERT INTO follows (follower_user_no, following_user_no)
VALUES (1, 2),
       (1, 3),
       (2, 1),
       (3, 1),
       (4, 1),
       (5, 1),
       (2, 3),
       (3, 4),
       (4, 5),
       (5, 2);

-- posts 테이블 (2024-02-07부터 2024-03-07까지, 총 90개)
INSERT INTO posts (user_no, post_cont, img_url, category, post_created_at)
VALUES (1, '비트코인 드디어 8천만원 돌파!', '/images/btc/price_80m.jpg', 'free', '2024-02-07 09:15:23'),
       (3, '[차트] BTC 일봉 삼각수렴 패턴', '/images/chart/btc_pattern1.jpg', 'chart', '2024-02-07 14:30:45'),
       (2, '오늘 비트코인 손실 인증합니다', '/images/pnl/loss1.jpg', 'pnl', '2024-02-07 20:45:12'),
       (5, '[전문가칼럼] 2024년 하반기 전망', '/images/expert/analysis1.jpg', 'expert', '2024-02-08 10:20:33'),
       (4, '업비트 서버 또 터졌네요ㅠㅠ', NULL, 'free', '2024-02-08 15:55:18'),
       (1, '골든크로스 임박! 상세 분석', '/images/chart/golden_cross.jpg', 'chart', '2024-02-08 21:30:42'),
       (2, '3년 존버 수익 인증', '/images/pnl/profit_3y.jpg', 'pnl', '2024-02-09 09:15:09'),
       (3, '이더리움 머지 이후 변화', '/images/eth/merge_effect.jpg', 'free', '2024-02-09 14:40:55'),
       (5, '[전문가] 비트코인 ETF 영향 분석', '/images/expert/etf_analysis.jpg', 'expert', '2024-02-09 19:25:30'),
       (4, '알트코인 차트 패턴 분석', '/images/chart/alt_pattern.jpg', 'chart', '2024-02-10 11:10:22'),
       (1, '2024 상반기 수익 인증', '/images/pnl/2024_h1.jpg', 'pnl', '2024-02-10 16:45:33'),
       (2, '바이낸스 신규 상장 소식', NULL, 'free', '2024-02-10 20:20:15'),
       (3, '[전문가] 레이어2 솔루션 분석', '/images/expert/layer2.jpg', 'expert', '2024-02-11 10:35:48'),
       (4, '비트코인 주간 차트 분석', '/images/chart/btc_weekly.jpg', 'chart', '2024-02-11 15:50:27'),
       (5, '선물 레버리지 수익 인증', '/images/pnl/futures_profit.jpg', 'pnl', '2024-02-11 21:15:39'),
       (1, '요즘 김치프리미엄 현황', '/images/market/kimchi.jpg', 'free', '2024-02-12 09:30:42'),
       (2, '[차트] 이더리움 지지선 분석', '/images/chart/eth_support.jpg', 'chart', '2024-02-12 14:45:15'),
       (3, '솔라나 단타 수익 인증', '/images/pnl/sol_trade.jpg', 'pnl', '2024-02-12 19:20:33'),
       (4, '[전문가] 메이저 알트코인 분석', '/images/expert/major_alt.jpg', 'expert', '2024-02-13 10:15:48'),
       (5, '코인베이스 상장 루머', NULL, 'free', '2024-02-13 15:40:22'),
       (1, '피보나치 되돌림 분석법', '/images/chart/fibonacci.jpg', 'chart', '2024-02-13 20:25:15'),
       (2, '채굴 6개월 수익 인증', '/images/pnl/mining_6m.jpg', 'pnl', '2024-02-14 09:50:33'),
       (3, '[전문가] DeFi 시장 전망', '/images/expert/defi_outlook.jpg', 'expert', '2024-02-14 14:15:27'),
       (4, '거래소 해킹 사태 정리', NULL, 'free', '2024-02-14 19:30:18'),
       (5, 'RSI 과매수 구간 분석', '/images/chart/rsi_analysis.jpg', 'chart', '2024-02-15 10:45:42'),
       (1, '알트코인 물량 분석', '/images/expert/volume_analysis.jpg', 'expert', '2024-02-15 15:20:15'),
       (2, '스테이킹 수익 인증', '/images/pnl/staking_profit.jpg', 'pnl', '2024-02-15 20:35:33'),
       (3, '신규 코인 상장 정보', NULL, 'free', '2024-02-16 09:10:48'),
       (4, '[차트] 다이버전스 포착', '/images/chart/divergence.jpg', 'chart', '2024-02-16 14:25:22'),
       (5, '[전문가] NFT 시장 분석', '/images/expert/nft_market.jpg', 'expert', '2024-02-16 19:50:15'),
       (1, '레버리지 3배 수익 인증', '/images/pnl/leverage_3x.jpg', 'pnl', '2024-02-17 10:15:33'),
       (2, '거래소 점검 시간 안내', NULL, 'free', '2024-02-17 15:40:27'),
       (3, '이동평균선 크로스 분석', '/images/chart/ma_cross.jpg', 'chart', '2024-02-17 20:55:18'),
       (4, '[전문가] 메타버스 코인 전망', '/images/expert/metaverse.jpg', 'expert', '2024-02-18 09:30:42'),
       (5, '단타 실패 손실 인증', '/images/pnl/short_loss.jpg', 'pnl', '2024-02-18 14:45:15'),
       (1, '코인 커뮤니티 현황', NULL, 'free', '2024-02-18 19:20:33'),
       (2, '볼린저밴드 활용법', '/images/chart/bollinger.jpg', 'chart', '2024-02-19 10:15:48'),
       (3, '[전문가] 거시경제 영향 분석', '/images/expert/macro.jpg', 'expert', '2024-02-19 15:40:22'),
       (4, '중장기 투자 수익 인증', '/images/pnl/mid_term.jpg', 'pnl', '2024-02-19 20:25:15'),
       (5, '에어드랍 정보 공유', NULL, 'free', '2024-02-20 09:50:33'),
       (1, 'MACD 신호 분석', '/images/chart/macd.jpg', 'chart', '2024-02-20 14:15:27'),
       (2, '[전문가] 신규 프로젝트 리뷰', '/images/expert/new_project.jpg', 'expert', '2024-02-20 19:30:18'),
       (3, '비트코인 숏 수익 인증', '/images/pnl/btc_short.jpg', 'pnl', '2024-02-21 10:45:42'),
       (4, '마진거래 꿀팁 공유', NULL, 'free', '2024-02-21 15:20:15'),
       (5, '삼각수렴 패턴 분석', '/images/chart/triangle.jpg', 'chart', '2024-02-21 20:35:33'),
       (1, '[전문가] 채굴 난이도 분석', '/images/expert/mining_diff.jpg', 'expert', '2024-02-22 09:10:48'),
       (2, '알트코인 손실 인증', '/images/pnl/alt_loss.jpg', 'pnl', '2024-02-22 14:25:22'),
       (3, '코인 밋업 후기', NULL, 'free', '2024-02-22 19:50:15'),
       (4, '채널 브레이크아웃 분석', '/images/chart/breakout.jpg', 'chart', '2024-02-23 10:15:33'),
       (5, '[전문가] 규제 영향 분석', '/images/expert/regulation.jpg', 'expert', '2024-02-23 15:40:27'),
       (1, '선물 청산 손실 인증', '/images/pnl/futures_loss.jpg', 'pnl', '2024-02-23 20:55:18'),
       (2, '거래소 수수료 정보', NULL, 'free', '2024-02-24 09:30:42'),
       (3, '추세선 활용 분석', '/images/chart/trendline.jpg', 'chart', '2024-02-24 14:45:15'),
       (4, '[전문가] 시장 심리 분석', '/images/expert/sentiment.jpg', 'expert', '2024-02-24 19:20:33'),
       (5, '스윙 매매 수익 인증', '/images/pnl/swing_trade.jpg', 'pnl', '2024-02-25 10:15:48'),
       (1, '코인 세금 정책 정리', NULL, 'free', '2024-02-25 15:40:22'),
       (2, '패턴 트레이딩 분석', '/images/chart/pattern_trading.jpg', 'chart', '2024-02-25 20:25:15'),
       (3, '[전문가] 기술적 지표 해석', '/images/expert/technical.jpg', 'expert', '2024-02-26 09:50:33'),
       (4, '장기 투자 수익 인증', '/images/pnl/long_term.jpg', 'pnl', '2024-02-26 14:15:27'),
       (5, '신규 거래소 런칭 소식', NULL, 'free', '2024-02-26 19:30:18'),
       (1, '지지/저항선 분석', '/images/chart/support_resistance.jpg', 'chart', '2024-02-27 10:45:42'),
       (2, '[전문가] 블록체인 기술 전망', '/images/expert/blockchain.jpg', 'expert', '2024-02-27 15:20:15'),
       (3, '데이트레이딩 수익 인증', '/images/pnl/day_trading.jpg', 'pnl', '2024-02-27 20:35:33'),
       (4, '코인 커뮤니티 설문', NULL, 'free', '2024-02-28 09:10:48'),
       (5, '차트 기본 패턴 분석', '/images/chart/basic_pattern.jpg', 'chart', '2024-02-28 14:25:22'),
       (1, '[전문가] 토큰이코노미 분석', '/images/expert/tokenomics.jpg', 'expert', '2024-02-28 19:50:15'),
       (2, '소액 투자 수익 인증', '/images/pnl/small_invest.jpg', 'pnl', '2024-02-29 10:15:33'),
       (3, '거래소 이벤트 정보', NULL, 'free', '2024-02-29 15:40:27'),
       (4, '캔들스틱 패턴 분석', '/images/chart/candlestick.jpg', 'chart', '2024-02-29 20:55:18'),
       (5, '[전문가] 스마트컨트랙트 리뷰', '/images/expert/smart_contract.jpg', 'expert', '2024-03-01 09:30:42'),
       (1, '존버 1년 수익 인증', '/images/pnl/hodl_1y.jpg', 'pnl', '2024-03-01 14:45:15'),
       (2, '코인 용어 정리', NULL, 'free', '2024-03-01 19:20:33'),
       (3, '차트 보는 법 강좌', '/images/chart/chart_guide.jpg', 'chart', '2024-03-02 10:15:48'),
       (4, '[전문가] 가상자산 시장 전망', '/images/expert/market_outlook.jpg', 'expert', '2024-03-02 15:40:22'),
       (5, '단기 스캘핑 수익 인증', '/images/pnl/scalping.jpg', 'pnl', '2024-03-02 20:25:15'),
       (1, '거래소 API 활용법', NULL, 'free', '2024-03-03 09:50:33'),
       (2, '엘리엇 파동 분석', '/images/chart/elliott_wave.jpg', 'chart', '2024-03-03 14:15:27'),
       (3, '[전문가] 알트코인 시즌 분석', '/images/expert/altseason.jpg', 'expert', '2024-03-03 19:30:18'),
       (4, '마진 롱 수익 인증', '/images/pnl/margin_long.jpg', 'pnl', '2024-03-04 10:45:42'),
       (5, '코인 뉴스 정리', NULL, 'free', '2024-03-04 15:20:15'),
       (1, '거래량 분석 기법', '/images/chart/volume_analysis.jpg', 'chart', '2024-03-04 20:35:33'),
       (2, '[전문가] 제도권 진입 영향', '/images/expert/institutional.jpg', 'expert', '2024-03-05 09:10:48'),
       (3, '퀀트 트레이딩 수익', '/images/pnl/quant_trading.jpg', 'pnl', '2024-03-05 14:25:22'),
       (4, '블록체인 컨퍼런스 후기', NULL, 'free', '2024-03-05 19:50:15'),
       (5, '웹3.0 프로젝트 분석', '/images/expert/web3.jpg', 'expert', '2024-03-06 10:15:33'),
       (1, '차트 기술적 분석', '/images/chart/technical_analysis.jpg', 'chart', '2024-03-06 15:40:27'),
       (2, '리플리스팅 영향 분석', '/images/expert/relisting.jpg', 'expert', '2024-03-06 20:55:18'),
       (3, 'ICO 투자 수익 인증', '/images/pnl/ico_profit.jpg', 'pnl', '2024-03-07 09:30:42'),
       (4, '코인 커뮤니티 모임', NULL, 'free', '2024-03-07 14:45:15'),
       (5, '시장 심리 분석', '/images/chart/market_psychology.jpg', 'chart', '2024-03-07 19:20:33');

INSERT INTO comments (post_no, user_no, parent_no, comment_cont, comment_created_at)
VALUES (1, 2, NULL, '드디어 올 것이 왔네요!', '2024-02-07 10:30:45'),
       (3, 4, NULL, '손실도 경험입니다... 파이팅하세요', '2024-02-07 21:20:45'),
       (1, 3, NULL, '가즈아!!', '2024-02-07 11:20:15'),
       (2, 3, NULL, '차트 분석 감사합니다', '2024-02-07 16:45:22'),
       (1, 4, 1, '저도 동감입니다. 드디어 버틴 보람이 있네요', '2024-02-07 15:45:30'),
       (4, 5, NULL, '전망이 매우 상세하네요', '2024-02-08 13:45:15'),
       (2, 4, NULL, '저도 같은 패턴 보고 있었어요', '2024-02-07 19:15:48'),
       (1, 5, 1, '이제 시작입니다', '2024-02-07 18:25:12'),
       (3, 5, NULL, '다음에는 꼭 수익 보실 거예요', '2024-02-08 12:40:18'),
       (5, 1, NULL, '진짜 서버 언제 고치나...', '2024-02-08 17:30:42'),
       (2, 5, 6, '맞습니다. 이 패턴은 신뢰도가 높죠', '2024-02-08 02:30:15'),
       (4, 1, NULL, '분석 감사합니다', '2024-02-08 16:20:33'),
       (3, 1, 10, '저도 비슷한 경험 있습니다', '2024-02-08 18:15:27'),
       (5, 2, NULL, '모바일로 하세요 pc보다 낫더라구요', '2024-02-08 19:45:15'),
       (4, 2, 14, '추가 의견 있습니다', '2024-02-08 21:10:48'),
       (1, 1, 2, '흐름이 좋아보이네요', '2024-02-07 20:10:33'),
       (5, 3, 14, '모바일도 불안정해요', '2024-02-09 01:20:33'),
       (2, 1, 7, '추가 설명 부탁드립니다', '2024-02-08 07:45:33'),
       (6, 2, NULL, '골든크로스 맞네요!', '2024-02-09 10:25:15'),
       (3, 2, 10, '리스크 관리가 중요하죠', '2024-02-08 22:30:42'),
       (4, 3, 14, '좋은 인사이트네요', '2024-02-09 08:25:22'),
       (5, 4, 19, '앱 최신 버전으로 업데이트 해보세요', '2024-02-09 07:15:48'),
       (6, 3, NULL, '매수 타이밍인가요?', '2024-02-09 14:50:33'),
       (5, 5, 19, '저도 업데이트 하니까 괜찮아졌어요', '2024-02-09 09:40:22'),
       (6, 4, 22, '신중한 접근이 필요해보입니다', '2024-02-09 19:15:27'),
       (6, 5, 22, '보조지표도 같이 봐야겠네요', '2024-02-09 22:30:18'),
       (7, 1, NULL, '3년 존버 대단하십니다', '2024-02-09 10:15:33'),
       (8, 5, NULL, '머지 이후 확실히 달라졌죠', '2024-02-09 15:30:27'),
       (7, 3, 27, '저도 2년째 존버 중입니다', '2024-02-09 17:45:12'),
       (9, 2, NULL, 'ETF 상장 이후 전망이 궁금했는데 좋은 분석이네요', '2024-02-09 20:25:18'),
       (8, 4, 28, '가스비가 많이 줄었어요', '2024-02-09 22:40:33'),
       (10, 1, NULL, '알트코인 차트 보는 눈이 대단하십니다', '2024-02-10 12:15:45'),
       (9, 3, 30, '기관 자금 유입이 관건이겠네요', '2024-02-10 14:30:22'),
       (11, 5, NULL, '축하드립니다! 멋진 수익이네요', '2024-02-10 17:25:48'),
       (10, 2, 32, '패턴이 잘 보이네요', '2024-02-10 19:40:15'),
       (12, 4, NULL, '어떤 코인이 상장되나요?', '2024-02-10 21:55:33'),
       (11, 3, 34, '장기 투자가 답인 것 같아요', '2024-02-11 09:20:27'),
       (13, 1, NULL, '레이어2 솔루션이 미래네요', '2024-02-11 11:45:42'),
       (12, 5, 36, '루머라던데 곧 공식 발표 있을 것 같아요', '2024-02-11 13:30:15'),
       (14, 2, NULL, '주간 차트로 보니 또 다르네요', '2024-02-11 16:15:33'),
       (13, 4, 38, '확장성 문제 해결이 핵심이죠', '2024-02-11 18:40:27'),
       (15, 3, NULL, '레버리지는 항상 조심해야죠', '2024-02-11 22:25:48'),
       (14, 5, 40, '월간 차트도 분석해주세요!', '2024-02-12 10:30:15'),
       (16, 1, NULL, '김프 줄어드는 추세네요', '2024-02-12 11:45:33'),
       (15, 2, 42, '배수 조절이 중요합니다', '2024-02-12 13:20:27'),
       (17, 4, NULL, '지지선 잘 잡으셨네요', '2024-02-12 15:55:42'),
       (16, 3, 44, '그만큼 시장이 안정화된거죠', '2024-02-12 17:30:15'),
       (18, 5, NULL, '솔라나 요즘 핫하던데 잘하셨네요', '2024-02-12 20:15:33'),
       (17, 1, 46, '저항선도 분석해주세요', '2024-02-12 22:40:27'),
       (19, 2, NULL, '메이저 알트 시즌 올까요?', '2024-02-13 11:25:48'),
       (18, 4, 48, '단타는 진짜 실력자네요', '2024-02-13 13:50:15'),
       (20, 3, NULL, '상장되면 좋겠네요', '2024-02-13 16:15:33'),
       (81, 2, NULL, '거래량 분석 정말 도움되네요', '2024-03-04 21:15:33'),
       (82, 4, NULL, '제도권 진입으로 시장 안정화될 것 같아요', '2024-03-05 10:30:27'),
       (81, 3, 51, '거래량이 핵심이죠', '2024-03-04 23:45:12'),
       (83, 5, NULL, '퀀트 수익률이 대단하네요', '2024-03-05 15:25:18'),
       (82, 1, 52, '기관 자금 유입도 늘겠죠', '2024-03-05 12:40:33'),
       (84, 3, NULL, '컨퍼런스 내용 공유 감사합니다', '2024-03-05 20:15:45'),
       (83, 2, 54, '전략 공유 가능하신가요?', '2024-03-05 17:30:22'),
       (85, 4, NULL, '웹3.0 전망 좋아보이네요', '2024-03-06 11:25:48'),
       (84, 5, 56, '다음 컨퍼런스는 언제인가요?', '2024-03-05 22:40:15'),
       (86, 1, NULL, '기술적 분석 잘 보고 갑니다', '2024-03-06 16:55:33'),
       (85, 3, 58, '구체적인 프로젝트가 궁금해요', '2024-03-06 13:20:27'),
       (87, 2, NULL, '리플 상장 효과 클 것 같네요', '2024-03-06 21:45:42'),
       (86, 4, 60, '보조지표 활용이 인상적이네요', '2024-03-06 18:30:15'),
       (88, 5, NULL, 'ICO 투자 팁 더 있나요?', '2024-03-07 10:15:33'),
       (87, 1, 62, '가격 상승 가능성 높아보입니다', '2024-03-06 23:40:27'),
       (89, 3, NULL, '모임 장소가 어디인가요?', '2024-03-07 15:25:48'),
       (88, 2, 64, '화이트페이퍼 분석이 중요하죠', '2024-03-07 12:30:15'),
       (90, 4, NULL, '시장 심리 분석 좋네요', '2024-03-07 20:15:33'),
       (89, 5, 66, '저도 참석하고 싶습니다', '2024-03-07 17:40:27'),
       (90, 1, 68, '공포 탐욕 지수랑 비슷하네요', '2024-03-07 21:55:42');

-- chatting_logs 테이블
INSERT INTO chatting_logs (user_no, content)
VALUES (1, '안녕하세요'),
       (2, '차트 어떻게 보시나요?'),
       (3, '지금 매수하기 좋은 타이밍 같아요'),
       (4, '장기 투자 추천드립니다'),
       (5, '오늘 시장이 좋네요'),
       (1, '새로운 코인 상장 소식입니다'),
       (2, '투자 조심하세요'),
       (3, '좋은 정보 감사합니다'),
       (4, '다들 화이팅하세요'),
       (5, '매수 타이밍 찾는 중');

-- post_likes 테이블
INSERT INTO post_likes (user_no, post_no, like_type)
VALUES (1, 1, true),
       (2, 1, true),
       (3, 1, false),
       (4, 1, true),
       (5, 1, true),
       (1, 2, true),
       (2, 2, false),
       (3, 2, true),
       (4, 2, true),
       (1, 3, true),
       (2, 3, true),
       (3, 3, true),
       (4, 3, false),
       (5, 3, true),
       (2, 4, true),
       (3, 4, false),
       (4, 4, true),
       (5, 4, true),
       (1, 5, false),
       (2, 5, true),
       (3, 5, true),
       (4, 5, true),
       (1, 6, true),
       (2, 6, true),
       (3, 6, false),
       (4, 6, true),
       (5, 6, true),
       (1, 7, true),
       (2, 7, true),
       (3, 7, true),
       (4, 7, false),
       (1, 8, false),
       (2, 8, true),
       (3, 8, true),
       (4, 8, true),
       (5, 8, true),
       (1, 9, true),
       (2, 9, false),
       (3, 9, true),
       (4, 9, true),
       (5, 9, true),
       (1, 10, true),
       (2, 10, true),
       (3, 10, false),
       (5, 10, true),
       (1, 11, true),
       (2, 11, true),
       (3, 11, true),
       (4, 11, false),
       (5, 11, true),
       (1, 12, false),
       (2, 12, true),
       (3, 12, true),
       (4, 12, true),
       (1, 13, true),
       (2, 13, true),
       (3, 13, false),
       (4, 13, true),
       (5, 13, true),
       (1, 14, true),
       (2, 14, true),
       (3, 14, true),
       (5, 14, false),
       (1, 15, true),
       (2, 15, false),
       (3, 15, true),
       (4, 15, true),
       (5, 15, true),
       (1, 16, true),
       (2, 16, true),
       (4, 16, true),
       (5, 16, false),
       (1, 17, false),
       (2, 17, true),
       (3, 17, true),
       (4, 17, true),
       (5, 17, true),
       (1, 18, true),
       (2, 18, true),
       (3, 18, false),
       (4, 18, true),
       (1, 19, true),
       (2, 19, true),
       (3, 19, true),
       (5, 19, false),
       (1, 20, true),
       (2, 20, false),
       (3, 20, true),
       (4, 20, true),
       (5, 20, true),
       (1, 21, true),
       (2, 21, true),
       (3, 21, false),
       (4, 21, true),
       (5, 21, true),
       (1, 22, true),
       (2, 22, true),
       (3, 22, true),
       (4, 22, false),
       (1, 23, false),
       (2, 23, true),
       (3, 23, true),
       (4, 23, true),
       (5, 23, true),
       (1, 24, true),
       (2, 24, false),
       (3, 24, true),
       (4, 24, true),
       (1, 25, true),
       (2, 25, true),
       (3, 25, true),
       (5, 25, false),
       (1, 26, true),
       (2, 26, false),
       (3, 26, true),
       (4, 26, true),
       (5, 26, true),
       (1, 27, true),
       (2, 27, true),
       (3, 27, true),
       (4, 27, false),
       (5, 27, true),
       (1, 28, true),
       (2, 28, true),
       (4, 28, true),
       (5, 28, false),
       (1, 29, false),
       (2, 29, true),
       (3, 29, true),
       (4, 29, true),
       (1, 30, true),
       (2, 30, true),
       (3, 30, true),
       (4, 30, false),
       (5, 30, true),
       (1, 31, true),
       (2, 31, true),
       (3, 31, true),
       (4, 31, false),
       (5, 31, true),
       (1, 32, true),
       (2, 32, false),
       (3, 32, true),
       (4, 32, true),
       (1, 33, true),
       (2, 33, true),
       (3, 33, false),
       (4, 33, true),
       (5, 33, true),
       (1, 34, true),
       (2, 34, true),
       (3, 34, true),
       (5, 34, false),
       (1, 35, false),
       (2, 35, true),
       (3, 35, true),
       (4, 35, true),
       (5, 35, true),
       (1, 36, true),
       (2, 36, true),
       (4, 36, false),
       (5, 36, true),
       (1, 37, true),
       (2, 37, true),
       (3, 37, true),
       (4, 37, false),
       (5, 37, true),
       (1, 38, true),
       (2, 38, true),
       (3, 38, true),
       (4, 38, true),
       (1, 39, true),
       (2, 39, true),
       (3, 39, false),
       (4, 39, true),
       (5, 39, true),
       (1, 40, true),
       (2, 40, true),
       (3, 40, true),
       (5, 40, false),
       (1, 41, true),
       (2, 41, false),
       (3, 41, true),
       (4, 41, true),
       (5, 41, true),
       (1, 42, true),
       (2, 42, true),
       (3, 42, true),
       (4, 42, false),
       (1, 43, true),
       (2, 43, true),
       (3, 43, false),
       (4, 43, true),
       (5, 43, true),
       (1, 44, false),
       (2, 44, true),
       (3, 44, true),
       (4, 44, true),
       (1, 45, true),
       (2, 45, true),
       (3, 45, true),
       (5, 45, false),
       (1, 46, true),
       (2, 46, false),
       (3, 46, true),
       (4, 46, true),
       (5, 46, true),
       (1, 47, true),
       (2, 47, true),
       (3, 47, true),
       (4, 47, false),
       (5, 47, true),
       (1, 48, true),
       (2, 48, true),
       (4, 48, true),
       (5, 48, false),
       (1, 49, false),
       (2, 49, true),
       (3, 49, true),
       (4, 49, true),
       (1, 50, true),
       (2, 50, true),
       (3, 50, true),
       (4, 50, false),
       (5, 50, true),
       (1, 51, true),
       (2, 51, true),
       (3, 51, false),
       (4, 51, true),
       (5, 51, true),
       (1, 52, true),
       (2, 52, true),
       (3, 52, true),
       (4, 52, false),
       (1, 53, false),
       (2, 53, true),
       (3, 53, true),
       (4, 53, true),
       (5, 53, true),
       (1, 54, true),
       (2, 54, false),
       (3, 54, true),
       (4, 54, true),
       (1, 55, true),
       (2, 55, true),
       (3, 55, true),
       (5, 55, false),
       (1, 56, true),
       (2, 56, true),
       (3, 56, false),
       (4, 56, true),
       (5, 56, true),
       (1, 57, true),
       (2, 57, false),
       (3, 57, true),
       (4, 57, true),
       (5, 57, true),
       (1, 58, true),
       (2, 58, true),
       (3, 58, true),
       (5, 58, false),
       (1, 59, true),
       (2, 59, true),
       (3, 59, false),
       (4, 59, true),
       (1, 60, false),
       (2, 60, true),
       (3, 60, true),
       (4, 60, true),
       (5, 60, true),
       (1, 61, true),
       (2, 61, true),
       (3, 61, false),
       (4, 61, true),
       (5, 61, true),
       (1, 62, true),
       (2, 62, false),
       (3, 62, true),
       (4, 62, true),
       (1, 63, true),
       (2, 63, true),
       (3, 63, true),
       (4, 63, false),
       (5, 63, true),
       (1, 64, true),
       (2, 64, true),
       (3, 64, true),
       (5, 64, false),
       (1, 65, false),
       (2, 65, true),
       (3, 65, true),
       (4, 65, true),
       (5, 65, true),
       (1, 66, true),
       (2, 66, true),
       (4, 66, false),
       (5, 66, true),
       (1, 67, true),
       (2, 67, true),
       (3, 67, true),
       (4, 67, false),
       (5, 67, true),
       (1, 68, false),
       (2, 68, true),
       (3, 68, true),
       (4, 68, true),
       (1, 69, true),
       (2, 69, true),
       (3, 69, false),
       (4, 69, true),
       (5, 69, true),
       (1, 70, true),
       (2, 70, true),
       (3, 70, true),
       (5, 70, false),
       (1, 71, true),
       (2, 71, false),
       (3, 71, true),
       (4, 71, true),
       (5, 71, true),
       (1, 72, true),
       (2, 72, true),
       (3, 72, true),
       (4, 72, false),
       (1, 73, true),
       (2, 73, true),
       (3, 73, false),
       (4, 73, true),
       (5, 73, true),
       (1, 74, false),
       (2, 74, true),
       (3, 74, true),
       (4, 74, true),
       (1, 75, true),
       (2, 75, true),
       (3, 75, true),
       (5, 75, false),
       (1, 76, true),
       (2, 76, false),
       (3, 76, true),
       (4, 76, true),
       (5, 76, true),
       (1, 77, true),
       (2, 77, true),
       (3, 77, true),
       (4, 77, false),
       (5, 77, true),
       (1, 78, true),
       (2, 78, true),
       (4, 78, true),
       (5, 78, false),
       (1, 79, false),
       (2, 79, true),
       (3, 79, true),
       (4, 79, true),
       (1, 80, true),
       (2, 80, true),
       (3, 80, true),
       (4, 80, false),
       (5, 80, true),
       (1, 81, true),
       (2, 81, true),
       (3, 81, false),
       (4, 81, true),
       (5, 81, true),
       (1, 82, true),
       (2, 82, true),
       (3, 82, true),
       (4, 82, false),
       (1, 83, false),
       (2, 83, true),
       (3, 83, true),
       (4, 83, true),
       (5, 83, true),
       (1, 84, true),
       (2, 84, false),
       (3, 84, true),
       (4, 84, true),
       (1, 85, true),
       (2, 85, true),
       (3, 85, true),
       (5, 85, false),
       (1, 86, true),
       (2, 86, true),
       (3, 86, false),
       (4, 86, true),
       (5, 86, true),
       (1, 87, true),
       (2, 87, false),
       (3, 87, true),
       (4, 87, true),
       (5, 87, true),
       (1, 88, true),
       (2, 88, true),
       (3, 88, true),
       (5, 88, false),
       (1, 89, true),
       (2, 89, true),
       (3, 89, false),
       (4, 89, true),
       (1, 90, false),
       (2, 90, true),
       (3, 90, true),
       (4, 90, true),
       (5, 90, true);

-- post_bookmarks 테이블
INSERT INTO post_bookmarks (user_no, post_no)
VALUES (1, 2),
       (2, 1),
       (3, 4),
       (4, 3),
       (5, 1),
       (1, 5),
       (2, 3),
       (3, 2),
       (4, 5),
       (5, 4);

-- comment_likes 테이블
INSERT INTO comment_likes (user_no, comment_no, like_type)
VALUES (1, 1, true),
       (3, 2, true),
       (2, 4, true),
       (4, 5, true),
       (5, 7, false),
       (1, 8, true),
       (2, 10, true),
       (4, 12, true),
       (3, 15, true),
       (5, 16, false),
       (2, 22, true),
       (4, 25, true),
       (1, 27, true),
       (3, 30, true),
       (5, 32, false),
       (2, 35, true),
       (4, 37, true),
       (1, 38, true),
       (3, 40, true),
       (2, 42, true),
       (5, 45, true),
       (1, 48, true),
       (4, 51, true),
       (3, 54, false),
       (2, 57, true),
       (5, 60, true),
       (1, 63, true),
       (4, 66, true),
       (3, 69, true);

-- password_change_logs 테이블
INSERT INTO password_change_logs (user_no, changed_password)
VALUES (1, 'newpass123'),
       (2, 'newpass456'),
       (3, 'newpass789'),
       (4, 'newpass321'),
       (5, 'newpass654'),
       (1, 'newpass987'),
       (2, 'newpass147'),
       (3, 'newpass258'),
       (4, 'newpass369'),
       (5, 'newpass741');

-- login_logs 테이블
INSERT INTO login_logs (user_no)
VALUES (1),
       (2),
       (3),
       (4),
       (5),
       (1),
       (2),
       (3),
       (4),
       (5);

-- exp_activity 테이블
INSERT INTO exp_activity (activity_type, exp_amount, description)
VALUES ('LOGIN', 10, '일일 로그인'),
       ('POST_WRITE', 20, '게시글 작성'),
       ('COMMENT_WRITE', 5, '댓글 작성'),
       ('TRADE', 15, '거래 완료'),
       ('FOLLOW', 5, '팔로우하기'),
       ('RECEIVE_LIKE', 2, '좋아요 받기'),
       ('SHARE_POST', 10, '게시글 공유'),
       ('COMPLETE_PROFILE', 50, '프로필 완성'),
       ('FIRST_TRADE', 100, '첫 거래 완료'),
       ('DAILY_VISIT', 5, '일일 방문');

-- exp_logs 테이블
INSERT INTO exp_logs (user_no, activity_type)
VALUES (1, 'LOGIN'),
       (2, 'POST_WRITE'),
       (3, 'COMMENT_WRITE'),
       (4, 'TRADE'),
       (5, 'FOLLOW'),
       (1, 'RECEIVE_LIKE'),
       (2, 'SHARE_POST'),
       (3, 'COMPLETE_PROFILE'),
       (4, 'FIRST_TRADE'),
       (5, 'DAILY_VISIT');

-- level_exp 테이블
INSERT INTO level_exp (level, required_exp)
VALUES (1, 0),
       (2, 100),
       (3, 300),
       (4, 600),
       (5, 1000),
       (6, 1500),
       (7, 2100),
       (8, 2800),
       (9, 3600),
       (10, 4500);

-- guides 테이블
INSERT INTO guides (guide_title, guide_cont)
VALUES ('비트코인 시작하기', '비트코인 투자 입문자를 위한 가이드'),
       ('이더리움 스테이킹', '이더리움 2.0 스테이킹 방법 안내'),
       ('차트 보는 법', '기초적인 차트 분석 방법'),
       ('거래소 이용방법', '거래소 회원가입부터 거래까지'),
       ('보안 설정 가이드', '2차 인증 설정 방법'),
       ('용어 사전', '가상화폐 투자 필수 용어 정리'),
       ('투자 전략 가이드', '기본적인 투자 전략 소개'),
       ('리스크 관리', '효과적인 리스크 관리 방법'),
       ('트레이딩뷰 사용법', '트레이딩뷰 차트 활용 가이드'),
       ('세금 가이드', '가상자산 과세 제도 안내');

-- questions 테이블
INSERT INTO questions (user_no, question_title, question_cont, question_status, question_option)
VALUES (1, '출금 문의', '출금이 지연되고 있습니다', 'PENDING', 'URGENT'),
       (2, '로그인 오류', '로그인이 되지 않습니다', 'IN_PROGRESS', 'NORMAL'),
       (3, 'API 오류', 'API 연동 중 오류가 발생했습니다', 'COMPLETED', 'TECHNICAL'),
       (4, '계정 복구', '계정 복구를 요청합니다', 'PENDING', 'URGENT'),
       (5, '입금 문의', '입금이 반영되지 않았습니다', 'IN_PROGRESS', 'URGENT'),
       (1, '인증 문의', '휴대폰 인증이 안됩니다', 'COMPLETED', 'NORMAL'),
       (2, '거래 오류', '거래 주문이 실행되지 않습니다', 'PENDING', 'TECHNICAL'),
       (3, '출금 한도', '출금 한도 증액 요청', 'IN_PROGRESS', 'NORMAL'),
       (4, '계정 보안', '보안 설정 방법 문의', 'COMPLETED', 'NORMAL'),
       (5, '입금 주소', '입금 주소 확인 요청', 'PENDING', 'URGENT');

-- answers 테이블
INSERT INTO answers (question_no, answer_cont)
VALUES (1, '출금 지연 관련 답변입니다...'),
       (2, '로그인 오류 해결 방법입니다...'),
       (3, 'API 오류 해결 방법 안내드립니다...'),
       (4, '계정 복구 절차 안내드립니다...'),
       (5, '입금 확인 후 처리해드렸습니다...'),
       (6, '인증 절차 안내드립니다...'),
       (7, '거래 오류 관련 답변드립니다...'),
       (8, '출금 한도 검토 후 답변드리겠습니다...'),
       (9, '보안 설정 가이드 링크 첨부합니다...'),
       (10, '입금 주소 확인 방법 안내드립니다...');

INSERT INTO faq (faq_title, faq_cont) VALUES
                                             ('시드머니는 어떻게 얻나요?', '시드머니는 자산현황 화면에서 ''내자산 추가하기'' 버튼을 클릭하여 획득이 가능합니다.'),
                                             ('비밀번호를 잊어버렸어요.', '로그인 화면에서 ''비밀번호 찾기''를 클릭하시면 가입하신 이메일로 임시 비밀번호를 발송해드립니다.'),
                                             ('회원 탈퇴는 어떻게 하나요?', '설정 > 계정관리 > 회원탈퇴 메뉴에서 진행하실 수 있습니다. 탈퇴 시 모든 데이터는 삭제되며 복구가 불가능합니다 알겠냐?.'),
                                             ('자동 거래는 어떻게 설정하나요?', '거래소 화면에서 ''자동거래 설정''을 선택하신 후, 원하시는 조건을 설정하시면 됩니다. 24시간 자동으로 거래가 진행됩니다.'),
                                             ('입금은 즉시 반영되나요?', '은행 영업일 기준 10분 이내로 입금이 반영됩니다. 단, 은행 점검 시간이나 공휴일에는 지연될 수 있습니다.'),
                                             ('출금 한도는 얼마인가요?', '기본 출금 한도는 1일 3,000만원입니다. 추가 인증을 완료하시면 한도를 상향 조정받으실 수 있습니다.'),
                                             ('거래 수수료는 얼마인가요?', '일반 거래 시 0.05%, VIP 등급의 경우 0.03%의 수수료가 적용됩니다. 자세한 수수료 정책은 고객센터에서 확인 가능합니다.'),
                                             ('모바일 앱은 언제 출시되나요?', '현재 모바일 앱을 개발 중에 있으며, 2024년 상반기 중 안드로이드와 iOS 버전이 동시 출시될 예정입니다.'),
                                             ('해외 사용자도 가입할 수 있나요?', '현재는 국내 사용자만 서비스 이용이 가능합니다. 해외 서비스는 2024년 하반기부터 단계적으로 오픈할 예정입니다.'),
                                             ('거래 내역은 어디서 확인하나요?', '마이페이지 > 거래내역 메뉴에서 기간별, 종류별로 모든 거래 내역을 확인하실 수 있습니다.');
