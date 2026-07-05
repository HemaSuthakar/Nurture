//hi
package com.cognizant.ormlearn;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.model.Department;
import com.cognizant.ormlearn.model.Employee;
import com.cognizant.ormlearn.model.Skill;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.repository.StockRepository;
import com.cognizant.ormlearn.service.DepartmentService;
import com.cognizant.ormlearn.service.EmployeeService;
import com.cognizant.ormlearn.service.SkillService;

/**
 * Runs every scenario from the Spring Data JPA hands-on document, one after
 * another, so the whole exercise set can be exercised with a single `run`.
 *
 * In the original hands-on, each test method is meant to be uncommented and
 * run one at a time. Here they are all wired up and called in sequence in
 * run(); feel free to comment any of them out if you want to focus on one
 * scenario, same as the original exercise instructs.
 */
@SpringBootApplication
public class OrmLearnApplication implements CommandLineRunner {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private SkillService skillService;

    public static void main(String[] args) {
        SpringApplication.run(OrmLearnApplication.class, args);
    }

    @Override
    public void run(String... args) {
        testCountryQueryMethods();
        testStockQueryMethods();
        testGetEmployee();
        testAddEmployee();
        testUpdateEmployee();
        testGetDepartment();
        testAddSkillToEmployee();
    }

    // ---------------------------------------------------------------
    // Hands on 1: Query Methods on Country
    // ---------------------------------------------------------------
    private void testCountryQueryMethods() {
        LOGGER.info("===== Hands on 1: Country Query Methods =====");

        List<Country> containingOu = countryRepository.findByNameContaining("ou");
        LOGGER.info("Countries containing 'ou': {}", containingOu);

        List<Country> containingOuSorted = countryRepository.findByNameContainingOrderByNameAsc("ou");
        LOGGER.info("Countries containing 'ou' (sorted asc): {}", containingOuSorted);

        List<Country> startingWithZ = countryRepository.findByNameStartingWith("Z");
        LOGGER.info("Countries starting with 'Z': {}", startingWithZ);
    }

    // ---------------------------------------------------------------
    // Hands on 2: Query Methods on Stock
    // ---------------------------------------------------------------
    private void testStockQueryMethods() {
        LOGGER.info("===== Hands on 2: Stock Query Methods =====");

        LOGGER.debug("FB stock in September 2019: {}",
                stockRepository.findByCodeAndDateBetween("FB",
                        LocalDate.of(2019, 9, 1), LocalDate.of(2019, 9, 30)));

        LOGGER.debug("GOOGL stock with close > 1250: {}",
                stockRepository.findByCodeAndCloseGreaterThan("GOOGL", new BigDecimal("1250")));

        LOGGER.debug("Top 3 highest volume days: {}",
                stockRepository.findTop3ByOrderByVolumeDesc());

        LOGGER.debug("Top 3 lowest NFLX close prices: {}",
                stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX"));
    }

    // ---------------------------------------------------------------
    // Hands on 4: ManyToOne (Employee -> Department)
    // ---------------------------------------------------------------
    private void testGetEmployee() {
        LOGGER.info("===== Hands on 4/6: Get Employee with Department and Skills =====");
        LOGGER.info("Start");
        Employee employee = employeeService.get(1);
        LOGGER.debug("Employee:{}", employee);
        LOGGER.debug("Department:{}", employee.getDepartment());
        LOGGER.debug("Skills:{}", employee.getSkillList());
        LOGGER.info("End");
    }

    private void testAddEmployee() {
        LOGGER.info("===== Hands on 4: Add Employee =====");
        LOGGER.info("Start");
        Employee employee = new Employee();
        employee.setName("Grace Hopper");
        employee.setSalary(90000.00);
        employee.setPermanent(true);
        employee.setDateOfBirth(LocalDate.of(1985, 3, 21));

        Department department = departmentService.get(1);
        employee.setDepartment(department);

        employeeService.save(employee);
        LOGGER.debug("Saved employee:{}", employee);
        LOGGER.info("End");
    }

    private void testUpdateEmployee() {
        LOGGER.info("===== Hands on 4: Update Employee =====");
        LOGGER.info("Start");
        Employee employee = employeeService.get(2);

        Department newDepartment = departmentService.get(3);
        employee.setDepartment(newDepartment);

        employeeService.save(employee);
        LOGGER.debug("Updated employee:{}", employee);
        LOGGER.info("End");
    }

    // ---------------------------------------------------------------
    // Hands on 5: OneToMany (Department -> Employees)
    // ---------------------------------------------------------------
    private void testGetDepartment() {
        LOGGER.info("===== Hands on 5: Get Department with Employees =====");
        LOGGER.info("Start");
        // Department 1 has more than one employee - see data.sql.
        // NOTE: if employeeList in Department.java is changed back to the
        // default LAZY fetch, calling getEmployeeList() outside of a
        // transaction here will raise a LazyInitializationException -
        // exactly what the hands-on exercise asks you to observe.
        Department department = departmentService.get(1);
        LOGGER.debug("Department:{}", department);
        LOGGER.debug("Employees:{}", department.getEmployeeList());
        LOGGER.info("End");
    }

    // ---------------------------------------------------------------
    // Hands on 6: ManyToMany (Employee <-> Skill)
    // ---------------------------------------------------------------
    private void testAddSkillToEmployee() {
        LOGGER.info("===== Hands on 6: Add Skill to Employee =====");
        LOGGER.info("Start");
        // Employee 6 / Skill 5 has no relationship yet - see data.sql.
        Employee employee = employeeService.get(6);
        Skill skill = skillService.get(5);

        employee.getSkillList().add(skill);
        employeeService.save(employee);

        LOGGER.debug("Employee {} now has skills: {}", employee.getName(), employee.getSkillList());
        LOGGER.info("End");
    }
}
