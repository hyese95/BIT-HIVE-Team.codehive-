-- users 테이블 (50명의 다양한 유저)
INSERT INTO users (user_id, password, nickname, email, phone, privacy_agreements, marketing_agreements, nationality,
                   gender, birth_date, name, self_introduction, role)
VALUES 

('user1', 'pass123', '비트맨', 'user1@example.com', '010-2222-0001', true, true, 'KR', 'MALE', '1990-01-01', '김철수', '비트코인 장기투자자', 'USER'),
('user2', 'pass123', '코인女', 'user2@example.com', '010-2222-0002', true, false, 'KR', 'FEMALE', '1992-02-02', '이영희', '알트코인 단타 전문', 'USER'),
('user3', 'pass123', '투자의신', 'user3@example.com', '010-2222-0003', true, true, 'KR', 'MALE', '1988-03-03', '박민수', '3년차 존버중', 'USER'),
('user4', 'pass123', '존버킹', 'user4@example.com', '010-2222-0004', true, true, 'KR', 'MALE', '1995-04-04', '정대만', '존버는 승리한다', 'USER'),
('user5', 'pass123', '단타여신', 'user5@example.com', '010-2222-0005', true, false, 'KR', 'FEMALE', '1993-05-05', '최지은', '단타 전문', 'USER'),
('user6', 'pass123', '코인요정', 'user6@example.com', '010-2222-0006', true, true, 'KR', 'FEMALE', '1991-06-06', '송미라', '비트코인 마이너', 'USER'),
('user7', 'pass123', '황소장', 'user7@example.com', '010-2222-0007', true, true, 'KR', 'MALE', '1987-07-07', '황길동', '강세장 전문가', 'USER'),
('user8', 'pass123', '곰돌이', 'user8@example.com', '010-2222-0008', true, false, 'KR', 'MALE', '1994-08-08', '곽진태', '약세장 전문가', 'USER'),
('user9', 'pass123', '알트하는여자', 'user9@example.com', '010-2222-0009', true, true, 'KR', 'FEMALE', '1996-09-09', '한미영', '알트코인 분석가', 'USER'),
('user10', 'pass123', '김코린이', 'user10@example.com', '010-2222-0010', true, true, 'KR', 'MALE', '1997-10-10', '김초보', '열심히 공부중입니다', 'USER'),
('user11', 'pass123', '이더리움맨', 'user11@example.com', '010-2222-0011', true, false, 'KR', 'MALE', '1989-11-11', '이더훈', '이더리움 맥시멀리스트', 'USER'),
('user12', 'pass123', '리플여신', 'user12@example.com', '010-2222-0012', true, true, 'KR', 'FEMALE', '1990-12-12', '정리플', 'XRP 홀더', 'USER'),
('user13', 'pass123', '도지코인러', 'user13@example.com', '010-2222-0013', true, true, 'KR', 'MALE', '1992-01-13', '도지킴', '밈코인 전문가', 'USER'),
('user14', 'pass123', '솔라나맨', 'user14@example.com', '010-2222-0014', true, false, 'KR', 'MALE', '1993-02-14', '솔라김', 'SOL 마니아', 'USER'),
('user15', 'pass123', '에이다여신', 'user15@example.com', '010-2222-0015', true, true, 'KR', 'FEMALE', '1994-03-15', '에이박', 'ADA 장기투자', 'USER'),
('user16', 'pass123', '폴카맨', 'user16@example.com', '010-2222-0016', true, true, 'KR', 'MALE', '1995-04-16', '폴카최', 'DOT 홀더', 'USER'),
('user17', 'pass123', '링크여신', 'user17@example.com', '010-2222-0017', true, false, 'KR', 'FEMALE', '1996-05-17', '링크정', 'LINK 믿음홀더', 'USER'),
('user18', 'pass123', '유니스왑킹', 'user18@example.com', '010-2222-0018', true, true, 'KR', 'MALE', '1997-06-18', '유니양', 'UNI 장기투자', 'USER'),
('user19', 'pass123', '테더지박령', 'user19@example.com', '010-2222-0019', true, true, 'KR', 'MALE', '1998-07-19', '테더송', '스테이블코인 전문가', 'USER'),
('user20', 'pass123', '메틱여신', 'user20@example.com', '010-2222-0020', true, false, 'KR', 'FEMALE', '1999-08-20', '메틱임', 'MATIC 홀더', 'USER'),
('user21', 'pass123', '코인초보', 'user21@example.com', '010-3333-0001', true, true, 'KR', 'MALE', '1991-01-21', '초보김', '열심히 배우는 중', 'USER'),
('user22', 'pass123', '투자신입', 'user22@example.com', '010-3333-0002', true, false, 'KR', 'FEMALE', '1992-02-22', '신입이', '첫 투자 시작했어요', 'USER'),
('user23', 'pass123', '블록체인맨', 'user23@example.com', '010-3333-0003', true, true, 'KR', 'MALE', '1993-03-23', '블록박', '블록체인 기술에 관심많아요', 'USER'),
('user24', 'pass123', '채굴러', 'user24@example.com', '010-3333-0004', true, true, 'KR', 'MALE', '1994-04-24', '채굴김', '채굴 시작했어요', 'USER'),
('user25', 'pass123', '스테이킹여신', 'user25@example.com', '010-3333-0005', true, false, 'KR', 'FEMALE', '1995-05-25', '스테장', '스테이킹 수익률 좋아요', 'USER'),
('user26', 'pass123', '디파이초보', 'user26@example.com', '010-3333-0006', true, true, 'KR', 'MALE', '1996-06-26', '디파최', 'DeFi 공부중입니다', 'USER'),
('user27', 'pass123', 'NFT수집가', 'user27@example.com', '010-3333-0007', true, true, 'KR', 'MALE', '1997-07-27', '엔프트', 'NFT 컬렉터입니다', 'USER'),
('user28', 'pass123', '메타버스킹', 'user28@example.com', '010-3333-0008', true, false, 'KR', 'MALE', '1998-08-28', '메타김', '메타버스 관련주 투자', 'USER'),
('user29', 'pass123', '레이어2여신', 'user29@example.com', '010-3333-0009', true, true, 'KR', 'FEMALE', '1999-09-29', '레이박', '레이어2 프로젝트 관심있어요', 'USER'),
('user30', 'pass123', '웹3맨', 'user30@example.com', '010-3333-0010', true, true, 'KR', 'MALE', '2000-10-30', '웹삼이', 'Web3.0 개발자입니다', 'USER'),
('user31', 'pass123', '스마트컨트랙트', 'user31@example.com', '010-3333-0011', true, false, 'KR', 'MALE', '1991-11-01', '스마정', '스마트컨트랙트 개발중', 'USER'),
('user32', 'pass123', '토큰이코노미', 'user32@example.com', '010-3333-0012', true, true, 'KR', 'FEMALE', '1992-12-02', '토큰양', '토큰이코노미 연구중', 'USER'),
('user33', 'pass123', '거래소맨', 'user33@example.com', '010-3333-0013', true, true, 'KR', 'MALE', '1993-01-03', '거래김', '거래소 API 개발자', 'USER'),
('user34', 'pass123', '차트초보', 'user34@example.com', '010-3333-0014', true, false, 'KR', 'MALE', '1994-02-04', '차트박', '차트 공부중이에요', 'USER'),
('user35', 'pass123', '코딩하는투자자', 'user35@example.com', '010-3333-0015', true, true, 'KR', 'MALE', '1995-03-05', '코딩이', '개발자겸 투자자입니다', 'USER'),
('user36', 'pass123', '마진여신', 'user36@example.com', '010-3333-0016', true, true, 'KR', 'FEMALE', '1996-04-06', '마진송', '마진거래 전문', 'USER'),
('user37', 'pass123', '선물왕', 'user37@example.com', '010-3333-0017', true, false, 'KR', 'MALE', '1997-05-07', '선물최', '선물거래 주력', 'USER'),
('user38', 'pass123', '스윙맨', 'user38@example.com', '010-3333-0018', true, true, 'KR', 'MALE', '1998-06-08', '스윙정', '스윙매매 전문', 'USER'),
('user39', 'pass123', '스캘핑여신', 'user39@example.com', '010-3333-0019', true, true, 'KR', 'FEMALE', '1999-07-09', '스캘프', '스캘핑 트레이더', 'USER'),
('user40', 'pass123', '포지션킹', 'user40@example.com', '010-3333-0020', true, false, 'KR', 'MALE', '2000-08-10', '포지션', '포지션 트레이딩 전문', 'USER');

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

