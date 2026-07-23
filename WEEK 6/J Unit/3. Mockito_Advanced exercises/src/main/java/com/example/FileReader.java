package com.example;

/**
 * A small abstraction over reading file content, so it can be mocked.
 * NOTE: this is our own interface, not java.io.FileReader - naming it
 * the same is intentional, matching the worksheet's solution code. Since
 * we never import java.io.FileReader in this package, there's no clash.
 */
public interface FileReader {

    String read();
}
