package com.example.mockingdeps;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Exercise 1: Mocking a Service Dependency in a Controller Test
 *
 * @WebMvcTest(UserController.class) loads only the web layer (controllers,
 * filters, converters) - it does NOT load @Service or @Repository beans.
 * @MockBean creates a Mockito mock of UserService and registers it in the
 * Spring context, so UserController's @Autowired UserService gets the
 * mock instead of a real one.
 */
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetUser_returnsUserFromMockedService() throws Exception {
        User mockUser = new User(1L, "Alice");
        when(userService.getUserById(1L)).thenReturn(mockUser);

        mockMvc.perform(get("/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Alice"));
    }

    @Test
    public void testGetUser_serviceReturnsNull_respondsOkWithEmptyBody() throws Exception {
        when(userService.getUserById(99L)).thenReturn(null);

        // Note: the controller as written always returns 200 OK, even when
        // the service returns null for a missing user (see UserService's
        // orElse(null)) - this test documents that current behavior.
        mockMvc.perform(get("/users/{id}", 99L))
                .andExpect(status().isOk());
    }
}
