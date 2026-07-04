# Spring REST Hands-on: Employee & Department Services

Implements the hands-on exercise:
- Static employee/department data configured via Spring XML (`employee.xml`)
- `EmployeeDao` / `DepartmentDao` load the data through constructor injection into
  static `EMPLOYEE_LIST` / `DEPARTMENT_LIST`
- `EmployeeService` / `DepartmentService` are `@Service` beans with `@Transactional`
  methods that delegate to the DAOs
- `EmployeeController` / `DepartmentController` expose:
  - `GET /employees`
  - `GET /departments`

## Requirements
- Java 11+ (JDK)
- Maven 3.6+ (or use an IDE with built-in Maven support)
- Internet access on first build (to download dependencies from Maven Central)

## Import into an IDE
**Eclipse / STS**: File → Import → Maven → Existing Maven Projects → select this folder.
**IntelliJ IDEA**: File → Open → select this folder (pom.xml is auto-detected).
**VS Code**: Open folder (with the Java Extension Pack / Maven for Java extension installed).

## Run

### From an IDE
Run `EmployeeApplication.java` (right-click → Run As → Java Application / Spring Boot App).

### From the command line
```bash
mvn spring-boot:run
```
or build a jar and run it:
```bash
mvn clean package
java -jar target/spring-rest-handson.jar
```

The app starts on **http://localhost:8080**.

## Test with Postman / browser
- `GET http://localhost:8080/employees` → returns the list of employees (with department and skills)
- `GET http://localhost:8080/departments` → returns the list of departments

## Project layout
```
src/main/java/com/training/employee/
  EmployeeApplication.java        # @SpringBootApplication, imports employee.xml
  model/Employee.java
  model/Department.java
  model/Skill.java
  dao/EmployeeDao.java            # static EMPLOYEE_LIST, getAllEmployees()
  dao/DepartmentDao.java          # static DEPARTMENT_LIST, getAllDepartments()
  service/EmployeeService.java    # @Service, @Transactional getAllEmployees()
  service/DepartmentService.java  # @Service, @Transactional getAllDepartments()
  controller/EmployeeController.java    # GET /employees
  controller/DepartmentController.java  # GET /departments
src/main/resources/
  employee.xml               # Spring bean config: departments, skills, employees
  application.properties
```

## Notes
- `employee.xml` currently defines 6 employees across 3 departments (Information
  Technology, Human Resources, Finance) and 6 reusable skills — edit this file to
  add/change data without touching Java code.
- `@CrossOrigin(origins = "*")` is enabled on both controllers so an Angular dev
  server (e.g. `http://localhost:4200`) can call these endpoints directly.
- This project was assembled in a sandboxed environment without access to Maven
  Central, so the build itself could not be executed here — the code was reviewed
  carefully against standard Spring Boot conventions, but please run `mvn clean
  package` on first import to confirm it builds cleanly in your environment.
