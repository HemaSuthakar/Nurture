package com.example;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercise 3: Test Execution Order
 * Uses @TestMethodOrder(MethodOrderer.OrderAnnotation.class) together with
 * @Order to force tests to run in a specific, predictable sequence instead
 * of JUnit's default (non-deterministic) order.
 *
 * This is useful when tests share state and must run as a pipeline, e.g.
 * building up a list step by step and checking it at the end.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderedTests {

    private static final List<String> executionLog = new ArrayList<>();

    @Test
    @Order(1)
    void firstTest() {
        executionLog.add("first");
        assertEquals(1, executionLog.size());
    }

    @Test
    @Order(2)
    void secondTest() {
        executionLog.add("second");
        assertEquals(2, executionLog.size());
        assertEquals("first", executionLog.get(0));
    }

    @Test
    @Order(3)
    void thirdTest() {
        executionLog.add("third");
        assertEquals(3, executionLog.size());
        assertEquals(List.of("first", "second", "third"), executionLog);
    }
}
