# Spring REST Hands-on: Country & Employee CRUD

A ready-to-run Spring Boot project implementing the hands-on exercise:
RESTful `Country` and `Employee` services with POST/PUT/DELETE, input
validation (`javax.validation` / Hibernate Validator), and a global
exception handler.

## Requirements

- JDK 8 or later
- Maven 3.6+ (or use an IDE with bundled Maven)
- Internet access on first build (to download dependencies from Maven Central)

## Import into an IDE

**IntelliJ IDEA / Eclipse / STS:** File → Open/Import → select this folder →
choose "Import as Maven project". The IDE will download dependencies
automatically.

## Run from the command line

```bash
mvn spring-boot:run
```

The app starts on **http://localhost:8090**.

## Run the packaged jar

```bash
mvn clean package
java -jar target/spring-rest-handson.jar
```

## Endpoints

### Country (`/countries`) — seeded with US, IN, UK

| Method | URL                  | Description                          |
|--------|----------------------|---------------------------------------|
| GET    | /countries           | Get all countries                    |
| GET    | /countries/{code}    | Get a specific country               |
| POST   | /countries           | Create a country (validated)         |
| PUT    | /countries           | Update a country (validated)         |
| DELETE | /countries/{code}    | Delete a specific country            |

`code` must be exactly 2 characters and not null; `name` must not be blank.

### Employee (`/employees`) — seeded with 2 employees

| Method | URL                | Description                          |
|--------|--------------------|---------------------------------------|
| GET    | /employees         | Get all employees                    |
| GET    | /employees/{id}    | Get a specific employee              |
| POST   | /employees         | Create an employee (validated)       |
| PUT    | /employees         | Update an employee (validated)       |
| DELETE | /employees/{id}    | Delete a specific employee           |

Employee validation: `id` not null, `name` 1-30 chars not blank, `salary`
not null and >= 0, `permanent` not null, `dateOfBirth` formatted `dd/MM/yyyy`.
Nested `department` and `skills` (each with `id` + `name`, 1-30 chars) are
validated too.

## Sample curl commands

```bash
# Get all countries
curl -i http://localhost:8090/countries

# Add a country
curl -i -H 'Content-Type: application/json' -X POST -s \
  -d '{"code":"IN","name":"India"}' \
  http://localhost:8090/countries

# Trigger a validation error (code too short)
curl -i -H 'Content-Type: application/json' -X POST -s \
  -d '{"code":"I","name":"India"}' \
  http://localhost:8090/countries

# Add an employee
curl -i -H 'Content-Type: application/json' -X POST -s -d '{
  "id": 3,
  "name": "Alex Kim",
  "salary": 45000,
  "permanent": true,
  "dateOfBirth": "12/03/1995",
  "department": {"id": 1, "name": "Engineering"},
  "skills": [{"id": 1, "name": "Java"}]
}' http://localhost:8090/employees

# Update an employee that does not exist -> 404
curl -i -H 'Content-Type: application/json' -X PUT -s -d '{
  "id": 999, "name": "Ghost", "salary": 1000, "permanent": true
}' http://localhost:8090/employees

# Delete an employee
curl -i -X DELETE http://localhost:8090/employees/1
```

## Global exception handling

`GlobalExceptionHandler` (`@ControllerAdvice`) centralizes:

- `MethodArgumentNotValidException` — `@Valid` failures → `400` with a
  `errors` array of messages.
- `HttpMessageNotReadableException` — malformed JSON / wrong field type
  (e.g. a string in a numeric field) → `400` with a descriptive message.
- `EmployeeNotFoundException` — missing employee on GET/PUT/DELETE → `404`.

## Tests

`EmployeeControllerTest` uses `MockMvc` to cover:
- successful GET/POST
- validation failures (400)
- not-found scenarios (404)

Run with:

```bash
mvn test
```

## Project structure

```
src/main/java/com/cognizant/springlearn/
├── SpringRestHandsonApplication.java
├── model/          Country, Employee, Department, Skill
├── controller/     CountryController, EmployeeController
├── service/        EmployeeService
├── dao/            EmployeeDao (in-memory store)
└── exception/      EmployeeNotFoundException, GlobalExceptionHandler
src/test/java/com/cognizant/springlearn/
└── EmployeeControllerTest.java
```

> Note: employee/country data is stored in-memory (a `List`) and resets on
> every restart — there's no database in this exercise, matching the
> hands-on's scope.
