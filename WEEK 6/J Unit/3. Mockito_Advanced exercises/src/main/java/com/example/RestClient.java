package com.example;

/**
 * Represents a client that calls an external RESTful API.
 * Mocked in tests so we never make a real HTTP call.
 */
public interface RestClient {

    String getResponse();
}
