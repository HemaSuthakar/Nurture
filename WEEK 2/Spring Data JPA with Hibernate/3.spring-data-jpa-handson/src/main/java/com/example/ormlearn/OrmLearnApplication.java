package com.example.ormlearn;

import com.example.ormlearn.entity.*;
import com.example.ormlearn.service.AttemptService;
import com.example.ormlearn.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class OrmLearnApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private AttemptService attemptService;

    public static void main(String[] args) {
        SpringApplication.run(OrmLearnApplication.class, args);
    }

    @Override
    public void run(String... args) {
        testGetAllPermanentEmployees();
        testGetAverageSalary();
        testGetAllEmployeesNative();
        testGetAttemptDetail();
    }

    // ----- Hands-on 2 -----
    private void testGetAllPermanentEmployees() {
        LOGGER.info("===== Hands-on 2: getAllPermanentEmployees (HQL with fetch) - Start =====");
        List<Employee> employees = employeeService.getAllPermanentEmployees();
        LOGGER.debug("Permanent Employees:{}", employees);
        employees.forEach(e -> LOGGER.debug("Skills:{}", e.getSkillList()));
        LOGGER.info("===== Hands-on 2: End =====");
    }

    // ----- Hands-on 4 -----
    private void testGetAverageSalary() {
        LOGGER.info("===== Hands-on 4: getAverageSalary (HQL aggregate function) - Start =====");
        double overallAverage = employeeService.getAverageSalary();
        LOGGER.debug("Average salary (all employees): {}", overallAverage);

        double departmentAverage = employeeService.getAverageSalary(1);
        LOGGER.debug("Average salary (department id=1): {}", departmentAverage);
        LOGGER.info("===== Hands-on 4: End =====");
    }

    // ----- Hands-on 5 -----
    private void testGetAllEmployeesNative() {
        LOGGER.info("===== Hands-on 5: getAllEmployeesNative (Native Query) - Start =====");
        List<Employee> employees = employeeService.getAllEmployeesNative();
        LOGGER.debug("All Employees (native query):{}", employees);
        LOGGER.info("===== Hands-on 5: End =====");
    }

    // ----- Hands-on 3 -----
    private void testGetAttemptDetail() {
        LOGGER.info("===== Hands-on 3: getAttemptDetail (HQL multi-join fetch) - Start =====");
        Attempt attempt = attemptService.getAttempt(1, 1);
        printAttemptDetails(attempt);
        LOGGER.info("===== Hands-on 3: End =====");
    }

    private void printAttemptDetails(Attempt attempt) {
        System.out.println();
        System.out.println("Username      : " + attempt.getUser().getUsername());
        System.out.println("Attempted Date: " + attempt.getAttemptDate());
        System.out.println();

        // attemptQuestions is a Set (see Attempt.java for why), so sort by id
        // to print questions in a stable, predictable order.
        attempt.getAttemptQuestions().stream()
                .sorted((a, b) -> a.getId().compareTo(b.getId()))
                .forEach(aq -> {
                    System.out.println(aq.getQuestion().getText());
                    aq.getAttemptOptions().stream()
                            .sorted((a, b) -> a.getId().compareTo(b.getId()))
                            .forEach(ao -> System.out.printf(" %-15s %-6s %s%n",
                                    ao.getOption().getText(),
                                    ao.getOption().getScore(),
                                    ao.isSelected()));
                    System.out.println();
                });
    }
}
