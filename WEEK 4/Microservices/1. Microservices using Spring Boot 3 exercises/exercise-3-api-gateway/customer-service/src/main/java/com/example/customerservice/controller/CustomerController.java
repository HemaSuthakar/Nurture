package com.example.customerservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @GetMapping
    public List<Map<String, Object>> getAllCustomers() {
        return List.of(
                Map.of("id", 1, "name", "Ravi Kumar"),
                Map.of("id", 2, "name", "Priya Singh")
        );
    }

    @GetMapping("/{id}")
    public Map<String, Object> getCustomer(@PathVariable int id) {
        return Map.of("id", id, "name", "Customer-" + id, "servedBy", "customer-service");
    }
}
