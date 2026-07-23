package com.example;

/**
 * Exercise 5: Timeout and Performance Testing
 * A class with a method that simulates some work being performed.
 */
public class PerformanceTester {

    /**
     * Simulates a task that takes a given number of milliseconds to run.
     */
    public String performTask(long simulatedDelayMillis) throws InterruptedException {
        Thread.sleep(simulatedDelayMillis);
        return "Task completed";
    }
}
