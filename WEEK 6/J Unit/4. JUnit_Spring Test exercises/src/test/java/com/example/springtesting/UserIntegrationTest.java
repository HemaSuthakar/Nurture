package com.example.springtesting;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercise 4: Integration Test with Spring Boot
 * @SpringBootTest(webEnvironment = RANDOM_PORT) boots the ENTIRE
 * application - real controller, real service, real UserRepository, and a
 * real (in-memory H2) database - and exercises it over actual HTTP calls
 * via TestRestTemplate. This is the "full flow from controller to
 * database" the exercise asks for, as opposed to the isolated/mocked unit
 * tests in the other exercises.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateThenGetUser_fullFlow() {
        // Act 1: create a user through the real POST endpoint
        User newUser = new User(null, "Diana");
        ResponseEntity<User> createResponse = restTemplate.postForEntity(
                "http://localhost:" + port + "/users", newUser, User.class);

        assertEquals(HttpStatus.OK, createResponse.getStatusCode());
        Long generatedId = createResponse.getBody().getId();

        // Act 2: fetch the same user back through the real GET endpoint
        ResponseEntity<User> getResponse = restTemplate.getForEntity(
                "http://localhost:" + port + "/users/" + generatedId, User.class);

        assertEquals(HttpStatus.OK, getResponse.getStatusCode());
        assertEquals("Diana", getResponse.getBody().getName());

        // Sanity check: the user really did land in the database
        assertEquals(true, userRepository.findById(generatedId).isPresent());
    }

    @Test
    public void testGetNonExistentUser_returns404() {
        ResponseEntity<String> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/users/999999", String.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found", response.getBody());
    }
}
