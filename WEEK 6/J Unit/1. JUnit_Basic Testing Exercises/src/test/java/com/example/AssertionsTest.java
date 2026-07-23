package com.example;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Exercise 3: Assertions in JUnit
 * Demonstrates the different assertion methods available in JUnit.
 */
public class AssertionsTest {

    @Test
    public void testAssertions() {
        // Assert equals
        assertEquals(5, 2 + 3);
        // Assert true
        assertTrue(5 > 3);
        // Assert false
        assertFalse(5 < 3);
        // Assert null
        assertNull(null);
        // Assert not null
        assertNotNull(new Object());
    }

    @Test
    public void testMoreAssertions() {
        // Assert same (reference equality)
        String a = "hello";
        String b = a;
        assertSame(a, b);

        // Assert not same
        String c = new String("hello");
        assertNotSame(a, c);

        // Assert array equals
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals(expected, actual);
    }
}
