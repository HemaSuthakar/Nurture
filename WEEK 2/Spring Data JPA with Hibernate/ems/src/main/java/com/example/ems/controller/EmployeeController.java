package com.example.ems.controller;

import com.example.ems.dto.EmployeeNameOnly;
import com.example.ems.dto.EmployeeSummary;
import com.example.ems.entity.Department;
import com.example.ems.entity.Employee;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Exercise 4: CRUD REST endpoints for Employee
// Exercise 5: query method endpoints
// Exercise 6: pagination + sorting endpoint
// Exercise 8: projection endpoints
@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @PostMapping
    public ResponseEntity<Employee> create(@RequestBody Employee employee) {
        if (employee.getDepartment() != null && employee.getDepartment().getId() != null) {
            Department dept = departmentRepository.findById(employee.getDepartment().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Department not found"));
            employee.setDepartment(dept);
        }
        return new ResponseEntity<>(employeeRepository.save(employee), HttpStatus.CREATED);
    }

    @GetMapping
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> update(@PathVariable Long id, @RequestBody Employee updated) {
        return employeeRepository.findById(id)
                .map(existing -> {
                    existing.setName(updated.getName());
                    existing.setEmail(updated.getEmail());
                    if (updated.getDepartment() != null && updated.getDepartment().getId() != null) {
                        departmentRepository.findById(updated.getDepartment().getId())
                                .ifPresent(existing::setDepartment);
                    }
                    return ResponseEntity.ok(employeeRepository.save(existing));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!employeeRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        employeeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Exercise 5: derived + custom query methods
    @GetMapping("/search")
    public List<Employee> searchByName(@RequestParam String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    @GetMapping("/by-department")
    public List<Employee> byDepartment(@RequestParam String departmentName) {
        return employeeRepository.findByDepartmentName(departmentName);
    }

    @GetMapping("/by-email")
    public Employee byEmail(@RequestParam String email) {
        return employeeRepository.findByEmailCustomQuery(email);
    }

    // Exercise 6: pagination and sorting combined
    @GetMapping("/page")
    public Page<Employee> getPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return employeeRepository.findAll(pageable);
    }

    // Exercise 8: projections
    @GetMapping("/department/{departmentId}/names")
    public List<EmployeeNameOnly> namesByDepartment(@PathVariable Long departmentId) {
        return employeeRepository.findByDepartmentId(departmentId);
    }

    @GetMapping("/department/{departmentId}/summaries")
    public List<EmployeeSummary> summariesByDepartment(@PathVariable Long departmentId) {
        return employeeRepository.findEmployeeSummariesByDepartmentId(departmentId);
    }
}
