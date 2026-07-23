package com.example;

/**
 * A small abstraction over writing file content, so it can be mocked.
 * NOTE: this is our own interface, not java.io.FileWriter (see FileReader
 * for the same note).
 */
public interface FileWriter {

    void write(String content);
}
