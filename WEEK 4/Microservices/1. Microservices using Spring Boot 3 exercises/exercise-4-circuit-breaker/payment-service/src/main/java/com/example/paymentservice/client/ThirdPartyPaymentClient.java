package com.example.paymentservice.client;

import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Simulates a slow / unreliable third-party payment gateway so the circuit breaker
 * behaviour can be observed without needing a real external dependency.
 *
 * - ~40% of calls are artificially slow (3-6 seconds) to trigger the TimeLimiter/slow-call logic.
 * - ~20% of calls throw an exception to simulate the third-party API failing outright.
 */
@Component
public class ThirdPartyPaymentClient {

    private final Random random = ThreadLocalRandom.current();

    public String charge(String orderId, double amount) {
        double roll = random.nextDouble();

        if (roll < 0.2) {
            throw new RuntimeException("Third-party payment gateway returned 500 Internal Server Error");
        }

        try {
            if (roll < 0.6) {
                // Simulate a slow call
                Thread.sleep(3000 + random.nextInt(3000));
            } else {
                // Simulate a normal, fast call
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Call interrupted", e);
        }

        return "SUCCESS - charged " + amount + " for order " + orderId + " via third-party gateway";
    }
}
