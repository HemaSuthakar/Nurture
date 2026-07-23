package com.example;

/**
 * Represents an external API / dependency that MyService relies on.
 * In real life this might make an HTTP call, hit a database, etc.
 * In these exercises it is always MOCKED with Mockito rather than
 * having a real implementation - that's the whole point.
 */
public interface ExternalApi {

    String getData();

    String getDataById(int id);

    void saveData(String data);

    void connect();

    void disconnect();

    void logMessage(String message);
}
