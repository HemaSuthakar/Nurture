package com.example.springtesting;

import org.springframework.stereotype.Service;

/**
 * Exercise 1: a trivial service, useful for showing what a basic unit
 * test looks like - no Spring context needed at all.
 */
@Service
public class CalculatorService {

    public int add(int a, int b) {
        return a + b;
    }
}
