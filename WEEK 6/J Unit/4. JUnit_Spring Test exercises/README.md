# Spring Testing Exercises

A ready-to-import Spring Boot + Maven project implementing all 9 exercises
from the "Spring Testing Exercises" worksheet.

## Project Structure

```
spring-testing-exercises/
├── pom.xml
└── src
    ├── main/java/com/example/springtesting
    │   ├── SpringTestingExercisesApplication.java
    │   ├── CalculatorService.java         (Exercise 1 & 9)
    │   ├── User.java                      (Exercise 2 - JPA entity)
    │   ├── UserRepository.java            (Exercise 2 & 7)
    │   ├── UserService.java               (Exercise 2, 5 & 6)
    │   ├── UserController.java            (Exercise 3 & 5)
    │   └── GlobalExceptionHandler.java    (Exercise 8)
    ├── main/resources/application.properties
    └── test/java/com/example/springtesting
        ├── CalculatorServiceTest.java     (Exercise 1 & 9)
        ├── UserServiceTest.java           (Exercise 2 & 6)
        ├── UserControllerTest.java        (Exercise 3, 5 & 8)
        ├── UserRepositoryTest.java        (Exercise 7)
        └── UserIntegrationTest.java       (Exercise 4)
```

## How to Import

### IntelliJ IDEA
1. Unzip this project.
2. `File` → `Open` → select the `spring-testing-exercises` folder (the one with `pom.xml`).
3. IntelliJ auto-detects it as Maven/Spring Boot and downloads dependencies.
4. Right-click `src/test/java` → `Run 'All Tests'`.

### Eclipse / Spring Tool Suite
1. Unzip this project.
2. `File` → `Import` → `Maven` → `Existing Maven Projects`.
3. Browse to `spring-testing-exercises` and finish.
4. Right-click the project → `Run As` → `JUnit Test`.

### Command Line (requires Maven installed)
```bash
cd spring-testing-exercises
mvn test          # run all tests
mvn spring-boot:run   # run the app itself on http://localhost:8080
```

**Requires Java 17+** (Spring Boot 3.x's minimum supported version).

## One Deliberate Change from the Worksheet

Exercise 2's snippet used `userRepository.findById(id).orElse(null)`.
In this project `UserService.getUserById()` uses `.orElseThrow(() -> new
NoSuchElementException(...))` instead. This is what makes Exercise 6
("test how a service handles a missing user") and Exercise 8 (the
`@ControllerAdvice` that specifically catches `NoSuchElementException`)
actually meaningful — with the original `orElse(null)`, a missing user
would just silently return `null` instead of triggering any exception
handling to test.

## Exercise-by-Exercise Notes

**Exercise 1 — Basic Unit Test**
`CalculatorServiceTest` is a plain JUnit test with no Spring context at
all — `CalculatorService` has no dependencies to mock.

**Exercise 2 — Mocking a Repository**
`UserServiceTest` uses `@Mock`/`@InjectMocks` (Mockito, via
`@ExtendWith(MockitoExtension.class)`) to fake out `UserRepository`
without loading Spring at all — the fastest way to unit test service logic.

**Exercise 3 — Testing a Controller with MockMvc**
`UserControllerTest` uses `@WebMvcTest(UserController.class)` + `@MockBean`
to load just the web layer and drive it with `MockMvc`.

**Exercise 4 — Full Integration Test**
`UserIntegrationTest` uses `@SpringBootTest(webEnvironment = RANDOM_PORT)`
and `TestRestTemplate` to boot the entire app (controller → service →
real H2 database) and hit it over real HTTP.

**Exercise 5 — POST Endpoint**
Covered in `UserControllerTest.testCreateUser_returnsCreatedUser()`, and
end-to-end in `UserIntegrationTest`.

**Exercise 6 — Service Exception Handling**
`UserServiceTest.testGetUserById_missingUser_throwsException()` asserts a
`NoSuchElementException` is thrown for a missing user.

**Exercise 7 — Custom Repository Query**
`UserRepositoryTest` uses `@DataJpaTest` (an isolated, auto-rolled-back,
in-memory-DB test slice) to test the derived `findByName(String)` query.

**Exercise 8 — Controller Exception Handling**
`UserControllerTest.testGetUser_notFound_returns404WithMessage()` mocks
the service to throw, and verifies the `@ControllerAdvice` converts it
into a 404 with body `"User not found"`.

**Exercise 9 — Parameterized Test**
`CalculatorServiceTest` also includes a `@ParameterizedTest` with
`@CsvSource` running the same assertion against five input/output pairs.

## Notes
- Spring Boot **3.2.5**, Java **17**, JUnit 5 (via `spring-boot-starter-test`).
- Uses an in-memory **H2** database, so no external database setup is
  needed to run the tests or the app locally.
