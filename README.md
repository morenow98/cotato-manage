# 8기 코테이토 백엔드 최종 네트워킹

---

# 코테이토 동아리원 관리하기

- 이번 미션은 2번으로 나뉩니다.
  - 첫 번째 미션은 코테이토 세션이 재시작되는 12월 21일까지입니다.
  - 두 번째 미션은 1월 초중순입니다.
- 이번 네트워킹 미션은 "좋은 코드"를 목표로 합니다. 여러 유용한 디자인 패턴을 적용하고 객체지향적인 팀원들의 코드를 보고 배우는 것이 최종 목표입니다.
- 1팀과 2팀의 미션은 같습니다.
- build.gradle 에 작성된 버전을 기준으로 합니다. Spring Boot 는 3.2.0, Java 는 17 버전을 활용합니다.

---

## 1단계 : Fork & Clone
- 현재 Repository를 각자 Fork한 후 Clone한 뒤 작업환경을 만든다.
- 새로운 branch 를 만들어 해당 branch 에서 작업한다. (주의할 점: main이 아닌 브랜치에서 작업할 것)

---

## 2단계 : 코테이테 동아리원 관리
- 코테이토 부원을 등록하고, 파트별 출력, 개인별 출력, 파트에서 개인별 출력 3가지 기능까지, 총 4개의 API를 개발한다.
- 동아리원은 이름, 들어온 기수, 나이, 파트, 실력 을 가지고 있다.
  - 이름은 한글 2글자 이상, 10글자 이하여야 한다.
  - 나이는 22살 이상 30살 이하여야 한다.
  - 파트는 기획, 디자이너, 프론트엔드, 백엔드 가 있다.
  - 실력은 해당 동아리원의 실력을 나타내는 0 이상의 정수값이다.

### 동아리원 등록 API
다음과 같은 Post 요청으로 부원을 등록한다. (간편함을 위해 데이터베이스에 등록하지 않고, ArrayList 와 같은 컬렉션에 저장한다.)

```json
{
    "name": "장우석",
    "period": "8기",
    "age": "26",
    "part": "백엔드"
}
```

### 동아리원들의 실력 계산
동아리원들의 실력 관리를 위해 실력을 계산한다. <br>
다음과 같은 규칙을 가진다.
- 자신의 나이만큼 실력을 가진다.
- 지나간 기수마다 실력이 2씩 증가한다. 예를 들어, 4기에 들어온 사람은 5기에 실력이 2 증가한다.
- 특정 월마다 각기 다른 파트의 실력이 증가하는 것을 발견했다. 하지만, 아쉽게도 27살 이상 동아리원들은 실력이 증가되지 않는다.
```
1, 5, 9월: 기획 파트 동아리원의 실력 10 증가
2, 6, 10월: 디자이너 파트 동아리원의 실력 10 증가
3, 7, 11월: 프론트엔드 파트 동아리원의 실력 10 증가
4, 8, 12월: 백엔드 파트 동아리원의 실력 10 증가
```

- 밑의 3개의 API 요청에는 현재 기수가 파라미터로 들어간다. 현재 기수와 오늘 날짜를 기반으로 실력을 계산한다.
  - EX) 10기에 들어온 25살 프론트엔드 동아리원의 실력을 계산할 경우
  - 현재 기수로 11기를 입력하고 오늘 날짜가 11월 28일이라면 실력은 37이 된다.

### 파트별 출력 API

- 파트별 동아리원들의 실력 평균이 해당 파트의 실력이다. 평균은 정수값으로 버림한다. (실제 평균이 24.9라면 24로 계산한다.)
- 다음의 기준으로 정렬하여 응답한다.
  - 파트별 실력에 대해 내림차순 정렬하여 응답한다.
  - 실력이 같을 경우 인원수에 대해 오름차순으로 정렬한다. 
  - 인원수가 같을 경우 디자이너, 기획, 백엔드, 프론트엔드 순으로 정렬한다.
- 파트, 실력, 인원수를 출력한다.
- 만약 동아리원이 없는 파트가 있다면, 해당 파트는 응답하지 않는다.
```
[
    {
        "part": "기획",
        "ability": "27",
        "count": "9"
    },
    {
        "part": "백엔드",
        "ability": "24",
        "count": "5"
    },
    {
        "part": "프론트엔드",
        "ability": "24",
        "count": "5"
    },
    {
        "part": "디자이너",
        "ability": "24",
        "count": "8"
    }
]
```


### 개인별 출력 API

- 다음의 기준으로 정렬하여 응답한다.
  - 파트에 상관없이 실력에 대해 내림차순 정렬하여 응답한다. 
  - 실력이 같을 경우 나이에 대해 내림차순 정렬한다. 
  - 나이가 같을 경우 들어온 기수에 대해 오름차순 정렬한다.
  - 기수가 같을 경우 이름의 사전순으로 정렬한다.
