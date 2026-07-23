package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.exception.EmployeeNotFoundException;
import com.cognizant.springlearn.model.Department;
import com.cognizant.springlearn.model.Employee;
import com.cognizant.springlearn.model.Skill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class EmployeeDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeDao.class);

    private final List<Employee> employees = new ArrayList<>();

    public EmployeeDao() {
        // seed with sample data so GET all / GET by id work out of the box
        Employee e1 = new Employee();
        e1.setId(1L);
        e1.setName("John Doe");
        e1.setSalary(50000.0);
        e1.setPermanent(true);
        e1.setDateOfBirth(LocalDate.of(1990, 5, 15));
        e1.setDepartment(new Department(1L, "Engineering"));
        e1.setSkills(Arrays.asList(new Skill(1L, "Java"), new Skill(2L, "Spring Boot")));
        employees.add(e1);

        Employee e2 = new Employee();
        e2.setId(2L);
        e2.setName("Jane Smith");
        e2.setSalary(60000.0);
        e2.setPermanent(false);
        e2.setDateOfBirth(LocalDate.of(1988, 11, 2));
        e2.setDepartment(new Department(2L, "Human Resources"));
        e2.setSkills(Arrays.asList(new Skill(3L, "Communication")));
        employees.add(e2);
    }

    public List<Employee> findAll() {
        return employees;
    }

    public Employee findById(Long id) throws EmployeeNotFoundException {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id " + id));
    }

    public Employee addEmployee(Employee employee) {
        LOGGER.info("Adding employee with id {}", employee.getId());
        employees.add(employee);
        return employee;
    }

    public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException {
        LOGGER.info("Updating employee with id {}", employee.getId());
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId().equals(employee.getId())) {
                employees.set(i, employee);
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Employee not found with id " + employee.getId());
    }

    public void deleteEmployee(Long id) throws EmployeeNotFoundException {
        LOGGER.info("Deleting employee with id {}", id);
        boolean removed = employees.removeIf(e -> e.getId().equals(id));
        if (!removed) {
            throw new EmployeeNotFoundException("Employee not found with id " + id);
        }
    }
}