-- 코인 거래(coin_transactions) 샘플 데이터 (BUY 거래와 SELL 거래를 균형있게 구성)
INSERT INTO coin_transactions (user_no, market, transaction_type, price, transaction_cnt, transaction_state) VALUES
-- 🟢 사용자 1 (BTC, ETH, ADA, SAND 거래)
(1, 'KRW-KRW', 'BUY', 1, 50000000, 'COMPLETED'),
(1, 'KRW-BTC', 'BUY', 78000000, 0.5, 'COMPLETED'),
(1, 'KRW-KRW', 'SELL', 1, 39000000, 'COMPLETED'),
(1, 'KRW-ETH', 'BUY', 4500000, 2, 'COMPLETED'),
(1, 'KRW-KRW', 'SELL', 1, 9000000, 'COMPLETED'),
(1, 'KRW-ADA', 'BUY', 1200, 1000, 'COMPLETED'),
(1, 'KRW-KRW', 'SELL', 1, 1200000, 'COMPLETED'),
(1, 'KRW-SAND', 'BUY', 5000, 500, 'COMPLETED'),
(1, 'KRW-KRW', 'SELL', 1, 2500000, 'COMPLETED'),
(1, 'KRW-BTC', 'SELL', 78200000, 0.2, 'COMPLETED'),
(1, 'KRW-KRW', 'BUY', 1, 15640000, 'COMPLETED'),
(1, 'KRW-ADA', 'SELL', 1250, 400, 'COMPLETED'),
(1, 'KRW-KRW', 'BUY', 1, 500000, 'COMPLETED'),

-- 🟠 사용자 1의 미체결 거래 (PENDING)
-- 보유 KRW 잔액: 약 14,440,000 KRW
-- 보유 코인: BTC 0.3개, ETH 2개, ADA 600개, SAND 500개
-- 미체결 매수 주문 (더 낮은 가격에 매수 주문)
(1, 'KRW-BTC', 'BUY', 76500000, 0.1, 'PENDING'),        -- 현재 시장가보다 낮은 가격에 BTC 매수 주문
(1, 'KRW-ETH', 'BUY', 4300000, 0.5, 'PENDING'),         -- 현재 시장가보다 낮은 가격에 ETH 매수 주문
(1, 'KRW-SOL', 'BUY', 180000, 10, 'PENDING'),           -- 새로운 코인 SOL 매수 주문
(1, 'KRW-XRP', 'BUY', 750, 2000, 'PENDING'),            -- 새로운 코인 XRP 매수 주문

-- 미체결 매도 주문 (더 높은 가격에 매도 주문)
(1, 'KRW-BTC', 'SELL', 81000000, 0.15, 'PENDING'),      -- 현재 시장가보다 높은 가격에 BTC 매도 주문
(1, 'KRW-ETH', 'SELL', 4800000, 1, 'PENDING'),          -- 현재 시장가보다 높은 가격에 ETH 매도 주문
(1, 'KRW-ADA', 'SELL', 1350, 300, 'PENDING'),           -- 현재 시장가보다 높은 가격에 ADA 매도 주문
(1, 'KRW-SAND', 'SELL', 5500, 250, 'PENDING'),          -- 현재 시장가보다 높은 가격에 SAND 매도 주문

-- 🟢 사용자 2 (ETH, QTUM, FLOW, ICX 거래)
(2, 'KRW-KRW', 'BUY', 1, 30000000, 'COMPLETED'),
(2, 'KRW-ETH', 'BUY', 4600000, 3, 'COMPLETED'),
(2, 'KRW-KRW', 'SELL', 1, 13800000, 'COMPLETED'),
(2, 'KRW-QTUM', 'BUY', 8200, 500, 'COMPLETED'),
(2, 'KRW-KRW', 'SELL', 1, 4100000, 'COMPLETED'),
(2, 'KRW-FLOW', 'BUY', 12000, 200, 'COMPLETED'),
(2, 'KRW-KRW', 'SELL', 1, 2400000, 'COMPLETED'),
(2, 'KRW-ICX', 'BUY', 450, 1000, 'COMPLETED'),
(2, 'KRW-KRW', 'SELL', 1, 450000, 'COMPLETED'),
(2, 'KRW-ETH', 'SELL', 4650000, 1, 'COMPLETED'),
(2, 'KRW-KRW', 'BUY', 1, 4650000, 'COMPLETED'),
(2, 'KRW-QTUM', 'SELL', 8300, 200, 'COMPLETED'),
(2, 'KRW-KRW', 'BUY', 1, 1660000, 'COMPLETED'),

-- 🟢 사용자 3 (SUI, WAVES, DOGE, BAT 거래)
(3, 'KRW-KRW', 'BUY', 1, 25000000, 'COMPLETED'),
(3, 'KRW-SUI', 'BUY', 1400, 5000, 'COMPLETED'),
(3, 'KRW-KRW', 'SELL', 1, 7000000, 'COMPLETED'),
(3, 'KRW-WAVES', 'BUY', 3500, 1000, 'COMPLETED'),
(3, 'KRW-KRW', 'SELL', 1, 3500000, 'COMPLETED'),
(3, 'KRW-DOGE', 'BUY', 160, 20000, 'COMPLETED'),
(3, 'KRW-KRW', 'SELL', 1, 3200000, 'COMPLETED'),
(3, 'KRW-BAT', 'BUY', 600, 5000, 'COMPLETED'),
(3, 'KRW-KRW', 'SELL', 1, 3000000, 'COMPLETED'),
(3, 'KRW-SUI', 'SELL', 1450, 2000, 'COMPLETED'),
(3, 'KRW-KRW', 'BUY', 1, 2900000, 'COMPLETED'),
(3, 'KRW-WAVES', 'SELL', 3600, 400, 'COMPLETED'),
(3, 'KRW-KRW', 'BUY', 1, 1440000, 'COMPLETED'),

-- 🟢 사용자 4 (JST, STMX, POLYX, THETA 거래)
(4, 'KRW-KRW', 'BUY', 1, 40000000, 'COMPLETED'),
(4, 'KRW-JST', 'BUY', 50, 50000, 'COMPLETED'),
(4, 'KRW-KRW', 'SELL', 1, 2500000, 'COMPLETED'),
(4, 'KRW-STMX', 'BUY', 30, 80000, 'COMPLETED'),
(4, 'KRW-KRW', 'SELL', 1, 2400000, 'COMPLETED'),
(4, 'KRW-POLYX', 'BUY', 450, 7000, 'COMPLETED'),
(4, 'KRW-KRW', 'SELL', 1, 3150000, 'COMPLETED'),
(4, 'KRW-THETA', 'BUY', 12000, 150, 'COMPLETED'),
(4, 'KRW-KRW', 'SELL', 1, 1800000, 'COMPLETED'),
(4, 'KRW-JST', 'SELL', 52, 20000, 'COMPLETED'),
(4, 'KRW-KRW', 'BUY', 1, 1040000, 'COMPLETED'),
(4, 'KRW-STMX', 'SELL', 32, 20000, 'COMPLETED'),
(4, 'KRW-KRW', 'BUY', 1, 640000, 'COMPLETED'),

-- 🟢 사용자 5 (ONDO, HIFI, CVC, ICX 거래)
(5, 'KRW-KRW', 'BUY', 1, 60000000, 'COMPLETED'),
(5, 'KRW-ONDO', 'BUY', 800, 4000, 'COMPLETED'),
(5, 'KRW-KRW', 'SELL', 1, 3200000, 'COMPLETED'),
(5, 'KRW-HIFI', 'BUY', 1500, 3000, 'COMPLETED'),
(5, 'KRW-KRW', 'SELL', 1, 4500000, 'COMPLETED'),
(5, 'KRW-CVC', 'BUY', 900, 5000, 'COMPLETED'),
(5, 'KRW-KRW', 'SELL', 1, 4500000, 'COMPLETED'),
(5, 'KRW-ICX', 'BUY', 440, 10000, 'COMPLETED'),
(5, 'KRW-KRW', 'SELL', 1, 4400000, 'COMPLETED'),
(5, 'KRW-ONDO', 'SELL', 820, 2000, 'COMPLETED'),
(5, 'KRW-KRW', 'BUY', 1, 1640000, 'COMPLETED'),
(5, 'KRW-HIFI', 'SELL', 1550, 1000, 'COMPLETED'),
(5, 'KRW-KRW', 'BUY', 1, 1550000, 'COMPLETED');

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

