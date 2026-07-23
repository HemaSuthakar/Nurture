# Exercise 4 — Resilient Microservices with Circuit Breaker

A single Spring Boot 3 project: **payment-service** (port `8095`), using **Resilience4j**
via `spring-cloud-starter-circuitbreaker-resilience4j`.

## What's simulated

`ThirdPartyPaymentClient` stands in for the "slow third-party API" mentioned in the
exercise:
- ~20% of calls throw an exception (simulated outage)
- ~40% of calls sleep 3-6 seconds (simulated slowness)
- ~40% of calls succeed quickly

`PaymentService.chargeCard(...)` wraps that call with `@CircuitBreaker` + `@TimeLimiter`
(the TimeLimiter cuts off any call that takes longer than 3s, feeding "slow call" data to
the breaker). When the breaker is open, or a call fails/times out, `fallbackCharge(...)`
runs instead and returns a graceful "queued for retry" response rather than an error.

Circuit breaker parameters (window size, failure threshold, open-state duration, etc.)
live in `application.yml` under `resilience4j.circuitbreaker.instances.thirdPartyPayment`.

## Logging & monitoring fallback events

`CircuitBreakerEventLogger` subscribes to the breaker's event stream and logs:
- state transitions (CLOSED → OPEN → HALF_OPEN → CLOSED)
- individual call failures
- failure-rate / slow-call-rate threshold breaches
- calls rejected outright because the circuit is OPEN (i.e., went straight to fallback)

For real monitoring, actuator endpoints are already exposed:
- http://localhost:8095/actuator/health (shows circuit breaker health)
- http://localhost:8095/actuator/circuitbreakers (current state of all breakers)
- http://localhost:8095/actuator/circuitbreakerevents (event history)

## Run it

```bash
cd payment-service
mvn spring-boot:run
```

## Try it out

Call the endpoint repeatedly (some calls will be slow/fail, forcing the breaker open):

```bash
for i in $(seq 1 15); do
  curl -s -X POST "http://localhost:8095/api/payments/charge?orderId=ORD-$i&amount=49.99"
  echo
done
```

Watch the console logs for `state transition` and `call NOT permitted` messages once
enough failures/slow calls accumulate, then check:

```bash
curl http://localhost:8095/actuator/circuitbreakers
curl http://localhost:8095/actuator/circuitbreakerevents
```
