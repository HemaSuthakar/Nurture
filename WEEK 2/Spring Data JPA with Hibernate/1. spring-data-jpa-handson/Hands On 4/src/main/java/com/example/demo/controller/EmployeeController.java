package com.example.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Employee;
import com.example.demo.service.EmployeeService;

@RestController
public class EmployeeController {

    private final EmployeeService s;

    public EmployeeController(EmployeeService s) {
        this.s = s;
    }

    @PostMapping("/spring/add")
    public Employee add(@RequestBody Employee e) {
        return s.addEmployee(e);
    }
}