-- posts 테이블 (2025-02-18부터 2025-03-18까지, 총 90개)
INSERT INTO posts (user_no, post_cont, img_url, category, post_created_at)
VALUES 
-- 2025-03-18 (오늘)
(1, '비트코인 드디어 8천만원 돌파!', '/images/btc/price_80m.jpg', 'free', '2025-03-18 09:15:23'),
(12, '[차트] BTC 일봉 삼각수렴 패턴', '/images/chart/btc_pattern1.jpg', 'chart', '2025-03-18 14:30:45'),
(23, '오늘 비트코인 손실 인증합니다', '/images/pnl/loss1.jpg', 'pnl', '2025-03-18 20:45:12'),

-- 2025-03-17
(34, '[전문가칼럼] 2024년 하반기 전망', '/images/expert/analysis1.jpg', 'expert', '2025-03-17 10:20:33'),
(5, '업비트 서버 또 터졌네요ㅠㅠ', NULL, 'free', '2025-03-17 15:55:18'),
(16, '골든크로스 임박! 상세 분석', '/images/chart/golden_cross.jpg', 'chart', '2025-03-17 21:30:42'),

-- 2025-03-16
(27, '3년 존버 수익 인증', '/images/pnl/profit_3y.jpg', 'pnl', '2025-03-16 09:15:09'),
(38, '이더리움 머지 이후 변화', '/images/eth/merge_effect.jpg', 'free', '2025-03-16 14:40:55'),
(9, '[전문가] 비트코인 ETF 영향 분석', '/images/expert/etf_analysis.jpg', 'expert', '2025-03-16 19:25:30'),

-- 2025-03-15
(20, '알트코인 차트 패턴 분석', '/images/chart/alt_pattern.jpg', 'chart', '2025-03-15 11:10:22'),
(31, '2024 상반기 수익 인증', '/images/pnl/2024_h1.jpg', 'pnl', '2025-03-15 16:45:33'),
(2, '바이낸스 신규 상장 소식', NULL, 'free', '2025-03-15 20:20:15'),

-- 2025-03-14
(13, '[전문가] 레이어2 솔루션 분석', '/images/expert/layer2.jpg', 'expert', '2025-03-14 10:35:48'),
(24, '비트코인 주간 차트 분석', '/images/chart/btc_weekly.jpg', 'chart', '2025-03-14 15:50:27'),
(35, '선물 레버리지 수익 인증', '/images/pnl/futures_profit.jpg', 'pnl', '2025-03-14 21:15:39'),

-- 2025-03-13
(6, '요즘 김치프리미엄 현황', '/images/market/kimchi.jpg', 'free', '2025-03-13 09:30:42'),
(17, '[차트] 이더리움 지지선 분석', '/images/chart/eth_support.jpg', 'chart', '2025-03-13 14:45:15'),
(28, '솔라나 단타 수익 인증', '/images/pnl/sol_trade.jpg', 'pnl', '2025-03-13 19:20:33'),

-- 2025-03-12
(39, '[전문가] 메이저 알트코인 분석', '/images/expert/major_alt.jpg', 'expert', '2025-03-12 10:15:48'),
(10, '코인베이스 상장 루머', NULL, 'free', '2025-03-12 15:40:22'),
(21, '피보나치 되돌림 분석법', '/images/chart/fibonacci.jpg', 'chart', '2025-03-12 20:25:15'),

-- 2025-03-11
(32, '채굴 6개월 수익 인증', '/images/pnl/mining_6m.jpg', 'pnl', '2025-03-11 09:50:33'),
(3, '[전문가] DeFi 시장 전망', '/images/expert/defi_outlook.jpg', 'expert', '2025-03-11 14:15:27'),
(14, '거래소 해킹 사태 정리', NULL, 'free', '2025-03-11 19:30:18'),

-- 2025-03-10
(25, 'RSI 과매수 구간 분석', '/images/chart/rsi_analysis.jpg', 'chart', '2025-03-10 10:45:42'),
(36, '알트코인 물량 분석', '/images/expert/volume_analysis.jpg', 'expert', '2025-03-10 15:20:15'),
(7, '스테이킹 수익 인증', '/images/pnl/staking_profit.jpg', 'pnl', '2025-03-10 20:35:33'),

-- 2025-03-09
(18, '신규 코인 상장 정보', NULL, 'free', '2025-03-09 09:10:48'),
(29, '[차트] 다이버전스 포착', '/images/chart/divergence.jpg', 'chart', '2025-03-09 14:25:22'),
(40, '[전문가] NFT 시장 분석', '/images/expert/nft_market.jpg', 'expert', '2025-03-09 19:50:15'),

-- 2025-03-08
(11, '레버리지 3배 수익 인증', '/images/pnl/leverage_3x.jpg', 'pnl', '2025-03-08 10:15:33'),
(22, '거래소 점검 시간 안내', NULL, 'free', '2025-03-08 15:40:27'),
(33, '이동평균선 크로스 분석', '/images/chart/ma_cross.jpg', 'chart', '2025-03-08 20:55:18'),

-- 2025-03-07
(4, '[전문가] 메타버스 코인 전망', '/images/expert/metaverse.jpg', 'expert', '2025-03-07 09:30:42'),
(15, '단타 실패 손실 인증', '/images/pnl/short_loss.jpg', 'pnl', '2025-03-07 14:45:15'),
(26, '코인 커뮤니티 현황', NULL, 'free', '2025-03-07 19:20:33'),

-- 2025-03-06
(37, '볼린저밴드 활용법', '/images/chart/bollinger.jpg', 'chart', '2025-03-06 10:15:48'),
(8, '[전문가] 거시경제 영향 분석', '/images/expert/macro.jpg', 'expert', '2025-03-06 15:40:22'),
(19, '중장기 투자 수익 인증', '/images/pnl/mid_term.jpg', 'pnl', '2025-03-06 20:25:15'),

-- 2025-03-05
(30, '에어드랍 정보 공유', NULL, 'free', '2025-03-05 09:50:33'),
(1, 'MACD 신호 분석', '/images/chart/macd.jpg', 'chart', '2025-03-05 14:15:27'),
(12, '[전문가] 신규 프로젝트 리뷰', '/images/expert/new_project.jpg', 'expert', '2025-03-05 19:30:18'),

-- 2025-03-04
(23, '비트코인 숏 수익 인증', '/images/pnl/btc_short.jpg', 'pnl', '2025-03-04 10:45:42'),
(34, '마진거래 꿀팁 공유', NULL, 'free', '2025-03-04 15:20:15'),
(5, '삼각수렴 패턴 분석', '/images/chart/triangle.jpg', 'chart', '2025-03-04 20:35:33'),

-- 2025-03-03
(16, '[전문가] 채굴 난이도 분석', '/images/expert/mining_diff.jpg', 'expert', '2025-03-03 09:10:48'),
(27, '알트코인 손실 인증', '/images/pnl/alt_loss.jpg', 'pnl', '2025-03-03 14:25:22'),
(38, '코인 밋업 후기', NULL, 'free', '2025-03-03 19:50:15'),

-- 2025-03-02
(9, '채널 브레이크아웃 분석', '/images/chart/breakout.jpg', 'chart', '2025-03-02 10:15:33'),
(20, '[전문가] 규제 영향 분석', '/images/expert/regulation.jpg', 'expert', '2025-03-02 15:40:27'),
(31, '선물 청산 손실 인증', '/images/pnl/futures_loss.jpg', 'pnl', '2025-03-02 20:55:18'),

-- 2025-03-01
(2, '거래소 수수료 정보', NULL, 'free', '2025-03-01 09:30:42'),
(13, '추세선 활용 분석', '/images/chart/trendline.jpg', 'chart', '2025-03-01 14:45:15'),
(24, '[전문가] 시장 심리 분석', '/images/expert/sentiment.jpg', 'expert', '2025-03-01 19:20:33'),

-- 2025-02-28
(35, '스윙 매매 수익 인증', '/images/pnl/swing_trade.jpg', 'pnl', '2025-02-28 10:15:48'),
(6, '코인 세금 정책 정리', NULL, 'free', '2025-02-28 15:40:22'),
(17, '패턴 트레이딩 분석', '/images/chart/pattern_trading.jpg', 'chart', '2025-02-28 20:25:15'),