- 이름, 들어온 기수, 나이, 파트, 실력을 출력한다. 
```
[
    {
        "name": "박윤하",
        "period": "6기",
        "age": "26",
        "part": "백엔드",
        "ability": "28"
    },
    {
        "name": "장우석",
        "period": "4기",
        "age": "20",
        "part": "디자이너",
        "ability": "28"
    },
    {
        "name": "신유승",
        "period": "10기",
        "age": "36",
        "part": "백엔드",
        "ability": "27"
    }
]
```

### 각 파트의 개인별 출력 API
- 개인별 출력을 하되, 한 파트의 동아리원들에 대해서만 응답한다.
```
[
    {
        "name": "박윤하",
        "period": "6기",
        "age": "26",
        "part": "백엔드",
        "ability": "28"
    },
    {
        "name": "신유승",
        "period": "10기",
        "age": "36",
        "part": "백엔드",
        "ability": "27"
    }
]
```

## ⚠️ 주의할 점
- API 설계는 직접 자유롭게 한다.
- build.gradle은 마음껏 변경할 수 있으나 JDK 버전은 변경하면 안 된다.
- [Java 코드 컨벤션](https://google.github.io/styleguide/javaguide.html)을 따른다.
- 메서드의 길이가 15라인을 넘어가지 않도록 구현한다.
  - 메서드 한 가지 일만 하도록 최대한 작게 만들어라.
- 값을 하드 코딩하지 않는다.
  - 값들은 의미를 드러내기 위해 static final 키워드를 통해 의미를 드러내야 한다. 또한 수정에도 용이하다.
  - 연관성이 있는 상수는 enum을 사용한다.
- 무작정 setter, getter 등의 Lombok 어노테이션을 사용하지 않는다. 
  - setter는 객체의 값을 쉽게 변경하게 해서 가독성을 떨어트린다.
  - 객체 자신의 값을 외부에 드러내기만 하면 되는 때만 getter를 사용하고, 그 이외에는 로직을 직접 포함하는 메서드를 작성한다.

---

## 3단계 : Pull Request
- 구현이 완료되었다면 commit 후 push 한다.
- main이 아닌 작업중인 브랜치에서 main에 merge한다.
- 자신의 main 브랜치에서 새로운 pull request 를 만들어 보낸다.
- 주의할 점: main이 아닌 곳에서 pull request하면 안됨. 1차 미션 종료 후 2차 미션 시에도 main이 아닌 브랜치에서 작업하고 main에서 pull request할 것.

---

# 🥔️2번째 미션에서의 변경점🥔
1. Pull Request 목록에서 다른 팀원들의 코드를 한 명씩 골라 정성껏 코드 리뷰한다.
   - 상대방의 코드 중 기억에 남을 만한 부분, 왜 이렇게 했는지 궁금한 부분, 다른 방식으로 적용하면 좋을 부분, 고쳐야 할 부분 등을 리뷰한다.
   - 단, 공격적으로 리뷰하지 말아야 하고 리뷰어의 생각이 무조건 맞는 것이 아니기 때문에 그것을 어떻게 받아들일지는 자신의 몫이다. _**성장**_ 을 위해 코드 리뷰를 하고, 받아들이자.
   - 2명 이상에게 코드 리뷰를 할 경우, 열심히 한 팀원을 추려 소정의 상품이 있다.
   - 다른 팀원의 코드를 리뷰하면서, 내 코드에 대한 코드 리뷰를 보면서 성장한다.
   - 코드 리뷰를 할 때는 다음과 같은 곳 등에 주안점을 두어 리뷰한다.
     - API 설계(endpoint, method 등)를 왜 그렇게 했는지
     - Java 코드 컨벤션을 따랐는지
     - 값, 문자열 상수들을 Enum 등으로 상수처리 했는지
     - if, for 등의 들여쓰기를 메소드 분리, stream 등을 통해 해소했는지
     - 한 메소드는 한 가지 일만 처리하는지
     - 실력 계산과 같은 로직을 Service가 아닌 Domain에서 구현했는지
       <br><br>
2. (추가 구현) 각종 예외 상황에 대해 Custom Exception 과 @RestControllerAdvice 으로 예외를 각각 다르게 처리한다.
   - 예를 들어, 파트를 찾을 수 없을 때, PartNotFoundException 등의 예외를 직접 만들어 처리한다.
   - 참고 페이지 : https://javachoi.tistory.com/253
     <br><br>
3. (추가 구현) 구현한 각 기능과 예외 상황에 대해 테스트 코드를 구현한다.
   - 예를 들어, 특정 기수에서의 다양한 동아리원에 대해 실력을 계산하거나, 동아리원 등록이 예상한대로 이루어지는지 등에 대한 테스트 코드를 구현한다.
   - JUnit5를 사용하며 Controller 테스트에는 WebMvcTest, 서비스 단위 테스트 등에는 ExtendWith 등의 annotation을 활용한다.
