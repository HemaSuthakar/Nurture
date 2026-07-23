package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

/**
 * Exercise 5: Timeout and Performance Testing
 * Demonstrates two ways to enforce a time limit on a test in JUnit 5:
 *   1. The declarative @Timeout annotation (fails the test if it runs too long)
 *   2. The programmatic assertTimeout() assertion
 */
public class PerformanceTesterTest {

    private final PerformanceTester performanceTester = new PerformanceTester();

    @Test
    @Timeout(1) // fails automatically if the test takes longer than 1 second
    void testPerformTask_completesWithinTimeout() throws InterruptedException {
        String result = performanceTester.performTask(200); // 200ms simulated work
        assertEquals("Task completed", result);
    }

    @Test
    void testPerformTask_usingAssertTimeout() {
        assertTimeout(Duration.ofMillis(500), () -> {
            String result = performanceTester.performTask(200);
            assertEquals("Task completed", result);
        });
    }
}
