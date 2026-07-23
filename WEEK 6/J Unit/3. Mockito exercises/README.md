# Mockito Hands-On Exercises

A ready-to-import Maven project covering all 7 exercises from the
"Mockito Hands-On Exercises" worksheet, using **JUnit 5 + Mockito**.

## Project Structure

```
mockito-exercises/
├── pom.xml
└── src
    ├── main/java/com/example
    │   ├── ExternalApi.java   (the dependency that gets mocked)
    │   └── MyService.java     (the class under test)
    └── test/java/com/example
        ├── Exercise1_MockingAndStubbingTest.java
        ├── Exercise2_VerifyingInteractionsTest.java
        ├── Exercise3_ArgumentMatchingTest.java
        ├── Exercise4_HandlingVoidMethodsTest.java
        ├── Exercise5_MultipleReturnsTest.java
        ├── Exercise6_VerifyingInteractionOrderTest.java
        └── Exercise7_VoidMethodExceptionTest.java
```

## How to Import

### IntelliJ IDEA
1. Unzip this project.
2. `File` → `Open` → select the `mockito-exercises` folder (the one with `pom.xml`).
3. IntelliJ auto-detects it as Maven and downloads dependencies.
4. Right-click `src/test/java` → `Run 'All Tests'`.

### Eclipse
1. Unzip this project.
2. `File` → `Import` → `Maven` → `Existing Maven Projects`.
3. Browse to `mockito-exercises` and finish.
4. Right-click the project → `Run As` → `JUnit Test`.

### Command Line (requires Maven installed)
```bash
cd mockito-exercises
mvn test
```

## The Setup Used Throughout

`ExternalApi` is a plain interface representing a dependency you don't
want to hit for real in a unit test (a network call, database, etc.).
`MyService` depends on it via constructor injection, which makes it easy
to swap in a Mockito mock during tests.

## Exercise-by-Exercise Notes

**Exercise 1 — Mocking and Stubbing**
`mock(ExternalApi.class)` creates a fake implementation; `when(...).thenReturn(...)`
tells it what to return when `getData()` is called.

**Exercise 2 — Verifying Interactions**
`verify(mockApi).getData()` confirms the method was actually called
(you can also assert *how many times* with `verify(mock, times(n))`).

**Exercise 3 — Argument Matching**
Shows `eq(5)` for an exact-value match and `anyInt()` for "matches any
int". Note Mockito's rule: if any argument in a call uses a matcher,
*all* arguments in that call must use matchers.

**Exercise 4 — Handling Void Methods**
Void methods can't use `when(...).thenReturn(...)`. Instead you stub them
with `doNothing().when(mock).voidMethod(...)` (or `doThrow`, `doAnswer`)
and confirm the call happened with `verify(...)`.

**Exercise 5 — Multiple Returns**
`when(...).thenReturn(a, b, c)` (or chained `.thenReturn(a).thenReturn(b)`)
returns a different value on each consecutive call, then keeps returning
the last one.

**Exercise 6 — Verifying Interaction Order**
`InOrder inOrder = inOrder(mock)` followed by `inOrder.verify(mock).methodA()`,
`inOrder.verify(mock).methodB()`, etc. fails the test if the calls
happened out of order.

**Exercise 7 — Void Methods That Throw**
`doThrow(new RuntimeException(...)).when(mock).voidMethod(...)` makes the
mocked void method throw when called - useful for testing your error
handling / recovery logic.

## Notes
- Uses **JUnit Jupiter 5.10.2** and **Mockito 5.11.0**, matching the
  `org.junit.jupiter.api.Test` and `org.mockito.Mockito` imports shown in
  the worksheet.
- Java 11 source/target is set in `pom.xml`; adjust if your JDK differs.
- This project is separate from the earlier JUnit 4 and JUnit 5 exercise
  projects, since it introduces a new dependency (Mockito) on top of
  JUnit 5.
