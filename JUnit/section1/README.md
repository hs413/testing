# JUnit 테스트 만들기

### 단위 테스트 작성 이유
- 코드가 어떤 일을 하는지 알기 쉬움
- 단위 테스트가 쌓이면 회귀 테스트를 지원

### 체크리스트
- 추가적인 테스트를 작성할 필요가 있는가
- 결함이나 한계를 드러낼 수 있는 테스트를 작성할 수 있는가

[ScoreCollection](src/main/java/com/example/section1/ScoreCollection.java) / [Test](src/test/java/com/example/section1/ScoreCollectionTest.java)

- 테스트 클래스에는 static 필드 x

[Profile](src/main/java/com/example/section1/Profile.java) / [Test](src/test/java/com/example/section1/ProfileTest.java)


### 햄크레스트 단언
- assertThat 메서드가 대표적
- 첫 번째 인자는 검증하고자 하는 값 - actual
- 두 번째 인자는 기대 값 - matcher
- 일반 문장처럼 왼쪽에서 오른쪽으로 읽을 수 있어야 함
  - 예) 계좌 잔고가 100과 같아야 한다
- 테스트 실패 시 오류 메시지에서 더 많은 정보를 얻을 수 있음
- not null 을 자주 검사하는 것은 설계 문제 대부분의 경우 불필요함

[AssertTest](src/test/java/com/example/section1/scratch/AssertTest.java)

### 테스트 구성
- 테스트 코드는 AAA의 3단계로 구성, 각 단계를 구분함으로서 테스트 코드의 가독성을 높인다. 
  - 준비(Arrange): 테스트 실행 전 객체의 생성이나 API 호출 등의 작업을 수행
  - 실행(Act): 테스트 하기 위한 핵심 로직을 실행
  - 단언(Assert): 실행 코드가 기대한 대로 동작 하는지 확인
- 테스트는 클래스 동작에 집중, 개별 메서드 테스트 x
- 테스트는 프로덕션 코드와 분리
  - 단위 테스트는 단방향성
  - 테스트 코드는 프로덕션 시스템을 의존
  - 반대는 해당하지 않음
- public 행위만 테스트 하는 것이 좋음, private 행위는 테스트 하지 않음
  - private 행위를 테스트할 경우 내부 결합도가 강해짐
- 테스트는 세부적으로 분리하는 것이 좋음
  - 단언이 실패했을 때 파악하기 쉬움
  - 다른 테스트의 영항을 제거
  - 모든 케이스가 실행됨을 보장
- 테스트 케이스는 문서 역할을 수행
  - 일관성 있는 이름 사용
  - 테스트 하고자 하는 행위를 명확히 작성
- 다른 사람이 쉽게 이해할 수 있도록 테스트 작성
  - 지역변수 이름
  - 의미있는 상수
  - 햄크레스트 단언
  - 테스트를 작게 나누기
  - @Before 사용 등

### Before
- Before 메서드는 각 테스트마다 매번 실행
- 여러개의 Before 메서드로 분할할 수 있음
  - 실행 순서는 보장하지 않음
  - 순서 보장이 필요한 경우 하나의 Before를 사용

### 테스트를 의미 있게 유지
- 실패하는 테스트가 있다면 바로 수정
- 항상 모든 테스트가 통과하도록
- 테스트는 빠르게 실행 되어야 함

### FIRST
- Fast
  - 데이터 베이스, 네트워크 등에 의존하지 않는 테스트 작성
- Isolated
  - 작은 양의 코드를 작성
  - 상호 작용이 많을수록 문제 발생 가능성이 높음
  - 다른 테스트에 의존하지 않아야 함
  - 순서나 시간에 상관 없이 실행되어야 함
- Repeatable
  - 테스트는 반복 가능해야 함
- Self-validating
- Timely

[StatCompiler](src/main/java/com/example/section1/domain/StatCompiler.java) / [Test](src/test/java/com/example/section1/domain/StatCompilerTest.java)

- 테스트는 결과를 예측할 수 있어야 함

- Boundary Conditions
  - 경계 조건
    - 모호하고 일관성 없는 입력 값
      - 예) 특수문자가 포함된 문자
    - 잘못된 양식 데이터
    - 수치적 오버플로가 발생하는 계산
    - 빈 값
      - 예) 0, "", null
    - 기댓값을 많이 벗어나는 값
      - 예) 150세의 나이
    - 중복이 허용되지 않는 목록의 중복 값
    - 정렬되지 않은 리스트
    - 시간 순이 맞지 않는 값
  - CORRECT
    - Conformance
    - Ordering
    - Range
    - Reference
    - Existence
    - Cardinality
    - Time
- Inverse relationship
- Cross-check
- Error Conditions
- Performance characteristics

### Mock
- Stub
  - 테스트 용도로 하드 코딩한 값을 반환하는 구현체
- Mock은 실제와 다르게 동작할 수 있으므로 이를 커버할 수 있는 상위 테스트 (통합 테스트)를 확인하는 것을 권장

[AddressRetriever](src/main/java/com/example/section1/AddressRetriever.java) / [AddressRetrieverTest](src/test/java/com/example/section1/AddressRetrieverTest.java)

### Refactoring

[Process](src/test/java/com/example/section1/util/SearchTestRefactoringProcess.java) / [Result](src/test/java/com/example/section1/util/SearchTest.java)

### TDD
- 실패하는 테스트 코드 작성
- 테스트 통과시키기
- 코드 개선


