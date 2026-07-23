package com.example.springtesting;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Exercise 7: Test Custom Repository Query
 * @DataJpaTest spins up an in-memory (H2) database and only the JPA-related
 * beans, and rolls back each test's transaction automatically - ideal for
 * testing a repository's derived query method in isolation.
 */
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByName_returnsMatchingUsers() {
        userRepository.save(new User(null, "Alice"));
        userRepository.save(new User(null, "Bob"));
        userRepository.save(new User(null, "Alice"));

        List<User> results = userRepository.findByName("Alice");

        assertEquals(2, results.size());
        assertTrue(results.stream().allMatch(u -> u.getName().equals("Alice")));
    }

    @Test
    public void testFindByName_noMatches_returnsEmptyList() {
        userRepository.save(new User(null, "Charlie"));

        List<User> results = userRepository.findByName("Nonexistent");

        assertTrue(results.isEmpty());
    }
}
