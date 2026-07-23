package com.example;

/**
 * Exercise 4: a service that depends on a NetworkClient to connect to a
 * server and returns a formatted status message.
 */
public class NetworkService {

    private final NetworkClient networkClient;

    public NetworkService(NetworkClient networkClient) {
        this.networkClient = networkClient;
    }

    public String connectToServer() {
        String connection = networkClient.connect();
        return "Connected to " + connection;
    }
}
