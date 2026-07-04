# spring-learn

A ready-to-run Maven/Spring Boot project implementing all 6 hands-on exercises
from the "Spring REST Hands-on" document.

## Requirements
- JDK 17+
- Maven 3.6+ (or use your IDE's built-in Maven)

## Import into Eclipse
1. Unzip this project.
2. In Eclipse: `File > Import > Maven > Existing Maven Projects`
3. Browse to the extracted `spring-learn` folder and click `Finish`.

## Build from command line
```
mvn clean package
```
(Drop any `-Dhttp.proxyHost=...` flags unless you're actually behind a proxy.)

## Run
- In Eclipse: right-click `SpringLearnApplication.java` > `Run As > Java Application`
- Or from command line: `mvn spring-boot:run`

## What's included
- **Hands on 1** – Spring Boot app scaffolded with `@SpringBootApplication` and
  `SpringApplication.run()` in `SpringLearnApplication.java`, built with Maven
  (`pom.xml`, group `com.cognizant`, artifact `spring-learn`).
- **Hands on 2** – `date-format.xml` defines a `SimpleDateFormat` bean loaded via
  `ClassPathXmlApplicationContext` in `displayDate()`.
- **Hands on 3** – Logging configured in `application.properties`
  (`logging.level.*`, `logging.pattern.console`) and used via SLF4J
  `Logger`/`LoggerFactory` throughout instead of `System.out.println()`.
- **Hands on 4** – `Country.java` bean with logged constructor/getters/setters,
  wired from `country.xml`, read in `displayCountry()`.
- **Hands on 5** – `displayCountry()` fetches the `country` bean twice to show
  singleton behavior (same instance). Add `scope="prototype"` to the `country`
  bean in `country.xml` to see two separate instances instead (comment included
  in the XML).
- **Hands on 6** – `country.xml` defines individual beans (`in`, `us`, `de`, `jp`)
  plus a `countryList` `ArrayList` bean referencing them, read in
  `displayCountries()`.

Running the app will print start/end and debug logs for all four exercises to
the console on startup.
