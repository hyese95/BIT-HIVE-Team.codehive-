-- users 테이블 (먼저 생성해야 다른 테이블의 외래키 참조 가능)
INSERT INTO users (user_id, password, nickname, email, phone, privacy_agreements, marketing_agreements, nationality, gender, birth_date, name, self_introduction, role) VALUES
                                                                                                                                                                            ('user1', 'pass123', '비트맨', 'user1@example.com', '010-1111-1111', true, true, 'KR', 'MALE', '1990-01-01', '김철수', '비트코인 투자자입니다', 'USER'),
                                                                                                                                                                            ('user2', 'pass123', '코인女', 'user2@example.com', '010-2222-2222', true, false, 'KR', 'FEMALE', '1992-02-02', '이영희', '알트코인 전문가', 'USER'),
                                                                                                                                                                            ('user3', 'pass123', '투자의신', 'user3@example.com', '010-3333-3333', true, true, 'KR', 'MALE', '1988-03-03', '박민수', '차트분석가', 'USER'),
                                                                                                                                                                            ('user4', 'pass123', '존버킹', 'user4@example.com', '010-4444-4444', true, true, 'KR', 'MALE', '1995-04-04', '정대만', '장기투자자', 'USER'),
                                                                                                                                                                            ('user5', 'pass123', '단타여신', 'user5@example.com', '010-5555-5555', true, false, 'KR', 'FEMALE', '1993-05-05', '최지은', '단타전문', 'USER'),
                                                                                                                                                                            ('admin1', 'admin123', '관리자1', 'admin1@example.com', '010-0000-0001', true, true, 'KR', 'MALE', '1985-12-12', '관리자', '관리자입니다', 'ADMIN'),
                                                                                                                                                                            ('user6', 'pass123', '코인요정', 'user6@example.com', '010-6666-6666', true, true, 'KR', 'FEMALE', '1991-06-06', '송미라', '비트코인 마이너', 'USER'),
                                                                                                                                                                            ('user7', 'pass123', '황소장', 'user7@example.com', '010-7777-7777', true, true, 'KR', 'MALE', '1987-07-07', '황길동', '강세장 전문가', 'USER'),
                                                                                                                                                                            ('user8', 'pass123', '곰돌이', 'user8@example.com', '010-8888-8888', true, false, 'KR', 'MALE', '1994-08-08', '곽진태', '약세장 전문가', 'USER'),
                                                                                                                                                                            ('user9', 'pass123', '알트하는여자', 'user9@example.com', '010-9999-9999', true, true, 'KR', 'FEMALE', '1996-09-09', '한미영', '알트코인 분석가', 'USER');

-- notification_settings 테이블
INSERT INTO notification_settings (volatility_yn, portfolio_yn, target_price_yn, trade_yn, like_yn, comment_yn, reply_yn, follower_yn) VALUES
                                                                                                                                           (true, true, true, true, true, true, true, true),
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
INSERT INTO target_price_alerts (user_no, market, target_price) VALUES
                                                                    (1, 'BTC/KRW', 50000000),
                                                                    (2, 'ETH/KRW', 3000000),
                                                                    (3, 'XRP/KRW', 1000),
                                                                    (4, 'BTC/KRW', 48000000),
                                                                    (5, 'ETH/KRW', 2800000),
                                                                    (1, 'XRP/KRW', 1200),
                                                                    (2, 'BTC/KRW', 52000000),
                                                                    (3, 'ETH/KRW', 3200000),
                                                                    (4, 'XRP/KRW', 800),
                                                                    (5, 'BTC/KRW', 55000000);

-- volatility_alerts 테이블
INSERT INTO volatility_alerts (user_no, market) VALUES
                                                    (1, 'BTC/KRW'),
                                                    (2, 'ETH/KRW'),
                                                    (3, 'XRP/KRW'),
                                                    (4, 'BTC/KRW'),
                                                    (5, 'ETH/KRW'),
                                                    (1, 'DOGE/KRW'),
                                                    (2, 'ADA/KRW'),
                                                    (3, 'SOL/KRW'),
                                                    (4, 'MATIC/KRW'),
                                                    (5, 'DOT/KRW');