-- 2025-02-27
(28, '[전문가] 기술적 지표 해석', '/images/expert/technical.jpg', 'expert', '2025-02-27 09:50:33'),
(39, '장기 투자 수익 인증', '/images/pnl/long_term.jpg', 'pnl', '2025-02-27 14:15:27'),
(10, '신규 거래소 런칭 소식', NULL, 'free', '2025-02-27 19:30:18'),

-- 2025-02-26
(21, '지지/저항선 분석', '/images/chart/support_resistance.jpg', 'chart', '2025-02-26 10:45:42'),
(32, '[전문가] 블록체인 기술 전망', '/images/expert/blockchain.jpg', 'expert', '2025-02-26 15:20:15'),
(3, '데이트레이딩 수익 인증', '/images/pnl/day_trading.jpg', 'pnl', '2025-02-26 20:35:33'),

-- 2025-02-25
(14, '코인 커뮤니티 설문', NULL, 'free', '2025-02-25 09:10:48'),
(25, '차트 기본 패턴 분석', '/images/chart/basic_pattern.jpg', 'chart', '2025-02-25 14:25:22'),
(36, '[전문가] 토큰이코노미 분석', '/images/expert/tokenomics.jpg', 'expert', '2025-02-25 19:50:15'),

-- 2025-02-24
(7, '소액 투자 수익 인증', '/images/pnl/small_invest.jpg', 'pnl', '2025-02-24 10:15:33'),
(18, '거래소 이벤트 정보', NULL, 'free', '2025-02-24 15:40:27'),
(29, '캔들스틱 패턴 분석', '/images/chart/candlestick.jpg', 'chart', '2025-02-24 20:55:18'),

-- 2025-02-23
(40, '[전문가] 스마트컨트랙트 리뷰', '/images/expert/smart_contract.jpg', 'expert', '2025-02-23 09:30:42'),
(11, '존버 1년 수익 인증', '/images/pnl/hodl_1y.jpg', 'pnl', '2025-02-23 14:45:15'),
(22, '코인 용어 정리', NULL, 'free', '2025-02-23 19:20:33'),

-- 2025-02-22
(33, '차트 보는 법 강좌', '/images/chart/chart_guide.jpg', 'chart', '2025-02-22 10:15:48'),
(4, '[전문가] 가상자산 시장 전망', '/images/expert/market_outlook.jpg', 'expert', '2025-02-22 15:40:22'),
(15, '단기 스캘핑 수익 인증', '/images/pnl/scalping.jpg', 'pnl', '2025-02-22 20:25:15'),

-- 2025-02-21
(26, '거래소 API 활용법', NULL, 'free', '2025-02-21 09:50:33'),
(37, '엘리엇 파동 분석', '/images/chart/elliott_wave.jpg', 'chart', '2025-02-21 14:15:27'),
(8, '[전문가] 알트코인 시즌 분석', '/images/expert/altseason.jpg', 'expert', '2025-02-21 19:30:18'),

-- 2025-02-20
(19, '마진 롱 수익 인증', '/images/pnl/margin_long.jpg', 'pnl', '2025-02-20 10:45:42'),
(30, '코인 뉴스 정리', NULL, 'free', '2025-02-20 15:20:15'),
(1, '거래량 분석 기법', '/images/chart/volume_analysis.jpg', 'chart', '2025-02-20 20:35:33'),

-- 2025-02-19
(12, '[전문가] 제도권 진입 영향', '/images/expert/institutional.jpg', 'expert', '2025-02-19 09:10:48'),
(23, '퀀트 트레이딩 수익', '/images/pnl/quant_trading.jpg', 'pnl', '2025-02-19 14:25:22'),
(34, '블록체인 컨퍼런스 후기', NULL, 'free', '2025-02-19 19:50:15'),

-- 2025-02-18 (한달 전)
(5, '웹3.0 프로젝트 분석', '/images/expert/web3.jpg', 'expert', '2025-02-18 10:15:33'),
(16, '차트 기술적 분석', '/images/chart/technical_analysis.jpg', 'chart', '2025-02-18 15:40:27'),
(27, '리플리스팅 영향 분석', '/images/expert/relisting.jpg', 'expert', '2025-02-18 20:55:18'),
(38, 'ICO 투자 수익 인증', '/images/pnl/ico_profit.jpg', 'pnl', '2025-02-18 21:30:42'),
(9, '코인 커뮤니티 모임', NULL, 'free', '2025-02-18 22:45:15'),
(20, '시장 심리 분석', '/images/chart/market_psychology.jpg', 'chart', '2025-02-18 23:20:33');

-- comments 테이블 (2025-02-18부터 2025-03-18까지의 게시글 댓글)
INSERT INTO comments (post_no, user_no, parent_no, comment_cont, comment_created_at)
VALUES 
-- 1단계: 원댓글만 먼저 삽입 (parent_no가 NULL인 댓글)
(1, 6, NULL, '드디어 올 것이 왔네요!', '2025-03-18 10:15:23'),
(1, 28, NULL, '가즈아!!', '2025-03-18 11:20:15'),
(12, 32, NULL, '차트 분석 감사합니다', '2025-03-18 16:45:22'),
(12, 39, NULL, '저도 같은 패턴 보고 있었어요', '2025-03-18 17:15:48'),
(23, 17, NULL, '손실도 경험입니다... 파이팅하세요', '2025-03-18 21:20:45'),
(23, 14, NULL, '다음에는 꼭 수익 보실 거예요', '2025-03-18 22:40:18'),

-- 2025-03-17 게시글의 원댓글
(34, 21, NULL, '전망이 매우 상세하네요', '2025-03-17 12:45:15'),
(34, 7, NULL, '분석 감사합니다', '2025-03-17 14:20:33'),
(5, 25, NULL, '진짜 서버 언제 고치나...', '2025-03-17 16:30:42'),
(5, 29, NULL, '모바일로 하세요 pc보다 낫더라구요', '2025-03-17 17:45:15'),
(16, 4, NULL, '골든크로스 맞네요!', '2025-03-17 22:25:15'),
(16, 8, NULL, '매수 타이밍인가요?', '2025-03-17 23:50:33'),

-- 2025-03-16 게시글의 원댓글
(27, 12, NULL, '3년 존버 대단하십니다', '2025-03-16 10:15:33'),
(38, 23, NULL, '머지 이후 확실히 달라졌죠', '2025-03-16 15:30:27'),
(9, 5, NULL, 'ETF 상장 이후 전망이 궁금했는데 좋은 분석이네요', '2025-03-16 20:25:18'),

-- 2025-03-15 게시글의 원댓글
(20, 27, NULL, '알트코인 차트 보는 눈이 대단하십니다', '2025-03-15 12:15:45'),
(31, 9, NULL, '축하드립니다! 멋진 수익이네요', '2025-03-15 17:25:48'),

-- 2025-03-14 게시글의 원댓글
(13, 13, NULL, '레이어2 솔루션이 미래네요', '2025-03-14 11:45:42'),
(24, 35, NULL, '주간 차트로 보니 또 다르네요', '2025-03-14 16:15:33'),
(35, 17, NULL, '레버리지는 항상 조심해야죠', '2025-03-14 22:25:48'),

-- 2025-03-13 게시글의 원댓글
(6, 39, NULL, '김프 줄어드는 추세네요', '2025-03-13 10:45:33'),
(17, 21, NULL, '지지선 잘 잡으셨네요', '2025-03-13 15:55:42'),
(28, 3, NULL, '솔라나 요즘 핫하던데 잘하셨네요', '2025-03-13 20:15:33'),

-- 2025-03-12 게시글의 원댓글
(39, 25, NULL, '메이저 알트 시즌 올까요?', '2025-03-12 11:25:48'),
(10, 31, NULL, '어떤 코인이 상장되나요?', '2025-03-12 16:55:33'),
(21, 7, NULL, '상장되면 좋겠네요', '2025-03-12 21:15:33'),

-- 2025-03-03 ~ 2025-03-11 게시글의 원댓글 (몇 개 선택)
(32, 18, NULL, '거래량 분석 정말 도움되네요', '2025-03-11 10:15:33'),
(5, 11, NULL, '퀀트 수익률이 대단하네요', '2025-03-04 21:25:18'),
(16, 33, NULL, '컨퍼런스 내용 공유 감사합니다', '2025-03-03 20:15:45'),

