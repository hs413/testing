# JUnit 테스트 만들기

## 1장

### 단위 테스트 작성 이유
- 코드가 어떤 일을 하는지 알기 쉬움
- 단위 테스트가 쌓이면 회귀 테스트를 지원

### 체크리스트
- 추가적인 테스트를 작성할 필요가 있는가
- 결함이나 한계를 드러낼 수 있는 테스트를 작성할 수 있는가

### 실습

[ScoreCollection](src/main/java/com/example/section1/ScoreCollection.java) - [Test](src/test/java/com/example/section1/ScoreCollectionTest.java)

## 2장

- 테스트 클래스에는 static 필드 x

### 실습

[Profile](src/main/java/com/example/section1/Profile.java) - [Test](src/test/java/com/example/section1/ProfileTest.java)

## 3장

### 햄크레스트 단언
- assertThat 메서드가 대표적
- 첫 번째 인자는 검증하고자 하는 값 - actual
- 두 번째 인자는 기대 값 - matcher
- 일반 문장처럼 왼쪽에서 오른쪽으로 읽을 수 있어야 함
  - 예) 계좌 잔고가 100과 같아야 한다
- 테스트 실패 시 오류 메시지에서 더 많은 정보를 얻을 수 있음
- not null 을 자주 검사하는 것은 설계 문제 대부분의 경우 불필요함

### 실습

[AssertTest](src/test/java/com/example/section1/scratch/AssertTest.java)
