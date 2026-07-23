package com.example.springtesting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercise 1: Basic Unit Test for a Service Method
 * CalculatorService has no Spring dependencies, so it's tested as a plain
 * old Java object (POJO) - no Spring context needs to be loaded, which
 * keeps the test fast.
 *
 * Exercise 9: Parameterized Test with JUnit
 * Reuses CalculatorService.add() to demonstrate @ParameterizedTest with
 * multiple input/expected-output pairs via @CsvSource.
 */
public class CalculatorServiceTest {

    private final CalculatorService calculatorService = new CalculatorService();

    @Test
    public void testAdd() {
        int result = calculatorService.add(2, 3);
        assertEquals(5, result);
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1, 2",
            "2, 3, 5",
            "-1, 1, 0",
            "0, 0, 0",
            "100, 200, 300"
    })
    public void testAdd_withMultipleInputs(int a, int b, int expected) {
        assertEquals(expected, calculatorService.add(a, b));
    }
}
