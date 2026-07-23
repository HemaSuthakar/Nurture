# Mocking Dependencies in Spring Tests using Mockito

A ready-to-import Spring Boot + Maven project implementing all 3 exercises
from the "Mocking Dependencies in Spring Tests using Mockito" worksheet.

## Project Structure

```
mockito-spring-mocking-exercises/
├── pom.xml
└── src
    ├── main/java/com/example/mockingdeps
    │   ├── MockingDepsApplication.java
    │   ├── User.java             (JPA entity, shared by all 3 exercises)
    │   ├── UserRepository.java   (shared by Exercises 2 & 3)
    │   ├── UserService.java      (shared by all 3 exercises)
    │   └── UserController.java   (shared by Exercises 1 & 3)
    ├── main/resources/application.properties
    └── test/java/com/example/mockingdeps
        ├── UserControllerTest.java    (Exercise 1)
        ├── UserServiceTest.java       (Exercise 2)
        └── UserIntegrationTest.java   (Exercise 3)
```

## How to Import

### IntelliJ IDEA
1. Unzip this project.
2. `File` → `Open` → select the `mockito-spring-mocking-exercises` folder (the one with `pom.xml`).
3. IntelliJ auto-detects it as Maven/Spring Boot and downloads dependencies.
4. Right-click `src/test/java` → `Run 'All Tests'`.

### Eclipse / Spring Tool Suite
1. Unzip this project.
2. `File` → `Import` → `Maven` → `Existing Maven Projects`.
3. Browse to `mockito-spring-mocking-exercises` and finish.
4. Right-click the project → `Run As` → `JUnit Test`.

### Command Line (requires Maven installed)
```bash
cd mockito-spring-mocking-exercises
mvn test
```

**Requires Java 17+** (Spring Boot 3.x's minimum supported version).

## Exercise-by-Exercise Notes

**Exercise 1 — Mocking a Service Dependency in a Controller Test**
`UserControllerTest` uses `@WebMvcTest(UserController.class)` to load only
the web layer, plus `@MockBean` to swap in a mocked `UserService` so the
controller is tested completely in isolation.

**Exercise 2 — Mocking a Repository in a Service Test**
`UserServiceTest` uses plain Mockito (`@Mock` + `@InjectMocks` via
`@ExtendWith(MockitoExtension.class)`) — no Spring context is loaded at
all, since `UserService`'s only dependency is `UserRepository`.

**Exercise 3 — Mocking a Service Dependency in an Integration Test**
`UserIntegrationTest` follows the worksheet's hint exactly: `@SpringBootTest`
+ `@AutoConfigureMockMvc` boots the full application context and wires up
real MockMvc, while `@MockBean` still swaps out `UserService` for a mock.
This is a hybrid: the web layer (controller, dispatcher, filters) is real,
but the service/database layer is mocked out — useful when you want to
verify the full request-handling pipeline without needing real data.

## The Three Testing Strategies at a Glance

| Exercise | Context loaded | What's mocked | Speed |
|---|---|---|---|
| 1 | Web layer only (`@WebMvcTest`) | `UserService` | Fast |
| 2 | None (plain Mockito) | `UserRepository` | Fastest |
| 3 | Entire app (`@SpringBootTest`) | `UserService` | Slower, most realistic |

## Notes
- Spring Boot **3.2.5**, Java **17**, JUnit 5 (via `spring-boot-starter-test`).
- `UserService.getUserById()` keeps the worksheet's original
  `.orElse(null)` behavior (unlike the earlier "Spring Testing Exercises"
  project, where it was changed to throw an exception for exercises
  specifically about exception handling — not needed here).
- Uses an in-memory **H2** database so the app can run standalone, though
  none of these 3 tests actually touch it (all of them mock right above
  the repository or service boundary).
