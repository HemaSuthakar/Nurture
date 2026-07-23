package com.example.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A tiny downstream "business" service.
 * Every response includes the instance id / port so you can see, when calling
 * it through the gateway's load-balanced route, which instance answered.
 */
@RestController
public class ExampleController {

    @Value("${server.port}")
    private String port;

    @Value("${instance.id:example-service}")
    private String instanceId;

    private final AtomicInteger callCount = new AtomicInteger(0);

    // Plain endpoint the gateway proxies to for Exercise 1 (routing/filtering)
    @GetMapping("/example/hello")
    public Map<String, Object> hello() {
        return Map.of(
                "message", "Hello from example-service",
                "instanceId", instanceId,
                "port", port,
                "timestamp", Instant.now().toString()
        );
    }

    // Endpoint used for Exercise 2 (load balancing) - call it several times
    // through the gateway and watch the instanceId/port change.
    @GetMapping("/loadbalanced/hello")
    public Map<String, Object> loadBalancedHello() {
        return Map.of(
                "message", "Hello from a load-balanced instance",
                "instanceId", instanceId,
                "port", port,
                "callNumber", callCount.incrementAndGet(),
                "timestamp", Instant.now().toString()
        );
    }

    // Endpoint used for Exercise 3 (resilience). Randomly fails or is slow so
    // you can watch the circuit breaker / timeout / fallback kick in.
    @GetMapping("/resilient/hello")
    public Map<String, Object> resilientHello() throws InterruptedException {
        int roll = ThreadLocalRandom.current().nextInt(100);

        if (roll < 30) {
            // Simulate a failure ~30% of the time
            throw new RuntimeException("Simulated downstream failure");
        }
        if (roll < 50) {
            // Simulate slowness ~20% of the time
            Thread.sleep(3000);
        }

        return Map.of(
                "message", "Hello from the resilient endpoint",
                "instanceId", instanceId,
                "port", port,
                "timestamp", Instant.now().toString()
        );
    }
}