-- coin_transactions 테이블
INSERT INTO coin_transactions (user_no, market, transaction_type, price, transaction_cnt, transaction_date, transaction_state) VALUES
                                                                                                                                   (1, 'BTC/KRW', 'BUY', 50000000, 0.1, '2024-03-01 10:00:00', 'COMPLETED'),
                                                                                                                                   (2, 'ETH/KRW', 'SELL', 3000000, 1.0, '2024-03-01 11:00:00', 'COMPLETED'),
                                                                                                                                   (3, 'XRP/KRW', 'BUY', 1000, 1000, '2024-03-01 12:00:00', 'COMPLETED'),
                                                                                                                                   (4, 'BTC/KRW', 'BUY', 48000000, 0.2, '2024-03-01 13:00:00', 'COMPLETED'),
                                                                                                                                   (5, 'ETH/KRW', 'SELL', 2800000, 2.0, '2024-03-01 14:00:00', 'COMPLETED'),
                                                                                                                                   (1, 'XRP/KRW', 'BUY', 1200, 500, '2024-03-01 15:00:00', 'PENDING'),
                                                                                                                                   (2, 'BTC/KRW', 'SELL', 52000000, 0.05, '2024-03-01 16:00:00', 'COMPLETED'),
                                                                                                                                   (3, 'ETH/KRW', 'BUY', 3200000, 0.5, '2024-03-01 17:00:00', 'COMPLETED'),
                                                                                                                                   (4, 'XRP/KRW', 'SELL', 800, 2000, '2024-03-01 18:00:00', 'PENDING'),
                                                                                                                                   (5, 'BTC/KRW', 'BUY', 55000000, 0.1, '2024-03-01 19:00:00', 'COMPLETED');

-- favorite_markets_folders 테이블
INSERT INTO favorite_markets_folders (user_no, list_name) VALUES
                                                              (1, '관심코인'),
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
INSERT INTO favorite_markets (user_no, market, list_no, sort_order) VALUES
                                                                        (1, 'BTC/KRW', 1, 'ASC'),
                                                                        (2, 'ETH/KRW', 2, 'DESC'),
                                                                        (3, 'XRP/KRW', 3, 'ASC'),
                                                                        (4, 'ADA/KRW', 4, 'ASC'),
                                                                        (5, 'DOGE/KRW', 5, 'DESC'),
                                                                        (1, 'SOL/KRW', 6, 'ASC'),
                                                                        (2, 'DOT/KRW', 7, 'DESC'),
                                                                        (3, 'MATIC/KRW', 8, 'ASC'),
                                                                        (4, 'LINK/KRW', 9, 'DESC'),
                                                                        (5, 'UNI/KRW', 10, 'ASC');

-- follows 테이블
INSERT INTO follows (follower_user_no, following_user_no) VALUES
                                                              (1, 2),
                                                              (1, 3),
                                                              (2, 1),
                                                              (3, 1),
                                                              (4, 1),
                                                              (5, 1),
                                                              (2, 3),
                                                              (3, 4),
                                                              (4, 5),
                                                              (5, 2);

-- posts 테이블
INSERT INTO posts (user_no, post_cont, img_url,category) VALUES
                                                    (1, '오늘 비트코인 상승장이네요!', '/images/chart1.jpg','free'),
                                                    (2, '이더리움 2.0 업데이트 후기', '/images/eth2.jpg','free'),
                                                    (3, '차트 분석 공유합니다', '/images/analysis1.jpg','chart'),
                                                    (4, '존버는 승리합니다', NULL,'free'),
                                                    (5, '단타 꿀팁 공유', '/images/tip1.jpg','expert'),
                                                    (1, '시장 분석 리포트', '/images/report1.jpg','expert'),
                                                    (2, '신규 코인 리뷰', '/images/review1.jpg','free'),
                                                    (3, '투자 전략 공유', NULL,'free'),
                                                    (4, '주말 시장 전망', '/images/weekend.jpg','chart'),
                                                    (5, '실전 매매 후기', '/images/trading.jpg','pnl'),
                                                    (1, '손익인증입니다1', '/images/trading.jpg','pnl'),
                                                    (3, '손익인증입니다2', '/images/trading.jpg','pnl'),
                                                    (2, '손익인증입니다3', '/images/trading.jpg','pnl'),
                                                    (1, '손익인증입니다4', '/images/trading.jpg','pnl');

