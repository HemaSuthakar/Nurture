package com.example;

/**
 * Represents a data repository (e.g. backed by a database). Mocked in
 * tests so we never touch a real database.
 */
public interface Repository {

    String getData();
}
