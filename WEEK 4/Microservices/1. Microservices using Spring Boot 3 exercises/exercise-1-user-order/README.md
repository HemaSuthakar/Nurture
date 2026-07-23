# Exercise 1 — User & Order Management System

Two independent Spring Boot 3 microservices that communicate over REST using **OpenFeign**.

- **user-service** (port `8081`) — manages users, backed by an in-memory H2 database.
- **order-service** (port `8082`) — manages orders; before saving an order it calls
  `user-service` via a Feign client (`UserClient`) to verify the user exists.

## How to import

1. Open each folder (`user-service`, `order-service`) as a **separate project** in your IDE
   (IntelliJ: File → Open → select the `pom.xml`; Eclipse/STS: Import → Existing Maven Project).
2. Each has its own `pom.xml`, so they build/run independently.

## How to run

Start `user-service` first, then `order-service`:

```bash
cd user-service
mvn spring-boot:run
```

```bash
cd order-service
mvn spring-boot:run
```

## Try it out

```bash
# Create a user
curl -X POST http://localhost:8081/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Alice","email":"alice@example.com"}'

# Create an order for that user (userId=1)
curl -X POST http://localhost:8082/api/orders \
  -H "Content-Type: application/json" \
  -d '{"userId":1,"product":"Laptop","quantity":1}'

# Try an order for a non-existent user -> 400 Bad Request
curl -X POST http://localhost:8082/api/orders \
  -H "Content-Type: application/json" \
  -d '{"userId":999,"product":"Phone","quantity":1}'
```

H2 consoles: http://localhost:8081/h2-console and http://localhost:8082/h2-console
(JDBC URL `jdbc:h2:mem:userdb` / `jdbc:h2:mem:orderdb`, user `sa`, empty password).

## Switching to MySQL/PostgreSQL

Each `application.properties` has commented-out MySQL settings, and `pom.xml` has a
commented-out `mysql-connector-j` dependency. Uncomment both, adjust credentials, and
make sure the target database exists (or use `createDatabaseIfNotExist=true` as shown).
