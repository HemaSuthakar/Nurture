package com.example;

/**
 * Exercises 1 & 5: a service that depends on a Repository to fetch raw
 * data, then "processes" it (here, by prefixing it).
 */
public class Service {

    private final Repository repository;

    public Service(Repository repository) {
        this.repository = repository;
    }

    public String processData() {
        String data = repository.getData();
        return "Processed " + data;
    }
}
