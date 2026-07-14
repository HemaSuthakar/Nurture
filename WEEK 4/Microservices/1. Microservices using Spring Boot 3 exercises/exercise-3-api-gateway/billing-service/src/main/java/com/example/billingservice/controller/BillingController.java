package com.example.billingservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    @GetMapping("/invoices")
    public List<Map<String, Object>> getInvoices() {
        return List.of(
                Map.of("id", 101, "amount", 249.50),
                Map.of("id", 102, "amount", 89.00)
        );
    }

    @GetMapping("/invoices/{id}")
    public Map<String, Object> getInvoice(@PathVariable int id) {
        return Map.of("id", id, "amount", 199.99, "servedBy", "billing-service");
    }
}