-- 2025-02-18 ~ 2025-03-02 게시글의 원댓글 (나머지)
(1, 15, NULL, '웹3.0 전망 좋아보이네요', '2025-02-20 21:25:48'),
(27, 19, NULL, '리플 상장 효과 클 것 같네요', '2025-02-18 21:45:42'),
(38, 1, NULL, 'ICO 투자 팁 더 있나요?', '2025-02-18 21:15:33'),
(9, 23, NULL, '모임 장소가 어디인가요?', '2025-02-18 23:25:48'),
(20, 5, NULL, '시장 심리 분석 좋네요', '2025-02-18 23:15:33');

-- 2단계: 대댓글 삽입 (comment_no가 생성된 후 parent_no로 참조)
-- 첫 번째 댓글(comment_no=1)부터 차례대로 부여될 것을 가정
INSERT INTO comments (post_no, user_no, parent_no, comment_cont, comment_created_at)
VALUES
-- 2025-03-18 게시글의 대댓글
(1, 10, 1, '저도 동감입니다. 드디어 버틴 보람이 있네요', '2025-03-18 12:45:30'),
(1, 3, 1, '이제 시작입니다', '2025-03-18 14:25:12'),
(1, 11, 2, '흐름이 좋아보이네요', '2025-03-18 15:10:33'),
(12, 36, 4, '맞습니다. 이 패턴은 신뢰도가 높죠', '2025-03-18 18:30:15'),
(12, 33, 4, '추가 설명 부탁드립니다', '2025-03-18 19:45:33'),
(23, 18, 5, '저도 비슷한 경험 있습니다', '2025-03-18 23:15:27'),
(23, 15, 5, '리스크 관리가 중요하죠', '2025-03-19 00:30:40'),

-- 2025-03-17 게시글의 대댓글
(34, 40, 7, '추가 의견 있습니다', '2025-03-17 16:10:48'),
(34, 26, 7, '좋은 인사이트네요', '2025-03-17 18:25:22'),
(5, 22, 9, '모바일도 불안정해요', '2025-03-17 19:20:33'),
(5, 37, 9, '앱 최신 버전으로 업데이트 해보세요', '2025-03-17 20:15:48'),
(5, 19, 9, '저도 업데이트 하니까 괜찮아졌어요', '2025-03-17 21:40:22'),
(16, 30, 12, '신중한 접근이 필요해보입니다', '2025-03-18 01:15:27'),
(16, 1, 12, '보조지표도 같이 봐야겠네요', '2025-03-18 02:30:18'),

-- 2025-03-16 게시글의 대댓글
(27, 34, 13, '저도 2년째 존버 중입니다', '2025-03-16 11:45:12'),
(38, 16, 14, '가스비가 많이 줄었어요', '2025-03-16 17:40:33'),
(9, 38, 15, '기관 자금 유입이 관건이겠네요', '2025-03-16 21:30:22'),

-- 2025-03-15 게시글의 대댓글
(20, 20, 16, '패턴이 잘 보이네요', '2025-03-15 14:40:15'),
(31, 2, 17, '장기 투자가 답인 것 같아요', '2025-03-15 19:20:27'),

-- 2025-03-14 게시글의 대댓글
(13, 6, 18, '확장성 문제 해결이 핵심이죠', '2025-03-14 13:40:27'),
(24, 28, 19, '월간 차트도 분석해주세요!', '2025-03-14 18:30:15'),
(35, 10, 20, '배수 조절이 중요합니다', '2025-03-15 00:20:27'),

-- 2025-03-13 게시글의 대댓글
(6, 32, 21, '그만큼 시장이 안정화된거죠', '2025-03-13 12:30:15'),
(17, 14, 22, '저항선도 분석해주세요', '2025-03-13 17:40:27'),
(28, 36, 23, '단타는 진짜 실력자네요', '2025-03-13 21:50:15'),

-- 2025-03-12 게시글의 대댓글
(10, 24, 25, '루머라던데 곧 공식 발표 있을 것 같아요', '2025-03-12 18:30:15'),

-- 2025-03-03 ~ 2025-03-11 게시글의 대댓글
(32, 40, 27, '거래량이 핵심이죠', '2025-03-11 11:45:12'),
(5, 4, 28, '전략 공유 가능하신가요?', '2025-03-04 22:30:22'),
(16, 26, 29, '다음 컨퍼런스는 언제인가요?', '2025-03-03 21:40:15'),

-- 2025-02-18 ~ 2025-03-02 게시글의 대댓글
(1, 8, 30, '구체적인 프로젝트가 궁금해요', '2025-02-20 22:20:27'),
(27, 12, 31, '가격 상승 가능성 높아보입니다', '2025-02-18 22:40:27'),
(38, 34, 32, '화이트페이퍼 분석이 중요하죠', '2025-02-18 22:30:15'),
(9, 16, 33, '저도 참석하고 싶습니다', '2025-02-19 00:40:27'),
(20, 27, 34, '공포 탐욕 지수랑 비슷하네요', '2025-02-19 00:55:42');

-- post_likes 테이블 (게시글 좋아요/싫어요)
INSERT INTO post_likes (user_no, post_no, like_type)
VALUES
-- 최근 인기 게시글 (1번: 비트코인 8천만원 돌파)
(2, 1, true), (3, 1, true), (4, 1, true), (5, 1, true), 
(6, 1, true), (7, 1, true), (8, 1, true), (9, 1, true),
(10, 1, true), (11, 1, true), (12, 1, true), (13, 1, true),
(14, 1, true), (15, 1, true), (16, 1, false), (17, 1, true),
(18, 1, true), (19, 1, true), (20, 1, true), (21, 1, true),
(22, 1, true), (23, 1, false), (24, 1, true), (25, 1, true),
(26, 1, true), (27, 1, true), (28, 1, true), (29, 1, true),
(30, 1, true), (31, 1, false), (32, 1, true), (33, 1, true),
(34, 1, true), (35, 1, true), (36, 1, true), (37, 1, true),
(38, 1, true), (39, 1, true), (40, 1, true),

-- 차트 관련 인기 게시글 (12번: BTC 일봉 삼각수렴 패턴)
(1, 12, true), (3, 12, true), (5, 12, true), (6, 12, true),
(8, 12, true), (10, 12, true), (12, 12, true), (14, 12, true),
(16, 12, true), (18, 12, true), (20, 12, true), (22, 12, true),
(24, 12, true), (26, 12, true), (28, 12, true), (30, 12, false),
(32, 12, true), (34, 12, true), (36, 12, true), (38, 12, true),
(40, 12, true), (7, 12, true), (9, 12, true), (11, 12, false),
(13, 12, true), (15, 12, true), (17, 12, true), (19, 12, true),
(21, 12, true), (23, 12, true),

-- 전문가 분석 인기 게시글 (34번: 2024년 하반기 전망)
(1, 34, true), (2, 34, true), (3, 34, true), (4, 34, true),
(5, 34, true), (7, 34, true), (9, 34, true), (11, 34, true),
(13, 34, true), (15, 34, true), (17, 34, true), (19, 34, true),
(21, 34, true), (23, 34, true), (25, 34, true), (27, 34, false),
(29, 34, true), (31, 34, true), (33, 34, true), (35, 34, true),
(37, 34, true), (39, 34, true), (6, 34, true), (8, 34, true),
(10, 34, false), (12, 34, true),

-- 중간 인기 게시글들 (15-20개 좋아요)
-- 5번: 업비트 서버 또 터졌네요
(1, 5, false), (2, 5, true), (3, 5, true), (6, 5, true),
(8, 5, true), (10, 5, true), (12, 5, true), (14, 5, true),
(16, 5, true), (18, 5, true), (20, 5, false), (22, 5, true),
(24, 5, true), (26, 5, true), (28, 5, true), (30, 5, true),
(32, 5, true),

-- 16번: 골든크로스 임박! 상세 분석
(2, 16, true), (4, 16, true), (6, 16, true), (8, 16, true),
(10, 16, true), (12, 16, true), (14, 16, true), (17, 16, false),
(20, 16, true), (23, 16, true), (26, 16, true), (29, 16, true),
(32, 16, true), (35, 16, true), (38, 16, true), (40, 16, true),

-- 27번: 3년 존버 수익 인증
(1, 27, true), (3, 27, true), (5, 27, true), (7, 27, true),
(9, 27, true), (11, 27, true), (13, 27, true), (15, 27, false),
(17, 27, true), (19, 27, true), (21, 27, true), (23, 27, true),
(25, 27, true), (30, 27, true), (35, 27, true), (39, 27, true),

