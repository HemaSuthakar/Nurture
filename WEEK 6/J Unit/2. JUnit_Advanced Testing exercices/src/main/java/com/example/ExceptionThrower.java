package com.example;

/**
 * Exercise 4: Exception Testing
 * A class with a method that throws an exception under certain conditions.
 */
public class ExceptionThrower {

    /**
     * Throws an IllegalArgumentException if the given value is negative.
     * Otherwise returns the value doubled.
     */
    public int throwException(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value must not be negative: " + value);
        }
        return value * 2;
    }
}
