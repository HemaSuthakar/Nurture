package com.example.ormlearn.repository;

import com.example.ormlearn.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    // ----- Hands-on 2: Get all permanent employees using HQL -----
    //
    // Evolution of this query (see the hands-on write-up):
    //   1) "SELECT e FROM Employee e WHERE e.permanent = 1"
    //      -> works, but if department/skillList were EAGER this fires extra
    //         per-row queries (or fails once EAGER is removed).
    //   2) "... left join e.department d left join e.skillList ..."
    //      -> join alone only lets you filter/sort on the association,
    //         it does NOT populate the Java objects.
    //   3) The version below adds "fetch" after each join, which is what
    //      actually populates department and skillList in a single SQL
    //      statement. This is the optimized, final version.
    //
    // NOTE: the docx's sample compares "e.permanent = 1". This project uses
    // Hibernate 6 (Spring Boot 3.2), which is strict about JPQL types, so a
    // boolean column must be compared with "= true" rather than the numeric
    // literal 1.
    @Query(value = "SELECT e FROM Employee e " +
            "left join fetch e.department d " +
            "left join fetch e.skillList " +
            "WHERE e.permanent = true")
    List<Employee> getAllPermanentEmployees();

    // ----- Hands-on 4: Get average salary using HQL -----

    @Query(value = "SELECT AVG(e.salary) FROM Employee e")
    double getAverageSalary();

    // Overload that filters by department id
    @Query(value = "SELECT AVG(e.salary) FROM Employee e where e.department.id = :id")
    double getAverageSalary(@Param("id") int id);

    // ----- Hands-on 5: Get all employees using Native Query -----

    @Query(value = "SELECT * FROM employee", nativeQuery = true)
    List<Employee> getAllEmployeesNative();
}
