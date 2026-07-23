# orm-learn — Spring Data JPA Hands-On

This zip contains **three runnable projects**, covering every hands-on
exercise in the *Spring Data JPA* training document (Hands on 1–9):

- `orm-learn/` — the main Spring Boot project: `Country` CRUD (Hands on 1,
  5–9, plus the partial-name-search objective) and the Spring Data JPA
  `Employee` side of Hands on 4.
- `handson2-hibernate-xml-config/` — plain Hibernate, XML mapping.
- `handson3-hibernate-annotation-config/` — plain Hibernate, annotation mapping.

## Project layout — `orm-learn/`

```
src/main/java/com/cognizant/ormlearn/
├── OrmLearnApplication.java          Main class, runs all the test methods
├── model/Country.java                @Entity mapped to the country table
├── model/Employee.java               @Entity for the Hands on 4 comparison
├── repository/CountryRepository.java JpaRepository + query method
├── repository/EmployeeRepository.java
└── service/
    ├── CountryService.java           CRUD + partial-name search, @Transactional
    ├── EmployeeService.java          addEmployee(), @Transactional
    └── exception/CountryNotFoundException.java

src/main/resources/
├── application.properties            MySQL config (matches the hands-on doc)
├── application-h2.properties         H2 in-memory profile — zero setup
├── schema.sql                        Creates the country and employee tables
└── data.sql                          Populates country with 249 rows
```

## Option A — Run instantly with H2 (no database install needed)

This is the fastest way to see it work.

**Command line:**
```
mvn spring-boot:run -Dspring-boot.run.profiles=h2
```

**In Eclipse:** Run → Run Configurations → your app → Arguments tab → VM
arguments:
```
-Dspring.profiles.active=h2
```

You'll see Hibernate SQL logs and the results of each hands-on test method
(find all, find by code "IN", add "ZZ", update it, delete it, and search
countries containing "land") in the console.

## Option B — Run against MySQL (as written in the hands-on doc)

1. Install MySQL Server 8.0 and create the schema:
   ```
   mysql -u root -p
   mysql> create schema ormlearn;
   ```
2. `src/main/resources/application.properties` is already pointed at
   `jdbc:mysql://localhost:3306/ormlearn` with user `root` / password `root`
   — edit these if your local MySQL credentials differ.
3. Run normally (no profile needed):
   ```
   mvn spring-boot:run
   ```
   `schema.sql` and `data.sql` run automatically on startup and (re)create
   the `country` table with all 249 countries. If you'd rather run those
   scripts manually in MySQL Workbench instead, set
   `spring.sql.init.mode=never` in `application.properties`.

## Import into Eclipse

1. Extract this zip.
2. Eclipse → File → Import → Maven → Existing Maven Projects → browse to the
   extracted `orm-learn` folder → Finish.
3. Right-click `OrmLearnApplication.java` → Run As → Java Application (add
   the VM argument above if using H2).

## Import into IntelliJ IDEA / other IDEs

Open the folder — it will be auto-detected as a Maven project from
`pom.xml`. Run `OrmLearnApplication`.

## What each hands-on maps to

| Hands on | Feature | Where |
|---|---|---|
| 1 | Project setup, `findAll()` | `CountryService.getAllCountries()` |
| 5 | Table creation & seed data | `schema.sql`, `data.sql` |
| 6 | Find country by code | `CountryService.findCountryByCode()` |
| 7 | Add a country | `CountryService.addCountry()` |
| 8 | Update a country | `CountryService.updateCountry()` |
| 9 | Delete a country | `CountryService.deleteCountry()` |
| Objectives | Partial name search | `CountryRepository.findByNameContainingIgnoreCase()` |
| 4 | Spring Data JPA side of the comparison | `model/Employee.java`, `repository/EmployeeRepository.java`, `service/EmployeeService.java`, run via `testAddEmployee()` |

Hands on 2 and 3 are plain Hibernate (no Spring) by design, so they live in
two **separate, standalone Maven projects** included alongside this one in
the zip — see below. Together with `Employee`/`EmployeeRepository`/
`EmployeeService` above, all three styles from Hands on 2, 3, and 4 are
runnable and directly comparable.

## Requirements

- JDK 8+ (project targets Java 8 for broad IDE compatibility)
- Maven 3.6+ (or your IDE's bundled Maven)
- MySQL Server 8.0 — only needed for Option B and for the standalone
  Hands on 2 / 3 Hibernate demos (they don't have an H2 profile)

## Hands on 2 — `handson2-hibernate-xml-config/`

Plain Hibernate, `Employee` mapped via `Employee.hbm.xml`, wired through
`hibernate.cfg.xml`. `EmployeeXmlDemo.main()` walks through
`SessionFactory` → `Session` → `Transaction` → `save()` / `createQuery().list()`
/ `get()` / `update()` / `delete()`, exactly as called out in the hands-on.

```
cd handson2-hibernate-xml-config
mvn compile exec:java -Dexec.mainClass=com.cognizant.hibernatexml.EmployeeXmlDemo
```
(or import into Eclipse as a Maven project and run `EmployeeXmlDemo` directly)

Uses the same MySQL `ormlearn` schema as the main project; `hibernate.hbm2ddl.auto=update`
creates the `EMPLOYEE` table on first run if it isn't already there.

## Hands on 3 — `handson3-hibernate-annotation-config/`

Identical walkthrough to Hands on 2, but `Employee` is mapped with
`@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column` instead of an
`.hbm.xml` file — compare the two `Employee` classes and `hibernate.cfg.xml`
files side by side to see exactly what annotations replace.

```
cd handson3-hibernate-annotation-config
mvn compile exec:java -Dexec.mainClass=com.cognizant.hibernateannotation.EmployeeAnnotationDemo
```

## Comparing all three (Hands on 4)

| | SessionFactory/Session/Transaction | Mapping | Where |
|---|---|---|---|
| Hands on 2 | Manual, explicit | XML (`Employee.hbm.xml`) | `handson2-hibernate-xml-config` |
| Hands on 3 | Manual, explicit | Annotations on POJO | `handson3-hibernate-annotation-config` |
| Hands on 4 | Hidden behind `@Transactional` + `JpaRepository` | Annotations on POJO | `orm-learn/.../EmployeeService.java` |

Run all three `addEmployee`-style flows and compare the console output and
the amount of boilerplate code each style needs.
