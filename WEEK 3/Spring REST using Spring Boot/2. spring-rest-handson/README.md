# spring-learn — Spring REST Hands-on

A ready-to-run Spring Boot project implementing the hands-on exercises:

- `GET /hello` — returns `Hello World!!`
- `GET /country` — returns India's country details
- `GET /countries` — returns the full list of countries
- `GET /countries/{code}` (also `/country/{code}`) — returns a country by code, case-insensitive
- Returns a `404` with reason `"Country not found"` when the code doesn't exist
- MockMVC tests in `SpringLearnApplicationTests`

Countries are loaded from `src/main/resources/country.xml` at startup.
The app runs on port **8083** (see `application.properties`).

## Import into an IDE

**Eclipse / STS**
1. `File > Import... > Maven > Existing Maven Projects`
2. Browse to this folder and select the `pom.xml`
3. Finish — Eclipse will download dependencies and build the project

**IntelliJ IDEA**
1. `File > Open...` and select this folder (or the `pom.xml`)
2. IntelliJ auto-detects the Maven project and imports it

## Run

Command line (requires Maven + JDK 11+):

```bash
mvn spring-boot:run
```

Or build a jar and run it:

```bash
mvn clean package
java -jar target/spring-learn-0.0.1-SNAPSHOT.jar
```

Then try:
- http://localhost:8083/hello
- http://localhost:8083/country
- http://localhost:8083/countries
- http://localhost:8083/countries/in
- http://localhost:8083/countries/az (404 example)

## Run tests

```bash
mvn clean test
```

Or right-click `SpringLearnApplicationTests.java` in your IDE and choose **Run As > JUnit Test**.

## Project structure

```
src/main/java/com/cognizant/springlearn/
├── SpringLearnApplication.java          # main class
├── controller/
│   ├── HelloController.java             # GET /hello
│   └── CountryController.java           # GET /country, /countries, /countries/{code}
├── service/
│   ├── CountryService.java              # loads country.xml, lookup logic
│   └── exception/CountryNotFoundException.java
└── model/Country.java

src/main/resources/
├── application.properties               # server.port=8083
└── country.xml                          # country data (IN, US, JP, DE)

src/test/java/com/cognizant/springlearn/
└── SpringLearnApplicationTests.java      # MockMVC tests
```
