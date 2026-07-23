package com.example;

/**
 * Exercise 2: a service that depends on a RestClient to call an external
 * RESTful API and returns a formatted result.
 */
public class ApiService {

    private final RestClient restClient;

    public ApiService(RestClient restClient) {
        this.restClient = restClient;
    }

    public String fetchData() {
        String response = restClient.getResponse();
        return "Fetched " + response;
    }
}
