# 🛠️ Cafe Kiosk – 관리자(Admin) 시스템


**BEANS COFFEE KIOSK**의 관리자(Admin) 시스템은  
키오스크 주문 데이터를 기반으로 **메뉴, 주문, 매출, 재고를 관리**할 수 있도록 구현한  
Spring Boot + Thymeleaf 기반의 관리자 웹 애플리케이션입니다.

키오스크(User)와 관리자는 **분리된 프로젝트 구조**로 설계되었으며,  
API 연동을 통해 실제 매장 운영 환경을 가정한 관리자 기능을 구현했습니다.



💁🏻‍♂️ **Kiosk(User) Repository 바로가기**

[![Kiosk User Repo](https://img.shields.io/badge/Kiosk%20User%20Repo-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/sjyun0507/kiosk_user_react)
---

## 📌 프로젝트 개요

- 프로젝트명: BEANS COFFEE KIOSK – Admin 시스템  
- 개발 기간: 2025.07.25 ~ 2025.08.02 (총 9일)  
- 개발 목적: 키오스크 주문 시스템과 연동되는 관리자 기능 구현  
- 개발자:
  - **박성원 (Seongwon Park)** – Admin 프론트엔드 & 백엔드 개발  
  - **남동하 (Nam Dong Ha)** – Admin 백엔드 개발

---

## 🧑‍💻 주요 기능

- **메뉴 등록/수정/삭제**  
  - 카테고리별 메뉴 관리  
  - 옵션 및 가격 설정  
  - 신메뉴 자동 분류 (30일 이내 등록 기준)

- **주문 내역 확인**  
  - 일자별 주문 목록 조회  
  - 주문 상세 정보 확인  
  - 결제 상태 및 주문 시간 표시

- **매출 통계 대시보드**  
  - 일/주/월 단위 매출 집계  
  - 인기 메뉴 분석  
  - 주문 수량 기반 그래프 시각화

- **재고 관리**  
  - 메뉴별 재고 설정  
  - 재고 부족 시 주문 제한 기능

---

## 🛠️ 기술 스택

| 구분               | 기술 / 도구               |
|--------------------|----------------------------|
| **Backend**        | Java, Spring Boot          |
| **Frontend**       | Thymeleaf, HTML, CSS, Bootstrap |
| **Database**       | MariaDB                    |
| **ORM / SQL 매핑** | MyBatis                    |
| **Template**       | Thymeleaf                  |
| **Tools**          | Git, GitHub, Swagger, Notion

---

## 📂 프로젝트 구조

```plaintext
kiosk_admin/
├── controller/        # 요청 처리 및 화면 연결 (Spring MVC)
├── service/           # 비즈니스 로직 처리
├── mapper/            # MyBatis SQL 매핑 인터페이스
├── xml/               # SQL 쿼리 정의 XML 파일
├── model/             # DTO 및 VO 클래스
├── templates/         # Thymeleaf 템플릿 (HTML)
├── static/            # CSS, JS, 이미지 등 정적 파일
└── application.yml    # 환경 설정
```

---

## 💭 회고

Admin 시스템은 Spring Boot 기반의 전통적인 MVC 구조로 설계하였고,  
ORM 대신 MyBatis를 사용하여 SQL 쿼리를 직접 정의하고 매핑하는 방식으로 구현했습니다.  
JPA와는 달리 쿼리 흐름을 세밀하게 제어할 수 있어  
복잡한 매출 통계나 조건 기반 메뉴 분류 로직을 명확하게 처리할 수 있었던 점이 장점이었습니다.

프론트엔드는 Thymeleaf와 Bootstrap을 함께 활용하여  
서버 사이드 렌더링 방식으로 구성하면서도  
반응형 UI와 관리자 친화적인 화면 구성을 동시에 만족시킬 수 있도록 설계했습니다.  
Bootstrap 컴포넌트를 활용해 빠르게 UI를 구성하고,  
관리자 입장에서 필요한 정보가 한눈에 들어오도록 배치하는 데 집중했습니다.

특히 신메뉴 자동 분류, 재고 기반 주문 제한, 기간별 매출 통계 등  
운영 중심의 기능들을 직접 설계하고 구현하면서  
실무에 가까운 경험을 할 수 있었고,  
SQL 최적화와 매핑 오류 처리 등에서 시행착오를 겪으며  
MyBatis의 구조와 장단점을 더 깊이 이해하게 되었습니다.

짧은 기간이었지만, 실서비스에 가까운 관리자 시스템을 직접 설계하고 구현해보며  
다음에는 실시간 주문 처리, 알림 기능, 권한 기반 접근 제어 등  
운영 효율성과 보안까지 고려한 관리자 시스템도 도전해보고 싶습니다.
