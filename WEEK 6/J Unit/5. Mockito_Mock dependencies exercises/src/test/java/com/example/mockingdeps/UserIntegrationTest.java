package com.example.mockingdeps;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Exercise 3: Mocking a Service Dependency in an Integration Test
 *
 * @SpringBootTest boots the FULL application context (unlike @WebMvcTest),
 * and @AutoConfigureMockMvc adds a MockMvc bean so we can drive real HTTP
 * requests through the real DispatcherServlet, filters, etc.
 *
 * Even though the whole context is loaded, @MockBean still replaces
 * UserService with a Mockito mock everywhere it's used (here, inside the
 * real UserController) - so this test exercises the real web layer while
 * keeping the service layer (and therefore the database) out of the
 * picture entirely, per the exercise's hint.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class UserIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    public void testGetUser_withMockedService_fullSpringContext() throws Exception {
        User mockUser = new User(5L, "Grace");
        when(userService.getUserById(5L)).thenReturn(mockUser);

        mockMvc.perform(get("/users/{id}", 5L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(5))
                .andExpect(jsonPath("$.name").value("Grace"));
    }
}
