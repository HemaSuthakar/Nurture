package com.example.ems.repository;

import com.example.ems.dto.EmployeeNameOnly;
import com.example.ems.dto.EmployeeSummary;
import com.example.ems.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Exercise 5: derived query methods (keyword-based)
    List<Employee> findByDepartmentName(String departmentName);

    List<Employee> findByNameContainingIgnoreCase(String name);

    // Exercise 5: custom query with @Query (JPQL)
    @Query("SELECT e FROM Employee e WHERE e.email = :email")
    Employee findByEmailCustomQuery(@Param("email") String email);

    // Exercise 5: named query defined on the Employee entity (@NamedQuery)
    List<Employee> findByNameNamedQuery(@Param("name") String name);

    List<Employee> findByDepartmentNameNamedQuery(@Param("departmentName") String departmentName);

    // Exercise 6: pagination + sorting - Spring Data derives paging support automatically
    Page<Employee> findByDepartmentName(String departmentName, Pageable pageable);

    Page<Employee> findAll(Pageable pageable);

    // Exercise 8: interface-based projection
    List<EmployeeNameOnly> findByDepartmentId(Long departmentId);

    // Exercise 8: class-based projection via JPQL constructor expression
    @Query("SELECT new com.example.ems.dto.EmployeeSummary(e.name, e.department.name) " +
            "FROM Employee e WHERE e.department.id = :departmentId")
    List<EmployeeSummary> findEmployeeSummariesByDepartmentId(@Param("departmentId") Long departmentId);
}