-- comments 테이블 (parent_no는 자기 자신을 참조하도록 설정)
INSERT INTO comments (post_no, user_no, parent_no, comment_cont) VALUES
                                                                     (1, 2, 1, '좋은 정보 감사합니다'),
                                                                     (1, 3, 1, '동의합니다'),
                                                                     (2, 1, 2, '매우 유익하네요'),
                                                                     (2, 4, 2, '궁금한 점이 해결됐어요'),
                                                                     (3, 5, 3, '차트 분석이 정확해요'),
                                                                     (3, 1, 3, '다음 분석도 기대할게요'),
                                                                     (4, 2, 4, '존버화이팅!'),
                                                                     (4, 3, 4, '장기투자가 답이죠'),
                                                                     (5, 4, 5, '좋은 정보 감사합니다'),
                                                                     (5, 1, 5, '많이 배워갑니다');

-- chatting_logs 테이블
INSERT INTO chatting_logs (user_no, content) VALUES
                                                 (1, '안녕하세요'),
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
INSERT INTO post_likes (user_no, post_no, like_type) VALUES
                                                         (1, 2, true),
                                                         (2, 1, true),
                                                         (3, 1, true),
                                                         (4, 2, true),
                                                         (5, 3, true),
                                                         (1, 3, false),
                                                         (2, 4, true),
                                                         (3, 5, true),
                                                         (4, 1, true),
                                                         (5, 2, false);

-- post_bookmarks 테이블
INSERT INTO post_bookmarks (user_no, post_no) VALUES
                                                  (1, 2),
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
INSERT INTO comment_likes (user_no, comment_no, like_type) VALUES
                                                               (1, 2, true),
                                                               (2, 1, true),
                                                               (3, 4, true),
                                                               (4, 3, true),
                                                               (5, 5, true),
                                                               (1, 6, false),
                                                               (2, 7, true),
                                                               (3, 8, true),
                                                               (4, 9, true),
                                                               (5, 10, false);

-- password_change_logs 테이블
INSERT INTO password_change_logs (user_no, changed_password) VALUES
                                                                 (1, 'newpass123'),
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
INSERT INTO login_logs (user_no) VALUES
                                     (1), (2), (3), (4), (5),
                                     (1), (2), (3), (4), (5);

-- exp_activity 테이블
INSERT INTO exp_activity (activity_type, exp_amount, description) VALUES
                                                                      ('LOGIN', 10, '일일 로그인'),
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
INSERT INTO exp_logs (user_no, activity_type) VALUES
                                                  (1, 'LOGIN'),
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
INSERT INTO level_exp (level, required_exp) VALUES
                                                (1, 0),
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
INSERT INTO guides (guide_title, guide_cont) VALUES
                                                 ('비트코인 시작하기', '비트코인 투자 입문자를 위한 가이드'),
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
INSERT INTO questions (user_no, question_title, question_cont, question_status, question_option) VALUES
                                                                                                     (1, '출금 문의', '출금이 지연되고 있습니다', 'PENDING', 'URGENT'),
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
INSERT INTO answers (question_no, answer_cont) VALUES
                                                   (1, '출금 지연 관련 답변입니다...'),
                                                   (2, '로그인 오류 해결 방법입니다...'),
                                                   (3, 'API 오류 해결 방법 안내드립니다...'),
                                                   (4, '계정 복구 절차 안내드립니다...'),
                                                   (5, '입금 확인 후 처리해드렸습니다...'),
                                                   (6, '인증 절차 안내드립니다...'),
                                                   (7, '거래 오류 관련 답변드립니다...'),
                                                   (8, '출금 한도 검토 후 답변드리겠습니다...'),
                                                   (9, '보안 설정 가이드 링크 첨부합니다...'),
                                                   (10, '입금 주소 확인 방법 안내드립니다...');