-- 9번: 비트코인 ETF 영향 분석
(2, 9, true), (4, 9, true), (6, 9, true), (8, 9, true),
(10, 9, true), (12, 9, false), (14, 9, true), (16, 9, true),
(18, 9, true), (20, 9, true), (22, 9, true), (24, 9, true),
(26, 9, true), (28, 9, true),

-- 중하위권 인기 게시글들 (8-14개 좋아요)
-- 20번: 알트코인 차트 패턴 분석
(1, 20, true), (7, 20, true), (13, 20, true), (19, 20, true),
(25, 20, false), (31, 20, true), (37, 20, true), (2, 20, true),
(8, 20, true), (14, 20, true), (39, 20, true),

-- 31번: 2024 상반기 수익 인증
(3, 31, true), (9, 31, true), (15, 31, true), (21, 31, true),
(27, 31, false), (33, 31, true), (39, 31, true), (4, 31, true),
(10, 31, true), (16, 31, true),

-- 2번: 바이낸스 신규 상장 소식
(5, 2, true), (11, 2, true), (17, 2, true), (23, 2, true),
(29, 2, false), (35, 2, true), (40, 2, true), (6, 2, true),
(12, 2, true),

-- 하위권 인기 게시글들 (3-7개 좋아요)
-- 13번: 레이어2 솔루션 분석
(1, 13, true), (7, 13, true), (13, 13, true), (19, 13, false),
(25, 13, true), (31, 13, true),

-- 24번: 비트코인 주간 차트 분석
(2, 24, true), (8, 24, true), (14, 24, false), (20, 24, true),
(26, 24, true),

-- 35번: 선물 레버리지 수익 인증
(3, 35, true), (9, 35, true), (15, 35, false), (21, 35, true),
(27, 35, true),

-- 그외 게시글들에도 소수의 좋아요/싫어요
-- 2월 게시글들도 몇 개씩 좋아요 포함
(4, 6, true), (10, 6, true), (16, 6, false), (22, 6, true),
(5, 17, true), (11, 17, true), (17, 17, true),
(6, 28, true), (12, 28, false), (18, 28, true),
(7, 39, true), (13, 39, true), (19, 39, true),
(8, 10, true), (14, 10, false),
(9, 21, true), (15, 21, true),
(10, 32, true), (16, 32, true), (22, 32, false),
(11, 3, true), (17, 3, true),
(12, 14, true), (18, 14, true),
(13, 25, false), (19, 25, true),
(14, 36, true), (20, 36, true),
(15, 7, true), (21, 7, true),
(16, 18, false), (22, 18, true),
(17, 29, true), (23, 29, true),
(18, 40, true), (24, 40, true),
(19, 11, false), (25, 11, true),
(20, 22, true), (26, 22, true),
(21, 33, true), (27, 33, false),
(22, 4, true), (28, 4, true),
(23, 15, true), (29, 15, true),
(24, 26, false), (30, 26, true),
(25, 37, true), (31, 37, true),
(26, 8, true), (32, 8, false),
(27, 19, true), (33, 19, true),
(28, 30, true), (34, 30, true),
(29, 38, false), (35, 38, true);

-- comment_likes 테이블 (댓글 좋아요/싫어요)
-- 원댓글과 대댓글이 모두 삽입된 후에 실행해야 합니다
INSERT INTO comment_likes (user_no, comment_no, like_type)
VALUES
-- 원댓글에 대한 좋아요/싫어요 (1번~35번 댓글)
-- 인기 게시글(1번)의 댓글들
(2, 1, true), (3, 1, true), (4, 1, true), (5, 1, true),
(7, 1, true), (9, 1, true), (11, 1, true), (13, 1, false),
(15, 1, true), (17, 1, true), (19, 1, true), (21, 1, true),
(23, 1, true), (25, 1, true),

(4, 2, true), (8, 2, true), (12, 2, true), (16, 2, true),
(20, 2, false), (24, 2, true), (28, 2, true), (32, 2, true),
(36, 2, true), (40, 2, true),

-- 12번 게시글(차트 분석)의 댓글들
(1, 3, true), (3, 3, true), (5, 3, true), (7, 3, true),
(9, 3, true), (11, 3, false), (13, 3, true), (15, 3, true),

(2, 4, true), (4, 4, true), (6, 4, true), (8, 4, true),
(10, 4, true), (12, 4, true),

-- 23번 게시글의 댓글들
(1, 5, true), (5, 5, true), (9, 5, false), (13, 5, true),
(17, 5, true),

(2, 6, true), (6, 6, true), (10, 6, true), (14, 6, false),
(18, 6, true),

-- 34번 게시글(전문가 분석)의 댓글들
(1, 7, true), (3, 7, true), (5, 7, true), (7, 7, true),
(9, 7, true), (11, 7, true),

(2, 8, true), (6, 8, true), (10, 8, false), (14, 8, true),
(18, 8, true),

-- 5번 게시글(서버 장애)의 댓글들
(1, 9, true), (3, 9, true), (7, 9, true), (9, 9, true),
(11, 9, false), (13, 9, true), (15, 9, true),

(2, 10, true), (6, 10, true), (10, 10, true), (14, 10, true),
(18, 10, true),

-- 16번 게시글(골든크로스)의 댓글들
(3, 11, true), (7, 11, true), (11, 11, true), (15, 11, true),
(19, 11, false), (23, 11, true),

(2, 12, true), (6, 12, true), (10, 12, true), (14, 12, true),
(18, 12, true),

-- 27번 게시글(3년 존버)의 댓글들
(1, 13, true), (5, 13, true), (9, 13, true), (13, 13, true),
(17, 13, false), (21, 13, true), (25, 13, true),

-- 38번, 9번 게시글의 댓글들
(2, 14, true), (6, 14, true), (10, 14, true), (14, 14, true),
(18, 14, true),

(1, 15, true), (5, 15, true), (9, 15, false), (13, 15, true),
(17, 15, true), (21, 15, true),

-- 다른 원댓글들에 대한 좋아요 (소수)
(4, 16, true), (8, 16, true), (12, 16, true),
(2, 17, true), (6, 17, true), (10, 17, false),
(3, 18, true), (7, 18, true),
(5, 19, true), (9, 19, true), (13, 19, true),
(1, 20, true), (11, 20, true),
(4, 21, true), (14, 21, true),
(2, 22, true), (12, 22, true),
(6, 23, true), (16, 23, false),
(3, 24, true), (13, 24, true),
(8, 25, true), (18, 25, true),
(5, 26, true), (15, 26, true),
(9, 27, true), (19, 27, true),
(4, 28, true), (14, 28, false),
(7, 29, true), (17, 29, true),
(2, 30, true), (12, 30, true),
(8, 31, true), (18, 31, true),
(1, 32, true), (11, 32, true),
(3, 33, true), (13, 33, true),
(6, 34, true), (16, 34, false),

-- 대댓글에 대한 좋아요/싫어요 (36번 이후 댓글)
-- 첫 번째 댓글(1번)의 대댓글들 (36번, 37번)
(2, 36, true), (7, 36, true), (12, 36, true), (17, 36, true),
(3, 37, true), (8, 37, true), (13, 37, false), (18, 37, true),

-- 2번 댓글의 대댓글 (38번)
(4, 38, true), (9, 38, true), (14, 38, true),

-- 4번 댓글의 대댓글들 (39번, 40번)
(5, 39, true), (10, 39, true), (15, 39, false),
(6, 40, true), (11, 40, true), (16, 40, true),

-- 5번 댓글의 대댓글들 (41번, 42번)
(7, 41, true), (12, 41, true), (17, 41, true),
(8, 42, true), (13, 42, false), (18, 42, true),

-- 7번 댓글의 대댓글들 (43번, 44번)
(9, 43, true), (14, 43, true), (19, 43, true),
(10, 44, true), (15, 44, false), (20, 44, true),

-- 9번 댓글의 대댓글들 (45번, 46번, 47번)
(1, 45, true), (6, 45, true), (11, 45, true),
(2, 46, true), (7, 46, false), (12, 46, true),
(3, 47, true), (8, 47, true), (13, 47, true),

