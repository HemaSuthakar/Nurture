package com.example.ems.repository;

import com.example.ems.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Exercise 3: repository extending JpaRepository with a derived query method
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByName(String name);
}
