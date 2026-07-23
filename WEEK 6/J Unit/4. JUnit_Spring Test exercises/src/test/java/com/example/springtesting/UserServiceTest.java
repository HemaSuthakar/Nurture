package com.example.springtesting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Exercise 2: Mocking a Repository in a Service Test
 * Uses plain Mockito (no Spring context) to mock UserRepository, which is
 * enough for a unit test of UserService's logic - loading the full Spring
 * context would be unnecessary overhead here.
 *
 * Exercise 6: Test Service Exception Handling
 * Verifies that UserService.getUserById() throws NoSuchElementException
 * when the repository has no matching user.
 */
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testGetUserById_found() {
        User mockUser = new User(1L, "Alice");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));

        User result = userService.getUserById(1L);

        assertEquals("Alice", result.getName());
        verify(userRepository).findById(1L);
    }

    @Test
    public void testGetUserById_missingUser_throwsException() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.getUserById(99L));
    }
}
