package com.example.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exercise 3: Using Different Appenders
 *
 * The appenders themselves (console + file) are configured declaratively
 * in src/main/resources/logback.xml. This class simply logs at a range of
 * levels; Logback routes each message to both the "console" and "file"
 * appenders as defined in that configuration, writing output to the
 * terminal and to app.log simultaneously.
 */
public class AppenderExample {

    private static final Logger logger = LoggerFactory.getLogger(AppenderExample.class);

    public static void main(String[] args) {
        logger.trace("This is a trace message");   // below root level, won't show
        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warning message");
        logger.error("This is an error message");

        System.out.println("Done. Check the console output above and the generated app.log file.");
    }
}
