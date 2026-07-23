package com.example.springtesting;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Exercise 3: Testing a REST Controller with MockMvc
 * Exercise 5: Test Controller POST Endpoint
 * Exercise 8: Test Controller Exception Handling
 *
 * @WebMvcTest loads only the web layer (controllers, @ControllerAdvice,
 * filters, converters) instead of the whole application context, and
 * @MockBean replaces UserService with a Mockito mock so we're testing the
 * controller in isolation.
 */
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    // Exercise 3
    @Test
    public void testGetUser_returnsUser() throws Exception {
        User mockUser = new User(1L, "Alice");
        when(userService.getUserById(1L)).thenReturn(mockUser);

        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    // Exercise 5
    @Test
    public void testCreateUser_returnsCreatedUser() throws Exception {
        User requestUser = new User(null, "Bob");
        User savedUser = new User(2L, "Bob");
        when(userService.saveUser(org.mockito.ArgumentMatchers.any(User.class))).thenReturn(savedUser);

        mockMvc.perform(post("/users")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(requestUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(2))
                .andExpect(jsonPath("$.name").value("Bob"));
    }

    // Exercise 8
    @Test
    public void testGetUser_notFound_returns404WithMessage() throws Exception {
        when(userService.getUserById(42L)).thenThrow(new NoSuchElementException("User not found with id: 42"));

        mockMvc.perform(get("/users/{id}", 42L))
                .andExpect(status().isNotFound())
                .andExpect(content().string("User not found"));
    }
}
