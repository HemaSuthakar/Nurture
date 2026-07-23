package com.example.ems.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicUpdate;

// Exercise 2: Employee entity, many-to-one side with Department
// Exercise 5: Named queries, invoked via EmployeeRepository.findByNameNamedQuery()
// Exercise 10: Hibernate-specific @DynamicUpdate only updates changed columns
@Entity
@Table(name = "employees")
@DynamicUpdate
@NamedQueries({
        @NamedQuery(
                name = "Employee.findByNameNamedQuery",
                query = "SELECT e FROM Employee e WHERE e.name = :name"
        ),
        @NamedQuery(
                name = "Employee.findByDepartmentNameNamedQuery",
                query = "SELECT e FROM Employee e WHERE e.department.name = :departmentName"
        )
})
public class Employee extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    public Employee() {
    }

    public Employee(String name, String email, Department department) {
        this.name = name;
        this.email = email;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
