# Spring Data JPA Hands-on

A ready-to-run Maven/Spring Boot project implementing the hands-on exercises from
*3__spring-data-jpa-handson.docx*:

- **Hands-on 2** — Get all permanent employees using HQL, with the fetch-join
  optimization walk-through (`EmployeeRepository.getAllPermanentEmployees`).
- **Hands-on 3** — Fetch quiz attempt details using a multi-table HQL join
  (`AttemptRepository.getAttempt`).
- **Hands-on 4** — Average salary using an HQL aggregate function
  (`EmployeeRepository.getAverageSalary`).
- **Hands-on 5** — Get all employees using a native SQL query
  (`EmployeeRepository.getAllEmployeesNative`).
- **Hands-on 6** — Criteria Query is discussed in the docx as a reference-link
  exercise (no fixed method signature given); a stub `EmployeeRepository`/
  `EmployeeService` structure is already in place so you can add a
  `CriteriaBuilder`-based method there yourself as an exercise.

No external database is required — it uses an in-memory H2 database that is
created and seeded automatically on startup, so you can just import and run.

## Requirements

- Java 17+
- Maven 3.6+ (or use the included wrapper if you add one, or your IDE's bundled Maven)

## Import into an IDE

**IntelliJ IDEA / Eclipse / VS Code:** File → Open / Import → select this folder
(the one containing `pom.xml`) → import as a Maven project. Dependencies download
automatically.

## Run

From the command line, inside this folder:

```bash
mvn spring-boot:run
```

Or build a jar and run it:

```bash
mvn clean package
java -jar target/spring-data-jpa-handson-1.0.0.jar
```

Or just run `OrmLearnApplication.main()` from your IDE.

On startup the app seeds the H2 database (departments, skills, employees, and a
sample quiz attempt) and then runs through all four hands-on queries, logging
the generated SQL and results to the console — that's the point of the
exercise: watch the log output to see how each HQL/native query behaves.

## Where to look

```
src/main/java/com/example/ormlearn/
  entity/       Employee, Department, Skill, AppUser, Question, QuizOption,
                Attempt, AttemptQuestion, AttemptOption
  repository/   EmployeeRepository, AttemptRepository  <- the HQL/native queries live here
  service/      EmployeeService, AttemptService
  OrmLearnApplication.java   <- runs the test methods for each hands-on, on startup

src/main/resources/
  application.properties     <- H2 config, SQL logging
  data.sql                   <- seed data (matches the sample quiz in the docx)
```

## H2 Console

While the app is running you can browse the live database at
`http://localhost:8080/h2-console` using JDBC URL `jdbc:h2:mem:ormlearn`,
user `sa`, empty password.
