# Spring Data JPA Hands-On (orm-learn)

A runnable Spring Boot project implementing every exercise from the
**Spring Data JPA hands-on** document:

- **Hands on 1** – Query Methods on `Country` (contains / sorted / starts-with)
- **Hands on 2** – Query Methods on `Stock` (date range, greater-than, top-N)
- **Hands on 3** – Payroll schema: `Employee`, `Department`, `Skill`
- **Hands on 4** – `@ManyToOne` / `@JoinColumn` (Employee → Department)
- **Hands on 5** – `@OneToMany` (Department → Employees), LAZY vs EAGER
- **Hands on 6** – `@ManyToMany` / `@JoinTable` (Employee ↔ Skill)

## Requirements

- Java 11+
- Maven 3.6+ (or use an IDE with built-in Maven support)

No external database server is required — the project runs against an
**in-memory H2 database** by default, seeded automatically from
`src/main/resources/schema.sql` and `data.sql`.

## Import & Run

**IDE (Eclipse / IntelliJ / STS):**
1. `File → Import → Existing Maven Project` (Eclipse/STS) or `Open` the folder (IntelliJ).
2. Let it download dependencies.
3. Run `OrmLearnApplication` as a Java Application.

**Command line:**
```bash
mvn spring-boot:run
```
or build a jar and run it:
```bash
mvn clean package
java -jar target/orm-learn-1.0.0.jar
```

On startup, `OrmLearnApplication` runs every hands-on scenario in sequence
and logs the results (query results, generated SQL, entity states). Look at
the console output — `logging.level.org.hibernate.SQL=DEBUG` is enabled so
you can see the actual SQL Hibernate generates for each query method and
each relationship fetch, exactly as the hands-on document asks you to
observe.

To focus on a single hands-on exercise, comment out the other method calls
inside `OrmLearnApplication.run(...)`, the same way the original document
describes.

## Project layout

```
src/main/java/com/cognizant/ormlearn/
  OrmLearnApplication.java        # main class + all "testXxx()" methods
  model/                          # Country, Stock, Department, Employee, Skill
  repository/                     # Spring Data JPA repositories (Query Methods live here)
  service/                        # EmployeeService, DepartmentService, SkillService
src/main/resources/
  application.properties          # datasource + Hibernate logging config
  schema.sql                       # table definitions
  data.sql                         # seed data for all 6 hands-on exercises
```

## Switching to MySQL

The original document uses a MySQL database named `ormlearn`. If you'd
rather run against real MySQL instead of H2:

1. In `pom.xml`, uncomment the `mysql-connector-java` dependency.
2. In `application.properties`, comment out the H2 block and uncomment the
   MySQL block, adjusting username/password as needed.
3. `schema.sql` / `data.sql` will run against MySQL the same way they do
   against H2 (both use standard `AUTO_INCREMENT` syntax).

## Notes on the seed data

- **Country** table is seeded with the full list of ISO 3166-1 country
  codes/names, so the "search box" and "alphabet index" query methods
  (Hands on 1) return real, meaningful results.
- **Stock** table includes a representative sample covering every scenario
  asked for in Hands on 2 (FB in Sept 2019, GOOGL close > 1250, top-3 volume
  days, NFLX's lowest closes). The original exercise's full one-year CSV of
  FB/GOOGL/NFLX data isn't reproduced here since it isn't attached to the
  brief — swap in the real `stock-data.csv` via `data.sql` if you have it.
- **Department/Employee/Skill** data is seeded so every relationship demo
  works out of the box: department 1 has 3 employees (for the OneToMany
  demo), and employee 6 / skill 5 intentionally have no relationship yet
  (for the `testAddSkillToEmployee()` demo).
