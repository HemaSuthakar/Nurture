# Exercise 2 — Inventory Management System with Service Discovery

Four Spring Boot 3 projects:

| Service            | Port | Role                                                |
|---------------------|------|------------------------------------------------------|
| `eureka-server`      | 8761 | Spring Cloud Netflix Eureka service registry          |
| `config-server`      | 8888 | Spring Cloud Config Server (native profile, no Git needed) |
| `product-service`    | 8083 | Manages products & stock, registers with Eureka       |
| `inventory-service`  | 8084 | Tracks stock levels, calls `product-service` by name via OpenFeign + Eureka |

## Start order

1. `eureka-server`
2. `config-server`
3. `product-service`
4. `inventory-service`

```bash
cd eureka-server && mvn spring-boot:run
cd config-server && mvn spring-boot:run
cd product-service && mvn spring-boot:run
cd inventory-service && mvn spring-boot:run
```

- Eureka dashboard: http://localhost:8761 — you should see `PRODUCT-SERVICE` and
  `INVENTORY-SERVICE` registered.
- Config server check: http://localhost:8888/product-service/default should return the
  centralized properties from `config-server/src/main/resources/config-repo/product-service.properties`.
- `product-service` reads that centralized config: http://localhost:8083/api/products/config-check

## Try it out

```bash
# Create a product
curl -X POST http://localhost:8083/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Wireless Mouse","price":19.99,"stock":100}'

# Create a stock record referencing that product (productId=1)
curl -X POST http://localhost:8084/api/inventory \
  -H "Content-Type: application/json" \
  -d '{"productId":1,"quantityAvailable":100,"warehouseLocation":"Chennai-A1"}'

# inventory-service calls product-service via Eureka + Feign to combine data
curl http://localhost:8084/api/inventory/1/details
```

## Notes

- Centralized config uses the Config Server's **native** profile (reads from a local
  `config-repo` folder bundled in the config-server's classpath), so you don't need to
  set up a separate Git repo to try this immediately. To point it at a real Git repo,
  swap `spring.cloud.config.server.native.search-locations` for
  `spring.cloud.config.server.git.uri` in `config-server/application.properties`.
- `spring.config.import=optional:configserver:...` means each service will still start
  even if the config server isn't running (handy while testing individually).
