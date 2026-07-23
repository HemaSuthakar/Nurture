package com.example;

/**
 * Represents a client that connects to a network resource / server.
 * Mocked in tests so no real network connection is made.
 */
public interface NetworkClient {

    String connect();
}