-- 나머지 대댓글들에 소수의 좋아요
(4, 48, true), (9, 48, false),
(5, 49, true), (10, 49, true),
(6, 50, true), (11, 50, true),
(7, 51, false), (12, 51, true),
(8, 52, true), (13, 52, true),
(9, 53, true), (14, 53, false),
(10, 54, true), (15, 54, true),
(11, 55, true), (16, 55, true),
(12, 56, false), (17, 56, true),
(13, 57, true), (18, 57, true),
(14, 58, true), (19, 58, true),
(15, 59, false), (20, 59, true),
(16, 60, true), (21, 60, true),
(17, 61, true), (22, 61, true),
(18, 62, false), (23, 62, true),
(19, 63, true), (24, 63, true),
(20, 64, true), (25, 64, true),
(21, 65, false), (26, 65, true),
(22, 66, true), (27, 66, true),
(23, 67, true), (28, 67, true),
(24, 68, false), (29, 68, true);
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
VALUES
-- 답변 완료된 문의
(1, '모의투자 초기 자금 문의', '시드머니를 추가로 받을 수 있나요?', 'COMPLETED', 'NORMAL'),
(2, '거래 체결 오류', '매수 주문했는데 계속 대기 상태입니다', 'COMPLETED', 'URGENT'),
(3, '로그인 안됨', '비밀번호 입력해도 로그인이 안돼요', 'COMPLETED', 'URGENT'),
(4, '차트 데이터 오류', '1분봉 차트가 제대로 안보여요', 'COMPLETED', 'TECHNICAL'),
(5, '회원정보 수정', '닉네임을 변경하고 싶습니다', 'COMPLETED', 'NORMAL'),
(1, '자동매매 설정 문의', '자동매매 설정 방법 알려주세요', 'COMPLETED', 'NORMAL'),
(2, '수익률 계산 문의', '총 수익률이 이상하게 나와요', 'COMPLETED', 'TECHNICAL'),
(3, '모바일 알림 설정', '가격 알림을 받고 싶어요', 'COMPLETED', 'NORMAL'),

-- 답변 대기중인 문의
(4, '거래내역 조회 오류', '어제 거래한 내역이 안보입니다', 'PENDING', 'URGENT'),
(5, '차트 분석 도구 문의', '피보나치 되돌림 사용법 알려주세요', 'PENDING', 'NORMAL'),
(1, '모의투자 규칙 문의', '레버리지 설정이 가능한가요?', 'PENDING', 'NORMAL'),
(2, '거래 취소 문의', '잘못 주문한 거래 취소하고 싶어요', 'PENDING', 'URGENT'),
(3, '수수료 문의', '거래 수수료는 얼마인가요?', 'PENDING', 'NORMAL'),
(4, '차트 저장 문의', '제가 그린 차트 저장이 안돼요', 'PENDING', 'TECHNICAL'),
(5, '입출금 한도 문의', '하루 최대 거래 한도가 궁금합니다', 'PENDING', 'NORMAL');

-- answers 테이블
INSERT INTO answers (question_no, answer_cont)
VALUES
    (1, '안녕하세요. 초기 시드머니는 계정당 1천만원으로 제한되어 있습니다. 초기화를 원하시면 고객센터로 문의 부탁드립니다.'),
    (2, '해당 주문에 대해 확인해본 결과, 시스템 일시 오류가 있었습니다. 현재는 정상 처리되어 주문 내역을 확인하실 수 있습니다.'),
    (3, '로그인 오류 확인 결과, 비밀번호 5회 오입력으로 인한 계정 잠금이었습니다. 잠금 해제 처리 완료했으니 다시 로그인 부탁드립니다.'),
    (4, '차트 데이터 오류 관련하여 확인 결과, 캐시 문제로 확인됩니다. 브라우저 캐시 삭제 후 다시 시도해주세요.'),
    (5, '닉네임 변경은 마이페이지 > 회원정보 수정에서 가능합니다. 단, 30일에 한 번만 변경 가능합니다.'),
    (6, '자동매매 설정 방법 가이드를 첨부해드립니다. 매수/매도 조건 설정 후 자동매매 ON 버튼을 클릭하시면 됩니다.'),
    (7, '수익률 계산 오류를 확인해보았습니다. 현재는 정상 반영되어 있으니 새로고침 후 확인 부탁드립니다.'),
    (8, '모바일 알림 설정 방법입니다. 설정 > 알림설정에서 원하시는 가격과 조건을 설정하실 수 있습니다.');

