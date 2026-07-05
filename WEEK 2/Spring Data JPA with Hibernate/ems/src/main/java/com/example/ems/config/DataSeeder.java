package com.example.ems.config;

import com.example.ems.entity.Department;
import com.example.ems.entity.Employee;
import com.example.ems.repository.DepartmentRepository;
import com.example.ems.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Seeds a couple of departments and employees so the API has data to try immediately
@Configuration
public class DataSeeder {

    @Bean
    public CommandLineRunner seedData(DepartmentRepository departmentRepository,
                                       EmployeeRepository employeeRepository) {
        return args -> {
            Department engineering = departmentRepository.save(new Department("Engineering"));
            Department sales = departmentRepository.save(new Department("Sales"));

            employeeRepository.save(new Employee("Alice Johnson", "alice@example.com", engineering));
            employeeRepository.save(new Employee("Bob Smith", "bob@example.com", engineering));
            employeeRepository.save(new Employee("Carol Davis", "carol@example.com", sales));
        };
    }
}
