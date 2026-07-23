package com.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Exercise 4: Exception Testing
 * Verifies that ExceptionThrower.throwException throws the expected
 * exception type (and message) when given invalid input, and behaves
 * normally otherwise.
 */
public class ExceptionThrowerTest {

    private final ExceptionThrower exceptionThrower = new ExceptionThrower();

    @Test
    void testThrowException_withNegativeValue_throwsIllegalArgumentException() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> exceptionThrower.throwException(-5)
        );
        assertEquals("Value must not be negative: -5", thrown.getMessage());
    }

    @Test
    void testThrowException_withNonNegativeValue_returnsDoubledValue() {
        int result = exceptionThrower.throwException(10);
        assertEquals(20, result);
    }
}
