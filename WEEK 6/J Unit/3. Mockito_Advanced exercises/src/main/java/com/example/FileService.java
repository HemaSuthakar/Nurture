package com.example;

/**
 * Exercise 3: a service that reads file content via a FileReader,
 * processes it, and can write results back out via a FileWriter.
 * Both dependencies are mocked in tests so no real file I/O happens.
 */
public class FileService {

    private final FileReader fileReader;
    private final FileWriter fileWriter;

    public FileService(FileReader fileReader, FileWriter fileWriter) {
        this.fileReader = fileReader;
        this.fileWriter = fileWriter;
    }

    public String processFile() {
        String content = fileReader.read();
        return "Processed " + content;
    }

    public void saveResult(String result) {
        fileWriter.write(result);
    }
}
