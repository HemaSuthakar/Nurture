# Exercise 3 — API Gateway

Three Spring Boot 3 projects:

| Service            | Port | Role                                   |
|---------------------|------|-----------------------------------------|
| `api-gateway`        | 8080 | Spring Cloud Gateway, routes + rate limits + caches |
| `customer-service`   | 8091 | Simple REST service                     |
| `billing-service`    | 8092 | Simple REST service                     |

## Start order

```bash
cd customer-service && mvn spring-boot:run
```
```bash
cd billing-service && mvn spring-boot:run
```
```bash
cd api-gateway && mvn spring-boot:run
```

## Routing / path rewriting

The gateway exposes clean external paths and rewrites them to each service's real API path:

- `GET http://localhost:8080/customers/1` → forwarded to `http://localhost:8091/api/customers/1`
- `GET http://localhost:8080/billing/invoices/101` → forwarded to `http://localhost:8092/api/billing/invoices/101`

## Rate limiting

Implemented as a custom `SimpleRateLimiterGatewayFilterFactory`
(`api-gateway/src/main/java/com/example/apigateway/filter`) — an in-memory, fixed-window
limiter (5 requests / 10 seconds per client IP+route by default, configurable per-route
in `application.yml`). This avoids requiring Redis just to try it out.

Test it:
```bash
for i in $(seq 1 8); do curl -i http://localhost:8080/customers/1 | grep -E "HTTP|X-RateLimit"; done
```
After the 5th request within the window you should see `429 Too Many Requests`.

> Note: Spring Cloud Gateway also ships a built-in `RequestRateLimiter` filter, but it
> requires a Redis connection. If you have Redis available, you can add
> `spring-boot-starter-data-redis-reactive` and swap in the built-in filter instead.

## Caching

Uses Spring Cloud Gateway's built-in **local response cache** (in-memory, via Caffeine —
no Redis needed), enabled globally in `application.yml`
(`spring.cloud.gateway.filter.local-response-cache`), with a 30 second TTL.

## Try it out

```bash
curl http://localhost:8080/customers
curl http://localhost:8080/customers/1
curl http://localhost:8080/billing/invoices
curl http://localhost:8080/billing/invoices/101
```
