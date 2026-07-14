package com.example.gateway;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.Map;

/**
 * Fallback response returned when the circuit breaker on the /resilient/**
 * route is OPEN or the downstream call times out.
 */
@RestController
public class FallbackController {

    @GetMapping("/fallback/resilient")
    public Mono<Map<String, Object>> resilientFallback() {
        return Mono.just(Map.of(
                "message", "The resilient-service is currently unavailable. Please try again shortly.",
                "fallback", true,
                "timestamp", Instant.now().toString()
        ));
    }
}
