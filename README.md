# Gameple Core

## Gameple
Gameple 은 게임 개발을 위한 공통 기능들을 콘솔 및 SDK 형태로 제공하는 게임 퍼블리싱 플랫폼입니다.
게임 개발사들은 별도의 인프라 구축이나 반복적인 기능 구현 없이, Gameple Service를 통해 게임 개발에만 몰입하여 효율적으로 퍼블리싱 기능을 적용할 수 있습니다.

---

## Gameple Core
Gample Core는 Gample 서비스의 Core Module을 제공하는 모듈입니다.

---

## 기술 스택
| 분야 | 사용 기술 |
|------|-----------|
| 백엔드 | Spring Boot (Java) |
| 데이터베이스 | MySQL, Redis, Elasticsearch |
| 메시지 브로커 | Kafka |
| 인증 | OAuth2.0, JWT |

---

## 제공 기능 (API 모듈)
- **Auth Module**: 로그인, 소셜 연동, 토큰 발급
- **User Module**: 유저 정보 관리, 탈퇴, 차단
- **Payment Module**: 결제 승인 및 영수증 검증
- **Inventory Module**: 아이템 지급, 소모, 구매 트랜잭션 처리
- **Community Module**: 게시판, 댓글, 신고 시스템
- **Ranking Module**: 실시간 랭킹, 시즌 랭킹, 보상 분배
- **Matchmaking Module**: PvP/PvE 매칭, MMR 기반 매칭 알고리즘
- **Push Notification Module**: 모바일/웹 푸시 메시지 발송 및 스케줄링
- **Advertisement Module**: 리워드형 광고, 타겟 광고 API 제공
- **Analytics Module**: DAU/MAU, 매출 지표, 이벤트 로그 수집

---

## Module Structure
```
📦 gameple-core
├── common            # 공통 라이브러리 (AOP, Exception Handler, Security Config)
├── user-service      # 유저 정보 및 상태 관리
├── inventory-service # 아이템/재화 관리
├── payment-service   # 결제 연동 및 검증
├── ranking-service   # 실시간/시즌 랭킹 처리
├── matchmaking-service # 매칭 알고리즘 및 큐 관리
├── community-service # 게시판/댓글/신고 시스템
├── push-service      # 푸시 알림 API
└── ad-service        # 광고 조회 및 리워드 처리
```

---

## 문의
플랫폼 연동 및 기술 지원이 필요할 경우 아래로 문의해 주세요. <br/>
📧 dahyun101107@naver.com

