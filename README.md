# 📌 BIT HIVE (Team.codeHive)
<br>

## 📃 Project Summary
- 비트코인의 실시간 현황 및 차트조회, 모의투자, 커뮤니티를 이용가능한 웹앱  
- 모의투자의를 통한 비트코인시장의 학습 및 수익률 예상, 정보공유
- 가상계좌를 생성하여 실시간 가격을 기준으로 매수와 매도, 실시간 수익률조회 가능

## 😎 Contributors
- [정혜성](https://github.com/hyese95)
- [인승엽](https://github.com/realsydrid)
- [김현기](https://github.com/Kimhyeongi-eng)
- [엄진영](https://github.com/young-5719)
- [황영중](https://github.com/Arkite124)
- [이재덕](https://github.com/WATB92)
<br>

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
   자유게시판에 좋아요 싫어요 버튼 삽입
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