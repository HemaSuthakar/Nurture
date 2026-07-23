package com.example;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercise 3: Mocking File I/O
 * Mocks both a FileReader and a FileWriter so FileService can be tested
 * without touching the real filesystem.
 */
public class FileServiceTest {

    @Test
    public void testServiceWithMockFileIO() {
        FileReader mockFileReader = mock(FileReader.class);
        FileWriter mockFileWriter = mock(FileWriter.class);
        when(mockFileReader.read()).thenReturn("Mock File Content");

        FileService fileService = new FileService(mockFileReader, mockFileWriter);
        String result = fileService.processFile();

        assertEquals("Processed Mock File Content", result);
    }

    @Test
    public void testServiceWritesResult() {
        FileReader mockFileReader = mock(FileReader.class);
        FileWriter mockFileWriter = mock(FileWriter.class);

        FileService fileService = new FileService(mockFileReader, mockFileWriter);
        fileService.saveResult("Final Output");

        verify(mockFileWriter).write("Final Output");
    }
}