INSERT INTO faq (faq_title, faq_cont) VALUES
                                             ('시드머니는 어떻게 얻나요?', '시드머니는 자산현황 화면에서 ''내자산 추가하기'' 버튼을 클릭하여 획득이 가능합니다.'),
                                             (-- users 테이블 (먼저 생성해야 다른 테이블의 외래키 참조 가능)
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

-- 코인 거래(coin_transactions) 샘플 데이터 (BUY 거래와 SELL 거래를 균형있게 구성)
INSERT INTO coin_transactions (user_no, market, transaction_type, price, transaction_cnt, transaction_state) VALUES
-- 🟢 사용자 1 (BTC, ETH, ADA, SAND 거래)
(1, 'KRW-KRW', 'BUY', 1, 50000000, 'COMPLETED'),
(1, 'KRW-BTC', 'BUY', 78000000, 0.5, 'COMPLETED'),
(1, 'KRW-KRW', 'SELL', 1, 39000000, 'COMPLETED'),
(1, 'KRW-ETH', 'BUY', 4500000, 2, 'COMPLETED'),
(1, 'KRW-KRW', 'SELL', 1, 9000000, 'COMPLETED'),
(1, 'KRW-ADA', 'BUY', 1200, 1000, 'COMPLETED'),
(1, 'KRW-KRW', 'SELL', 1, 1200000, 'COMPLETED'),
(1, 'KRW-SAND', 'BUY', 5000, 500, 'COMPLETED'),
(1, 'KRW-KRW', 'SELL', 1, 2500000, 'COMPLETED'),
(1, 'KRW-BTC', 'SELL', 78200000, 0.2, 'COMPLETED'),
(1, 'KRW-KRW', 'BUY', 1, 15640000, 'COMPLETED'),
(1, 'KRW-ADA', 'SELL', 1250, 400, 'COMPLETED'),
(1, 'KRW-KRW', 'BUY', 1, 500000, 'COMPLETED'),

-- 🟢 사용자 2 (ETH, QTUM, FLOW, ICX 거래)
(2, 'KRW-KRW', 'BUY', 1, 30000000, 'COMPLETED'),
(2, 'KRW-ETH', 'BUY', 4600000, 3, 'COMPLETED'),
(2, 'KRW-KRW', 'SELL', 1, 13800000, 'COMPLETED'),
(2, 'KRW-QTUM', 'BUY', 8200, 500, 'COMPLETED'),
(2, 'KRW-KRW', 'SELL', 1, 4100000, 'COMPLETED'),
(2, 'KRW-FLOW', 'BUY', 12000, 200, 'COMPLETED'),
(2, 'KRW-KRW', 'SELL', 1, 2400000, 'COMPLETED'),
(2, 'KRW-ICX', 'BUY', 450, 1000, 'COMPLETED'),
(2, 'KRW-KRW', 'SELL', 1, 450000, 'COMPLETED'),
(2, 'KRW-ETH', 'SELL', 4650000, 1, 'COMPLETED'),
(2, 'KRW-KRW', 'BUY', 1, 4650000, 'COMPLETED'),
(2, 'KRW-QTUM', 'SELL', 8300, 200, 'COMPLETED'),
(2, 'KRW-KRW', 'BUY', 1, 1660000, 'COMPLETED'),

-- 🟢 사용자 3 (SUI, WAVES, DOGE, BAT 거래)
(3, 'KRW-KRW', 'BUY', 1, 25000000, 'COMPLETED'),
(3, 'KRW-SUI', 'BUY', 1400, 5000, 'COMPLETED'),
(3, 'KRW-KRW', 'SELL', 1, 7000000, 'COMPLETED'),
(3, 'KRW-WAVES', 'BUY', 3500, 1000, 'COMPLETED'),
(3, 'KRW-KRW', 'SELL', 1, 3500000, 'COMPLETED'),
(3, 'KRW-DOGE', 'BUY', 160, 20000, 'COMPLETED'),
(3, 'KRW-KRW', 'SELL', 1, 3200000, 'COMPLETED'),
(3, 'KRW-BAT', 'BUY', 600, 5000, 'COMPLETED'),
(3, 'KRW-KRW', 'SELL', 1, 3000000, 'COMPLETED'),
(3, 'KRW-SUI', 'SELL', 1450, 2000, 'COMPLETED'),
(3, 'KRW-KRW', 'BUY', 1, 2900000, 'COMPLETED'),
(3, 'KRW-WAVES', 'SELL', 3600, 400, 'COMPLETED'),
(3, 'KRW-KRW', 'BUY', 1, 1440000, 'COMPLETED'),

-- 🟢 사용자 4 (JST, STMX, POLYX, THETA 거래)
(4, 'KRW-KRW', 'BUY', 1, 40000000, 'COMPLETED'),
(4, 'KRW-JST', 'BUY', 50, 50000, 'COMPLETED'),
(4, 'KRW-KRW', 'SELL', 1, 2500000, 'COMPLETED'),
(4, 'KRW-STMX', 'BUY', 30, 80000, 'COMPLETED'),
(4, 'KRW-KRW', 'SELL', 1, 2400000, 'COMPLETED'),
(4, 'KRW-POLYX', 'BUY', 450, 7000, 'COMPLETED'),
(4, 'KRW-KRW', 'SELL', 1, 3150000, 'COMPLETED'),
(4, 'KRW-THETA', 'BUY', 12000, 150, 'COMPLETED'),
(4, 'KRW-KRW', 'SELL', 1, 1800000, 'COMPLETED'),
(4, 'KRW-JST', 'SELL', 52, 20000, 'COMPLETED'),
(4, 'KRW-KRW', 'BUY', 1, 1040000, 'COMPLETED'),
(4, 'KRW-STMX', 'SELL', 32, 20000, 'COMPLETED'),
(4, 'KRW-KRW', 'BUY', 1, 640000, 'COMPLETED'),

-- 🟢 사용자 5 (ONDO, HIFI, CVC, ICX 거래)
(5, 'KRW-KRW', 'BUY', 1, 60000000, 'COMPLETED'),
(5, 'KRW-ONDO', 'BUY', 800, 4000, 'COMPLETED'),
(5, 'KRW-KRW', 'SELL', 1, 3200000, 'COMPLETED'),
(5, 'KRW-HIFI', 'BUY', 1500, 3000, 'COMPLETED'),
(5, 'KRW-KRW', 'SELL', 1, 4500000, 'COMPLETED'),
(5, 'KRW-CVC', 'BUY', 900, 5000, 'COMPLETED'),
(5, 'KRW-KRW', 'SELL', 1, 4500000, 'COMPLETED'),
(5, 'KRW-ICX', 'BUY', 440, 10000, 'COMPLETED'),
(5, 'KRW-KRW', 'SELL', 1, 4400000, 'COMPLETED'),
(5, 'KRW-ONDO', 'SELL', 820, 2000, 'COMPLETED'),
(5, 'KRW-KRW', 'BUY', 1, 1640000, 'COMPLETED'),
(5, 'KRW-HIFI', 'SELL', 1550, 1000, 'COMPLETED'),
(5, 'KRW-KRW', 'BUY', 1, 1550000, 'COMPLETED');

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
VALUES
-- 답변 완료된 문의
(1, '모의투자 초기 자금 문의', '시드머니를 추가로 받을 수 있나요?', 'COMPLETED', 'NORMAL'),
(2, '거래 체결 오류', '매수 주문했는데 계속 대기 상태입니다', 'COMPLETED', 'URGENT'),
(3, '로그인 안됨', '비밀번호 입력해도 로그인이 안돼요', 'COMPLETED', 'URGENT'),
(4, '차트 데이터 오류', '1분봉 차트가 제대로 안보여요', 'COMPLETED', 'TECHNICAL'),
(5, '회원정보 수정', '닉네임을 변경하고 싶습니다', 'COMPLETED', 'NORMAL'),
(1, '자동매매 설정 문의', '자동매매 설정 방법 알려주세요', 'COMPLETED', 'NORMAL'),
(2, '수익률 계산 문의', '총 수익률이 이상하게 나와요', 'COMPLETED', 'TECHNICAL'),
(3, '모바일 알림 설정', '가격 알림을 받고 싶어요', 'COMPLETED', 'NORMAL'),

-- 답변 대기중인 문의
(4, '거래내역 조회 오류', '어제 거래한 내역이 안보입니다', 'PENDING', 'URGENT'),
(5, '차트 분석 도구 문의', '피보나치 되돌림 사용법 알려주세요', 'PENDING', 'NORMAL'),
(1, '모의투자 규칙 문의', '레버리지 설정이 가능한가요?', 'PENDING', 'NORMAL'),
(2, '거래 취소 문의', '잘못 주문한 거래 취소하고 싶어요', 'PENDING', 'URGENT'),
(3, '수수료 문의', '거래 수수료는 얼마인가요?', 'PENDING', 'NORMAL'),
(4, '차트 저장 문의', '제가 그린 차트 저장이 안돼요', 'PENDING', 'TECHNICAL'),
(5, '입출금 한도 문의', '하루 최대 거래 한도가 궁금합니다', 'PENDING', 'NORMAL');

-- answers 테이블
INSERT INTO answers (question_no, answer_cont)
VALUES
    (1, '안녕하세요. 초기 시드머니는 계정당 1천만원으로 제한되어 있습니다. 초기화를 원하시면 고객센터로 문의 부탁드립니다.'),
    (2, '해당 주문에 대해 확인해본 결과, 시스템 일시 오류가 있었습니다. 현재는 정상 처리되어 주문 내역을 확인하실 수 있습니다.'),
    (3, '로그인 오류 확인 결과, 비밀번호 5회 오입력으로 인한 계정 잠금이었습니다. 잠금 해제 처리 완료했으니 다시 로그인 부탁드립니다.'),
    (4, '차트 데이터 오류 관련하여 확인 결과, 캐시 문제로 확인됩니다. 브라우저 캐시 삭제 후 다시 시도해주세요.'),
    (5, '닉네임 변경은 마이페이지 > 회원정보 수정에서 가능합니다. 단, 30일에 한 번만 변경 가능합니다.'),
    (6, '자동매매 설정 방법 가이드를 첨부해드립니다. 매수/매도 조건 설정 후 자동매매 ON 버튼을 클릭하시면 됩니다.'),
    (7, '수익률 계산 오류를 확인해보았습니다. 현재는 정상 반영되어 있으니 새로고침 후 확인 부탁드립니다.'),
    (8, '모바일 알림 설정 방법입니다. 설정 > 알림설정에서 원하시는 가격과 조건을 설정하실 수 있습니다.');

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
'비밀번호를 잊어버렸어요.', '로그인 화면에서 ''비밀번호 찾기''를 클릭하시면 가입하신 이메일로 임시 비밀번호를 발송해드립니다.'),
                                             ('회원 탈퇴는 어떻게 하나요?', '설정 > 계정관리 > 회원탈퇴 메뉴에서 진행하실 수 있습니다. 탈퇴 시 모든 데이터는 삭제되며 복구가 불가능합니다 알겠냐?.'),
                                             ('자동 거래는 어떻게 설정하나요?', '거래소 화면에서 ''자동거래 설정''을 선택하신 후, 원하시는 조건을 설정하시면 됩니다. 24시간 자동으로 거래가 진행됩니다.'),
                                             ('입금은 즉시 반영되나요?', '은행 영업일 기준 10분 이내로 입금이 반영됩니다. 단, 은행 점검 시간이나 공휴일에는 지연될 수 있습니다.'),
                                             ('출금 한도는 얼마인가요?', '기본 출금 한도는 1일 3,000만원입니다. 추가 인증을 완료하시면 한도를 상향 조정받으실 수 있습니다.'),
                                             ('거래 수수료는 얼마인가요?', '일반 거래 시 0.05%, VIP 등급의 경우 0.03%의 수수료가 적용됩니다. 자세한 수수료 정책은 고객센터에서 확인 가능합니다.'),
                                             ('모바일 앱은 언제 출시되나요?', '현재 모바일 앱을 개발 중에 있으며, 2024년 상반기 중 안드로이드와 iOS 버전이 동시 출시될 예정입니다.'),
                                             ('해외 사용자도 가입할 수 있나요?', '현재는 국내 사용자만 서비스 이용이 가능합니다. 해외 서비스는 2024년 하반기부터 단계적으로 오픈할 예정입니다.'),
                                             ('거래 내역은 어디서 확인하나요?', '마이페이지 > 거래내역 메뉴에서 기간별, 종류별로 모든 거래 내역을 확인하실 수 있습니다.');
