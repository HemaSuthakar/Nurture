package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository r;

    public EmployeeService(EmployeeRepository r) {
        this.r = r;
    }

    @Transactional
    public Employee addEmployee(Employee e) {
        return r.save(e);
    }
}
