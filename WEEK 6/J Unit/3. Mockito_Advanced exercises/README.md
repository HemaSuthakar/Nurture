# Advanced Mockito Hands-On Exercises

A ready-to-import Maven project implementing all 5 exercises from the
"Advanced Mockito Hands-On Exercises" worksheet, using the **exact**
class names, method names, and solution code given in the PDF.

## Project Structure

```
mockito-advanced-exercises/
├── pom.xml
└── src
    ├── main/java/com/example
    │   ├── Repository.java       (Exercise 1 & 5)
    │   ├── Service.java          (Exercise 1 & 5)
    │   ├── RestClient.java       (Exercise 2)
    │   ├── ApiService.java       (Exercise 2)
    │   ├── FileReader.java       (Exercise 3 - our own interface, not java.io)
    │   ├── FileWriter.java       (Exercise 3 - our own interface, not java.io)
    │   ├── FileService.java      (Exercise 3)
    │   ├── NetworkClient.java    (Exercise 4)
    │   └── NetworkService.java   (Exercise 4)
    └── test/java/com/example
        ├── ServiceTest.java             (Exercise 1)
        ├── ApiServiceTest.java          (Exercise 2)
        ├── FileServiceTest.java         (Exercise 3)
        ├── NetworkServiceTest.java      (Exercise 4)
        └── MultiReturnServiceTest.java  (Exercise 5)
```

## How to Import

### IntelliJ IDEA
1. Unzip this project.
2. `File` → `Open` → select the `mockito-advanced-exercises` folder (the one with `pom.xml`).
3. IntelliJ auto-detects it as Maven and downloads dependencies.
4. Right-click `src/test/java` → `Run 'All Tests'`.

### Eclipse
1. Unzip this project.
2. `File` → `Import` → `Maven` → `Existing Maven Projects`.
3. Browse to `mockito-advanced-exercises` and finish.
4. Right-click the project → `Run As` → `JUnit Test`.

### Command Line (requires Maven installed)
```bash
cd mockito-advanced-exercises
mvn test
```

## Exercise-by-Exercise Notes

**Exercise 1 — Mocking Databases and Repositories**
`Repository` stands in for a database layer. `Service.processData()`
fetches raw data and prefixes it with `"Processed "`.

**Exercise 2 — Mocking External Services (RESTful APIs)**
`RestClient` stands in for an HTTP client. `ApiService.fetchData()`
prefixes the response with `"Fetched "`.

**Exercise 3 — Mocking File I/O**
`FileReader`/`FileWriter` are small custom interfaces (deliberately named
the same as `java.io.FileReader`/`FileWriter`, per the worksheet — since
we never import the `java.io` versions here, there's no clash).
`FileService.processFile()` prefixes read content with `"Processed "`,
and `saveResult(...)` writes back out through the mocked writer.

**Exercise 4 — Mocking Network Interactions**
`NetworkClient` stands in for a network/socket connection.
`NetworkService.connectToServer()` prefixes the connection info with
`"Connected to "`.

**Exercise 5 — Mocking Multiple Return Values**
Reuses `Repository`/`Service` from Exercise 1. Chains
`.thenReturn("First Mock Data").thenReturn("Second Mock Data")` so two
consecutive calls to `processData()` return different results.

## Notes
- Uses **JUnit Jupiter 5.10.2** and **Mockito 5.11.0**, matching the
  imports (`org.junit.jupiter.api.Test`, `org.mockito.Mockito`) shown in
  the worksheet's solution code.
- Java 11 source/target is set in `pom.xml`; adjust if your JDK differs.
- This is a separate project from the earlier basic Mockito exercises
  project, since the classes/scenarios here (Repository, RestClient,
  FileReader/Writer, NetworkClient) are all new and specific to this
  worksheet.
