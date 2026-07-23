# JUnit Basic Testing Exercises

A ready-to-import Maven project covering all 4 exercises from the
"JUnit Testing Exercises" worksheet.

## Project Structure

```
junit-exercises/
├── pom.xml
└── src
    ├── main/java/com/example
    │   ├── Calculator.java        (used by Exercise 2)
    │   └── BankAccount.java       (used by Exercise 4)
    └── test/java/com/example
        ├── CalculatorTest.java     (Exercise 2: Writing Basic JUnit Tests)
        ├── AssertionsTest.java     (Exercise 3: Assertions in JUnit)
        └── BankAccountTest.java    (Exercise 4: AAA Pattern, @Before/@After)
```

Exercise 1 (Setting Up JUnit) is already done for you — the JUnit 4.13.2
dependency is declared in `pom.xml`.

## How to Import

### IntelliJ IDEA
1. Unzip this project.
2. `File` → `Open` → select the `junit-exercises` folder (the one containing `pom.xml`).
3. IntelliJ will detect it as a Maven project and auto-import dependencies.
4. Right-click the `src/test/java` folder → `Run 'All Tests'`.

### Eclipse
1. Unzip this project.
2. `File` → `Import` → `Maven` → `Existing Maven Projects`.
3. Browse to the `junit-exercises` folder and finish the import.
4. Right-click the project → `Run As` → `JUnit Test`.

### Command Line (requires Maven installed)
```bash
cd junit-exercises
mvn test
```

## What Each Test Class Covers

- **CalculatorTest** — basic tests for add/subtract/multiply/divide/isEven,
  including a test for the divide-by-zero exception.
- **AssertionsTest** — assertEquals, assertTrue, assertFalse, assertNull,
  assertNotNull, assertSame/assertNotSame, and assertArrayEquals.
- **BankAccountTest** — uses `@Before`/`@After` to set up and tear down a
  fresh `BankAccount` fixture before/after every test, and structures each
  test method using the Arrange-Act-Assert pattern.

## Notes
- Tests use **JUnit 4** (`org.junit.Test`, `org.junit.Before`, `org.junit.After`),
  matching the dependency shown in Exercise 1 of the worksheet.
- Java 11 source/target is set in `pom.xml`; adjust `maven.compiler.source/target`
  if your JDK differs.
