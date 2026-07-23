package com.cognizant.springlearn;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllEmployees_returnsSeededList() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    void getEmployeeById_notFound_returns404() throws Exception {
        mockMvc.perform(get("/employees/{id}", 999))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404));
    }

    @Test
    void addEmployee_missingRequiredFields_returns400WithErrors() throws Exception {
        String invalidPayload = "{\"name\":\"\"}";

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPayload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.errors").isArray());
    }

    @Test
    void updateEmployee_nonExistentId_returns404() throws Exception {
        String payload = "{\"id\":999,\"name\":\"Ghost\",\"salary\":1000,\"permanent\":true}";

        mockMvc.perform(put("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteEmployee_nonExistentId_returns404() throws Exception {
        mockMvc.perform(delete("/employees/{id}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    void addCountry_invalidCodeLength_returns400() throws Exception {
        String invalidPayload = "{\"code\":\"I\",\"name\":\"India\"}";

        mockMvc.perform(post("/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidPayload))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]").value("Country code should be 2 characters"));
    }

    @Test
    void addCountry_validPayload_returns200() throws Exception {
        String payload = "{\"code\":\"FR\",\"name\":\"France\"}";

        mockMvc.perform(post("/countries")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("FR"))
                .andExpect(jsonPath("$.name").value("France"));
    }
}
