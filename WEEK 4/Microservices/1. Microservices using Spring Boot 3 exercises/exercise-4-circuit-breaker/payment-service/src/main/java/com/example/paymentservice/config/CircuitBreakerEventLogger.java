package com.example.paymentservice.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Registers listeners on the "thirdPartyPayment" circuit breaker so that every
 * state transition (CLOSED -> OPEN -> HALF_OPEN -> CLOSED) and every fallback-triggering
 * failure/slow-call is logged. In a real system you'd ship these events to
 * Prometheus/Grafana or an APM tool instead of (or in addition to) logging them; the
 * actuator endpoints exposed in application.yml (/actuator/circuitbreakerevents,
 * /actuator/circuitbreakers) already provide a monitoring-ready view.
 */
@Component
public class CircuitBreakerEventLogger {

    private static final Logger log = LoggerFactory.getLogger(CircuitBreakerEventLogger.class);

    private final CircuitBreakerRegistry registry;

    public CircuitBreakerEventLogger(CircuitBreakerRegistry registry) {
        this.registry = registry;
    }

    @PostConstruct
    public void registerListeners() {
        CircuitBreaker circuitBreaker = registry.circuitBreaker("thirdPartyPayment");

        circuitBreaker.getEventPublisher()
                .onStateTransition(event -> log.warn(
                        "[CircuitBreaker:{}] state transition: {} -> {}",
                        event.getCircuitBreakerName(),
                        event.getStateTransition().getFromState(),
                        event.getStateTransition().getToState()))
                .onError(event -> log.error(
                        "[CircuitBreaker:{}] call failed after {} ms - {}",
                        event.getCircuitBreakerName(),
                        event.getElapsedDuration().toMillis(),
                        event.getThrowable().toString()))
                .onSlowCallRateExceeded(event -> log.warn(
                        "[CircuitBreaker:{}] slow call rate exceeded: {}%",
                        event.getCircuitBreakerName(), event.getSlowCallRate()))
                .onFailureRateExceeded(event -> log.warn(
                        "[CircuitBreaker:{}] failure rate exceeded: {}%",
                        event.getCircuitBreakerName(), event.getFailureRate()))
                .onCallNotPermitted(event -> log.warn(
                        "[CircuitBreaker:{}] call NOT permitted - circuit is OPEN, fallback will be used",
                        event.getCircuitBreakerName()));
    }
}
