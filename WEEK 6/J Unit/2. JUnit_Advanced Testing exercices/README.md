# JUnit 5 Advanced Testing Exercises

A ready-to-import Maven project covering all 5 exercises from the
"Advanced JUnit Testing Exercises" worksheet, using **JUnit 5 (Jupiter)**.

## Project Structure

```
junit5-advanced-exercises/
├── pom.xml
└── src
    ├── main/java/com/example
    │   ├── EvenChecker.java          (Exercise 1)
    │   ├── ExceptionThrower.java     (Exercise 4)
    │   └── PerformanceTester.java    (Exercise 5)
    └── test/java/com/example
        ├── EvenCheckerTest.java      (Exercise 1: Parameterized Tests)
        ├── AllTests.java             (Exercise 2: Test Suites)
        ├── OrderedTests.java         (Exercise 3: Test Execution Order)
        ├── ExceptionThrowerTest.java (Exercise 4: Exception Testing)
        └── PerformanceTesterTest.java(Exercise 5: Timeout Testing)
```

## How to Import

### IntelliJ IDEA
1. Unzip this project.
2. `File` → `Open` → select the `junit5-advanced-exercises` folder (the one with `pom.xml`).
3. IntelliJ auto-detects it as Maven and downloads dependencies.
4. Right-click `src/test/java` → `Run 'All Tests'`.

### Eclipse
1. Unzip this project.
2. `File` → `Import` → `Maven` → `Existing Maven Projects`.
3. Browse to `junit5-advanced-exercises` and finish.
4. Right-click the project → `Run As` → `JUnit Test`.

### Command Line (requires Maven installed)
```bash
cd junit5-advanced-exercises
mvn test
```

## Exercise-by-Exercise Notes

**Exercise 1 — Parameterized Tests**
`EvenCheckerTest` uses `@ParameterizedTest` with `@ValueSource(ints = {...})`
to run the same assertion against many even/odd numbers, plus a bonus
`@CsvSource` example pairing an input with its expected result.

**Exercise 2 — Test Suites**
`AllTests` uses `@Suite` and `@SelectClasses({...})` (from the
`junit-platform-suite` module) to group the other four test classes so
they can be run together as one suite.

**Exercise 3 — Test Execution Order**
`OrderedTests` uses `@TestMethodOrder(MethodOrderer.OrderAnnotation.class)`
at the class level and `@Order(1)`, `@Order(2)`, `@Order(3)` on each method
to guarantee a deterministic run order (JUnit's default order is otherwise
unspecified).

**Exercise 4 — Exception Testing**
`ExceptionThrowerTest` uses `assertThrows(ExceptionType.class, () -> ...)`
to verify both that the correct exception type is thrown and that its
message is correct.

**Exercise 5 — Timeout / Performance Testing**
`PerformanceTesterTest` shows both JUnit 5 timeout mechanisms: the
declarative `@Timeout(1)` annotation and the programmatic
`assertTimeout(Duration, executable)` assertion.

## Notes
- Uses **JUnit Jupiter 5.10.2** and **JUnit Platform 1.10.2**, matching the
  `@ParameterizedTest`, `@Suite`, and `@TestMethodOrder` annotations shown
  in the worksheet (these are JUnit 5 APIs, not JUnit 4).
- Java 11 source/target is set in `pom.xml`; adjust if your JDK differs.
- This is a separate project from the "JUnit Basic Testing Exercises" one,
  since that worksheet used JUnit 4 and this one uses JUnit 5 — mixing both
  frameworks in a single project is possible but adds unnecessary complexity
  for learning purposes.
