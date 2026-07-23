# SLF4J Logging Exercises

A ready-to-import Maven project containing solutions to all 3 exercises.

## Project Structure

```
slf4j-logging-exercises/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/example/logging/
    │   ├── LoggingExample.java              # Exercise 1
    │   ├── ParameterizedLoggingExample.java # Exercise 2
    │   └── AppenderExample.java             # Exercise 3
    └── resources/
        └── logback.xml                       # Appender configuration for Exercise 3
```

## How to Import

### IntelliJ IDEA
1. File → Open → select the `slf4j-logging-exercises` folder (or `pom.xml` inside it)
2. Choose "Open as Project" — IntelliJ will detect it as a Maven project and download dependencies automatically

### Eclipse
1. File → Import → Maven → Existing Maven Projects
2. Browse to the `slf4j-logging-exercises` folder → Finish

### VS Code
1. Open the folder with the "Extension Pack for Java" installed
2. It will auto-detect the Maven project

## How to Run

From the command line (requires Maven and Java 11+):

```bash
cd slf4j-logging-exercises

# Exercise 1
mvn compile exec:java -Dexec.mainClass="com.example.logging.LoggingExample"

# Exercise 2
mvn compile exec:java -Dexec.mainClass="com.example.logging.ParameterizedLoggingExample"

# Exercise 3
mvn compile exec:java -Dexec.mainClass="com.example.logging.AppenderExample"
```

Or simply right-click each class in your IDE and choose "Run".

## Notes

- **Exercise 1** logs a plain `error` and `warn` message.
- **Exercise 2** demonstrates parameterized logging with `{}` placeholders (single param, multiple params, and combining a message with an exception) — more efficient than string concatenation since arguments are only evaluated/formatted if the log level is enabled.
- **Exercise 3** uses the `logback.xml` in `src/main/resources` to route log output to **both** the console and a file (`app.log`, created in your working directory when you run it).
