package com.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Exercise 1: Parameterized Tests
 * Runs the same test logic with several different inputs instead of writing
 * a separate @Test method for each case.
 */
public class EvenCheckerTest {

    private final EvenChecker evenChecker = new EvenChecker();

    @ParameterizedTest
    @ValueSource(ints = {2, 4, 6, 8, 100, 0, -2})
    void testIsEven_withEvenNumbers(int number) {
        assertTrue(evenChecker.isEven(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3, 5, 7, 99, -3})
    void testIsEven_withOddNumbers(int number) {
        assertFalse(evenChecker.isEven(number));
    }

    // Bonus: @CsvSource lets you pair an input with its expected output
    @ParameterizedTest
    @CsvSource({
            "2, true",
            "3, false",
            "0, true",
            "-4, true",
            "-5, false"
    })
    void testIsEven_withCsvSource(int number, boolean expected) {
        assertTrue(evenChecker.isEven(number) == expected);
    }
}
