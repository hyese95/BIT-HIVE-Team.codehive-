# 📌 BIT HIVE (Team.codeHive)
<br>

## 📃 Project Summary
- 비트코인의 실시간 현황 및 차트조회, 모의투자, 커뮤니티를 이용가능한 웹앱  
- 모의투자의를 통한 비트코인시장의 학습 및 수익률 예상, 정보공유
- 가상계좌를 생성하여 실시간 가격을 기준으로 매수와 매도, 실시간 수익률조회 가능

## 😎 Contributors
- ![Backend](https://img.shields.io/badge/Backend-Node.js-43853D?style=for-the-badge&logo=node.js&logoColor=white)[정혜성](https://github.com/hyese95) Team Manager
- ![Backend](https://img.shields.io/badge/Backend-Node.js-43853D?style=for-the-badge&logo=node.js&logoColor=white) [엄진영](https://github.com/young-5719)
- ![Frontend](https://img.shields.io/badge/Frontend-React-61DAFB?style=for-the-badge&logo=react&logoColor=white)[인승엽](https://github.com/realsydrid) Assistant Manager
- ![Frontend](https://img.shields.io/badge/Frontend-React-61DAFB?style=for-the-badge&logo=react&logoColor=white) [황영중](https://github.com/Arkite124)
- ![Frontend](https://img.shields.io/badge/Frontend-React-61DAFB?style=for-the-badge&logo=react&logoColor=white)[이재덕](https://github.com/WATB92)
  <br>

## 🛠️ 기술 스텍

### Frontend  
![React](https://img.shields.io/badge/React-61DAFB?style=for-the-badge&logo=react&logoColor=black)  
![SASS](https://img.shields.io/badge/SASS-CC6699?style=for-the-badge&logo=sass&logoColor=white)  
![Bootstrap](https://img.shields.io/badge/Bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white)  

### Backend  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white)  
![Java](https://img.shields.io/badge/Java-007396?style=for-the-badge&logo=java&logoColor=white)  
![Tomcat](https://img.shields.io/badge/Tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black)  
![WebSocket](https://img.shields.io/badge/WebSocket-0084FF?style=for-the-badge&logo=websocket&logoColor=white)  
![JPA](https://img.shields.io/badge/JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)  
![MyBatis](https://img.shields.io/badge/MyBatis-BF4F24?style=for-the-badge&logo=mybatis&logoColor=white)  
![Thymeleaf](https://img.shields.io/badge/Thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white)  

### Database  
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)  

### Real-time Data  
![Binance API](https://img.shields.io/badge/Binance%20API-FCD535?style=for-the-badge&logo=binance&logoColor=black)  

### Deployment & DevOps  
![AWS](https://img.shields.io/badge/AWS-232F3E?style=for-the-badge&logo=amazonaws&logoColor=white)  
![EC2](https://img.shields.io/badge/EC2-FF9900?style=for-the-badge&logo=amazonaws&logoColor=white)  
![RDS](https://img.shields.io/badge/RDS-527FFF?style=for-the-badge&logo=amazonrds&logoColor=white)  
![S3](https://img.shields.io/badge/S3-569A31?style=for-the-badge&logo=amazons3&logoColor=white)  
![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white)  
![Kubernetes](https://img.shields.io/badge/Kubernetes-326CE5?style=for-the-badge&logo=kubernetes&logoColor=white)  

## 2024 12-19 회의록

- 결정된 사안

1. 별도의 홈화면 없이 초기화면을 거래소페이지로

2. 고정 하단메뉴바 삽입

3. 전체메뉴펼치기 아이콘 삽입

## 2024-12-20 회의록
- 전체메뉴보기 버튼은 하단고정메뉴바에 삽입
### [거래소 페이지]
1. 거래소페이지 헤더삽입(로고 및 사이트명 기재) - 스크롤 다운 시 사라짐
2. 헤더아래 1줄짜리 전광판 삽입 - 스크롤 다운 시 사라짐
3. 전광판 아래 내 자산현황 요약 콘텐츠 삽입(미로그인 시 로그인 유도 문구 및 버튼으로 대체)
4. 내자산현황 요약 콘텐츠는 스크롤다운 시 한줄요약으로 축소되면서 상단고정바
5. 내자산현황 요약 아래에 거래소 서브메뉴 탭 삽입(원화,보유,관심,검색 순) - 스크롤 다운 시 사라짐

### [자산현황 페이지]
- 상단에 서브메뉴바 삽입(나의 자산,거래내역,주문내역,미체결 순)
##### 나의자산
1. 총 보유자산 현황 및 벤다이어그램
2. 보유 코인에대한 정보
##### 거래내역
1. 기간설정, 자산전체 및 특정 코인 선택, 거래전체 및 특정 기능 선택
2. 거래일시, 자산, 거래구분, 거래수량 등 표기
##### 주문내역
1. 거래내역의 1, 2번과 동일
##### 미체결
1. 기간설정 기능
2. 선택 주문취소 기능

## 2024-12-23
0. 모든페이지 공통으로 헤더우측에 내정보 요약보기 버튼 삽입(다크모드 설정도 여기서 설정)
1. 커뮤니티 초기화면은 자유게시판으로 설정
2. 손익인증 게시판 제외 나머지 3게시판(자유게시판,차트분석,전문가게시판)은 형식 동일
3. 손익인증 게시판 상단에는 접고 펼 수있는 수익률 탑랭커 표시
4. 자유게시판형식은 본문이 접힌 상태로 첫줄만 뜨는 방식으로, 더보기를 누르면 현재화면에서 그 글만 확대되며 나머지는 아래로 밀리는 방식. 최신순 인기순(일간,주간,월간)정렬 기능 추가
5. 자유게시판에서 댓글버튼 또는 그 글을 클릭하면 그 글만 존재하고 댓글이 보이는 페이지로 이동
6. 자유게시판에 좋아요 싫어요 버튼 삽입
7. 커뮤니티 상단에 검색버튼 삽입, 검색버튼을 누르면 검색전용 페이지로 이동
8. 검색은 통합검색포함 각 게시판별 검색가능한 드롭다운버튼 추가, 검색페이지 하단에 인기검색어 표시
9. 검색결과페이지도 자유게시판처럼 첫줄만 요약으로 보이는 방식, 최신순 인기순 정렬 기능 추가
10. 모든 커뮤니티 페이지(검색결과포함)는 무한스크롤 방식이되 특정개수만 보여주고 더보기를 눌러서 계속 로드하는 방식을 사용

## 2024-12-24

- 커뮤니티(자유게시판) 부분 구체화 및 

## 2024-12-26

- 커뮤니티섹션 피그마 작업 마무리
- 뉴스 / 고객센터 섹션 피그마 초안작업
- 거레소 섹션 구체화

## 2024-12-27

- 고객센터섹션 마무리
- 뉴스부분 수정 및 구체화 완료
- 거래소 부분 구체화 완료

## 2024-12-30

- 로그인 & 회원가입 구현
 
1. 로그인 눌렀을 시 초기화면
2. 아이디 찾기 & 비밀번호 재설정 화면
3. 아이디 찾기 시 휴대폰 pass 를 통한 아이디 찾기 화면
4. 비밀번호 재설정 화면 아이디 입력 및 새 비밀번호 입력창

- 회원가입
1. 회원가입 정보 입력 화면 및 이메일 인증
2. 회원가입 화면에 약관 동의
3. 회원가입 시 비밀번호 일치 확인 여부 및 닉네임 중복 체크
4. 회원 탈퇴 기능 추가

- 설정
 
1. 초기화면 구현 및 알림설정(거래소 알림 및 커뮤니티 알림)
2. 설정 화면 내 서비스 항목들 추가 (공지사항, 서비스 이용약관, 개인정보 취급방침, 라이센스, 버전정보)

- 프로필
 
1. 내 정보에 프로필 변경화면 추가(기본 제공 프로필 제공)
2. 메인메뉴 카테고리
3. 하단 메인메뉴 눌렀을 때 나오는 카테고리 소분류 화면

- 커뮤니티
1. 게시물 수정 및 삭제 기능

## 2024-12-31
- 내 자산
1. 보유 종목에 대한 공유 기능

## 2025-01-03
1. 거래소, 내 자산, 유저 정보
- 각 페이지 별  프로토타입 연결을 위한 프레임 단위 통합

## 2025-01-06 ~ 2025-01-07
- Figma 에서 프로토타입 화면 간의 연결 작업을 진행하여, 사용자 흐름과 인터랙션을 최적화

## 2025-01-14
- 커뮤니티
1. 커뮤니티 검색 했을시 카테고리별 검색 결과 분류 기능
2. 손익인증 게시판 내 랭킹 유저 수익률 공개 및 개인정보 내에서 수익률 공개/비공개 기능 추가
3. 게시판 내 글쓰기 기능 구현
4. 게시물 스크롤 내렸을 시 최상단으로 올릴 수 있는 버튼
- 내 자산
1. 보유 종목 공유 기능 눌렀을 때 유저 아이디 정보 출력
- 거래소
1. 코인 관심목록 눌렀을 때 사용자가 관심목록 내에서 항목 분류 기능 추가

## 2025-01-17
- 커뮤니티
1. 게시판 글에 북마크 on/off 기능
2. 커뮤니티 통합 검색 출력 화면 
3. 검색에 따른 각 게시판별 검색 결과 화면
4. 손익 인증 게시판 다른 유저 게시물 눌렀을 때 화면
- 내 정보
1. 회원정보에 저장해놓은 북마크 목록 삭제,편집 기능

## 2025-01-23
- 차트
1. 차트 화면 터치 시 알람 저가/고가 화면 출력 및 알림 버튼
2. 급격한 시세 변동 알림 및 지정가 도달 알림 설정

- 설정
1. 알림 설정 편집 기능

- 비로그인 섹션
1. 로그인을 하지 않았을 때의 사용자 화면 구현(거래소,커뮤니티,설정 및 채팅방)

## 2025-02-05
- 내 자산
1. 회원 가입 후 초기 자금 유저 선택 페이지
2. 보유 자산(보유 종목 및 수익률) 초기화 기능

- 유저
1. 로그인 및 게시글, 댓글, 추천에 따른 경험치 지급
2. 특정 레벨 달성 및 수익률 랭킹에 따른 인장 지급

- 데이터베이스
1. 보유 자산 거래내역 및 게시글(댓글, 좋아요, 북마크, 팔로잉) 테이블 작성

## 2025-02-07
- 데이터베이스
1. 데이터베이스 아키텍처를 설계하고 최적화하여 구축

## 2025-02-10
- 데이터베이스
1. 데이터베이스 스키마 구축 (관리자 계정과 유저 계정 구축)

## 2025-02-11
- 피그마 화면단
1. 설정
2. 지정가, 시세변동알림목록 수정
3. 팔로잉, 팔로우 및 FAQ 수정
4. 뉴스
5. 카테고리별 내용 수정
6. 뉴스 리다이렉션 외부 이동에 따른 프롬프트 창
7. 뉴스 테마 항목 수정
8. 김프에 따른 코인별 정렬 기준 추가
9. 거래소
10. 중복 데이터 제거
11. 코인 별 차트에 알람 추가
12. 전체 메뉴
13. 카테고리 입출금 항목 제거

- 데이터베이스
1. 팔로우 날짜 데이터베이스에 추가
2. 문의하기 페이지 카테고리 데이터베이스 추가

## 2025-02-13
- 피그마 화면단
1. 전 섹션 프로토타입 완료