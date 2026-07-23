package com.cognizant.springlearn.controller;

import com.cognizant.springlearn.exception.EmployeeNotFoundException;
import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;

    // Get all employees
    @GetMapping
    public List<Employee> getAllEmployees() {
        LOGGER.info("Start");
        return employeeService.getAllEmployees();
    }

    // Get a specific employee
    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        return employeeService.getEmployeeById(id);
    }

    // Create an employee based on data in payload
    @PostMapping
    public Employee addEmployee(@RequestBody @Valid Employee employee) {
        LOGGER.info("Start");
        return employeeService.addEmployee(employee);
    }

    // Update an employee based on data in payload
    @PutMapping
    public void updateEmployee(@RequestBody @Valid Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        employeeService.updateEmployee(employee);
        LOGGER.info("End");
    }

    // Delete a specific employee
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) throws EmployeeNotFoundException {
        LOGGER.info("Start");
        employeeService.deleteEmployee(id);
        LOGGER.info("End");
    }
}
