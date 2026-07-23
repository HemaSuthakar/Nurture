package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 2: Parameterized Logging
 *
 * Parameterized logging avoids the cost of building a concatenated
 * string when the log level is disabled, and keeps log statements
 * readable. Placeholders are marked with {} and filled in order.
 */
public class ParameterizedLoggingExample {

    private static final Logger logger = LoggerFactory.getLogger(ParameterizedLoggingExample.class);

    public static void main(String[] args) {
        String userName = "Alice";
        int userId = 101;
        double accountBalance = 2543.75;

        // Single parameter
        logger.info("User {} logged in", userName);

        // Two parameters
        logger.debug("User {} has id {}", userName, userId);

        // More than two parameters requires an Object[] (or varargs are handled automatically)
        logger.info("User {} (id={}) has balance {}", userName, userId, accountBalance);

        // Parameterized logging combined with an exception
        try {
            processTransaction(userId, -50.0);
        } catch (IllegalArgumentException e) {
            logger.error("Transaction failed for user {}: {}", userId, e.getMessage(), e);
        }
    }

    private static void processTransaction(int userId, double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Transaction amount cannot be negative: " + amount);
        }
    }
}
