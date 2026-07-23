package com.example;

/**
 * The class under test in all Mockito exercises.
 * It depends on ExternalApi, which is injected via the constructor
 * (constructor injection makes it trivial to swap in a mock during tests).
 */
public class MyService {

    private final ExternalApi externalApi;

    public MyService(ExternalApi externalApi) {
        this.externalApi = externalApi;
    }

    /** Exercise 1 & 2: simply delegates to the external API. */
    public String fetchData() {
        return externalApi.getData();
    }

    /** Exercise 3: delegates a call that takes an argument. */
    public String fetchDataById(int id) {
        return externalApi.getDataById(id);
    }

    /** Exercise 4: delegates to a void method. */
    public void saveData(String data) {
        externalApi.saveData(data);
    }

    /**
     * Exercise 6: calls three external methods in a specific order -
     * connect, then fetch, then disconnect. Useful for verifying order.
     */
    public String performSequence() {
        externalApi.connect();
        String data = externalApi.getData();
        externalApi.disconnect();
        return data;
    }

    /**
     * Exercise 7: calls a void method that might throw, and handles the
     * exception gracefully instead of letting it propagate.
     *
     * @return true if the log call succeeded, false if it threw an exception
     */
    public boolean logSafely(String message) {
        try {
            externalApi.logMessage(message);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }
}
