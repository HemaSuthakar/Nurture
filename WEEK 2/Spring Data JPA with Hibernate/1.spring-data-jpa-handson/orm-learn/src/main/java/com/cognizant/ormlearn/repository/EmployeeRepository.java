package com.cognizant.ormlearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cognizant.ormlearn.model.Employee;

// Hands on 4 - the entire "Hibernate SessionFactory/Transaction/save()"
// dance from EmployeeXmlDemo.addEmployee() / EmployeeAnnotationDemo.addEmployee()
// collapses into this one interface once Spring Data JPA is layered on top.
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
