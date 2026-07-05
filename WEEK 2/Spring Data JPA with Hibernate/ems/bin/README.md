# Employee Management System

A runnable Spring Boot project implementing all 10 exercises from
*Spring Data JPA and Hibernate*: entities, repositories, CRUD REST endpoints,
custom/named queries, pagination & sorting, auditing, projections, and
Hibernate-specific tuning.

## Requirements
- Java 17+
- Maven 3.8+ (or just use an IDE with built-in Maven support)
- No Lombok plugin needed — this project uses plain getters/setters/constructors,
  so it compiles the same in any IDE without extra setup.

## How to import
1. Unzip this project.
2. Open it in IntelliJ IDEA / Eclipse / VS Code as a **Maven project**
   (File → Open → select the folder / pom.xml).
3. Let the IDE download dependencies (Spring Boot, Spring Data JPA, H2, Web).

## How to run
- From the IDE: run `EmployeeManagementSystemApplication`.
- From the command line:
  ```bash
  mvn spring-boot:run
  ```
- The app starts on **http://localhost:8080** with an in-memory H2 database,
  pre-seeded with 2 departments and 3 employees.
- H2 console: http://localhost:8080/h2-console
  (JDBC URL: `jdbc:h2:mem:testdb`, user: `sa`, password: `password`)

## Try the API

| Feature | Endpoint |
|---|---|
| List employees | `GET /api/employees` |
| Get employee | `GET /api/employees/{id}` |
| Create employee | `POST /api/employees` |
| Update employee | `PUT /api/employees/{id}` |
| Delete employee | `DELETE /api/employees/{id}` |
| List departments | `GET /api/departments` |
| CRUD departments | `POST/PUT/DELETE /api/departments/...` |
| Search by name | `GET /api/employees/search?name=ali` |
| By department (derived query) | `GET /api/employees/by-department?departmentName=Engineering` |
| By email (`@Query`) | `GET /api/employees/by-email?email=alice@example.com` |
| Pagination + sorting | `GET /api/employees/page?page=0&size=5&sortBy=name&direction=asc` |
| Interface projection | `GET /api/employees/department/{departmentId}/names` |
| Class-based projection | `GET /api/employees/department/{departmentId}/summaries` |

Example create request:
```bash
curl -X POST http://localhost:8080/api/departments \
  -H "Content-Type: application/json" \
  -d '{"name":"Marketing"}'

curl -X POST http://localhost:8080/api/employees \
  -H "Content-Type: application/json" \
  -d '{"name":"Dana Lee","email":"dana@example.com","department":{"id":1}}'
```

## Where each exercise lives

| Exercise | Where |
|---|---|
| 1 – Project setup, H2 config | `pom.xml`, `application.properties` |
| 2 – Entities & relationship | `entity/Employee.java`, `entity/Department.java` |
| 3 – Repositories | `repository/EmployeeRepository.java`, `repository/DepartmentRepository.java` |
| 4 – CRUD operations | `controller/EmployeeController.java`, `controller/DepartmentController.java` |
| 5 – Query methods, `@Query`, `@NamedQuery` | `EmployeeRepository` + `@NamedQueries` on `Employee` |
| 6 – Pagination & sorting | `EmployeeController.getPage()` |
| 7 – Auditing | `entity/Auditable.java`, `config/AuditorConfig.java` |
| 8 – Projections | `dto/EmployeeNameOnly.java` (interface), `dto/EmployeeSummary.java` (class) |
| 9 – Data source config | `application.properties` (externalized, auto-configured by Spring Boot) |
| 10 – Hibernate-specific features | `@DynamicUpdate` on `Employee`, dialect + batch settings in `application.properties` |

## Notes
- `spring.jpa.hibernate.ddl-auto=update` auto-creates the schema on startup — fine for
  this H2 in-memory demo; use migrations (Flyway/Liquibase) for real projects.
- Auditing uses a fixed `"system"` auditor (`AuditorConfig`); wire it to Spring Security's
  current user in a real application.
