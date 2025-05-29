# 🍰 디어케이 (Dear.K)
### 추억을 쉽고, 맛있게.  <u>**레터링 케이크 큐레이션 서비스**</u> 🎀

![240345](https://github.com/user-attachments/assets/9c03b11b-b68e-4e60-b728-dd5bfc4e7315)

디어케이는 **서로에게 핏-한 가게(메이커)와 고객(피커)를 연결하고 서로에게 집중할 수 있도록** 
환경을 제공하는 큐레이팅 주문 플랫폼입니다.

## 💘 팀원 소개
![Slide 16_9 - 207](https://github.com/user-attachments/assets/e9192488-ff3a-44c1-9577-e391baf2cc50)


## 🛠️ 사용 스택 & 선정 이유

- `Spring Boot 3.3.4`
- `JDK 17`
- `PostgreSQL` : 공간 데이터 및 관계형 데이터를 유연하게 처리할 수 있어 선택하였습니다.
- `JPA` : 객체와 테이블 간 매핑을 통해 SQL 없이도 데이터 처리가 가능하도록 하였습니다.
- `AWS RDS` : 안정적인 관계형 데이터베이스 운영을 위해 사용하였습니다.
- `AWS S3` : 정적 리소스 및 이미지 파일을 안전하게 저장하고 제공하기 위해 사용하였습니다.
- `Jwt & Cookie` : 유저 인증과 토큰의 안전한 전달에 사용하였습니다.
- `Nginx` : 웹 서버, 리버스 프록시, 로드밸런싱에 활용하였습니다.
- `Docker` : 서버 컨테이너 실행을 위해 사용하였습니다.
- `Docker Compose` : 여러 컨테이너를 동일한 환경에서 실행하도록 관리하였습니다.
- `Github Actions` : CI/CD 파이프라인 자동화를 위해 활용하였습니다.

## 🌐 시스템 아키텍처
![아키텍처](https://github.com/user-attachments/assets/d6fd0075-d54a-4082-b00e-6ddb7af37f72)

## 🌱 ERD
![image](https://github.com/user-attachments/assets/2de8c9d4-0b89-4507-bb9a-6ffc94888187)

## 📢 API 명세서
[디어케이 API 명세서](http://223.130.155.249:8080/swagger-ui/index.html)<br><br>

## ✅ Issue & PR
`Code Review` & `Approve` 설정

## 🔀 Branch
생성한 이슈에 따라서 브랜치 생성 `Ex) feat/#4-login` <br><br>
`main branch` : 개발 최종 완료 시 merge <br>
`develop branch` : 개발 진행 <br>
`feature branch` : 새로운 기능 개발 <br>
`refactor branch` : 리팩토링 진행

## 💬 Commit Message
이슈번호 붙여서 커밋` Ex) [FEAT] #1: 로그인 기능 추가` <br>
Body는 추가 설명 필요하면 사용

| 작업태그 | 내용 |
|----------|------|
| `feat` | 새로운 기능에 대한 커밋 |
| `fix` | 버그 수정에 대한 커밋 |
| `chore` | 그 외 자잘한 수정에 대한 커밋 |
| `cicd` | CICD 관련 설정 수정에 대한 커밋 |
| `docs` | 문서 수정에 대한 커밋 |
| `style` | 코드의 동작에는 영향을 주지 않는 포맷, 세미콜론 등 수정에 대한 커밋 |
| `refactor` | 코드 리팩토링에 대한 커밋 |
| `test` | 테스트 코드 수정에 대한 커밋 |
| `design` | CSS 및 UI 수정에 대한 커밋 |
| `set` | 프로젝트 세팅 관련 커밋 |

## 📛 Naming

- **패키지명** : 한 단어 소문자 사용 `Ex) auth` <br>
- **클래스명** : 파스칼 케이스 사용 `Ex) JwtUtil` <br>
- **메서드명** : 카멜 케이스 사용, 동사로 시작 `Ex) getUser` <br>
- **변수명** : 카멜 케이스 사용 `Ex) jwtProperties` <br>
- **상수명** : 대문자 사용 `Ex) USER_ROLE` <br>
- **컬럼명** : 스네이크 케이스 사용 `Ex) user_id` <br>

## 📦 Package
src
├── main
│   ├── auth
│   ├── alarm
│   ├── design
│   ├── event
│   ├── global
│   ├── order
│   ├── store
│   └── user
│       ├── controller
│       ├── domain
│       ├── dto
│       |    ├── request
│       |    └── response
│       ├── exception
│       ├── repository
│       ├── service
│       └── util
└── resources
    ├── application-dev.yml
    └── application-prod.yml

## 📩 API Response
``` json
{
  "isSuccess": true,
  "code": "REQUEST_OK",
  "message": "요청이 승인되었습니다.",
  "results": {
    "storeId": 1,
    "storeName": "디어레터",
    "designName": "하트 리본 케이크"
   }
}
```
- `isSuccess` : 성공 여부
- `code` : 성공 코드, 커스텀 가능
- `message` : 성공 메세지
- `results` : 데이터가 들어가는 곳
