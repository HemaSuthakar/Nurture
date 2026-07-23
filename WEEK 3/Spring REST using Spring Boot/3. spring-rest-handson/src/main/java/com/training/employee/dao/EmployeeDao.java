package com.training.employee.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.training.employee.model.Employee;

@Repository
public class EmployeeDao {

    private static ArrayList<Employee> EMPLOYEE_LIST;

    public EmployeeDao(@Qualifier("employeeList") ArrayList<Employee> employeeList) {
        EMPLOYEE_LIST = employeeList;
        System.out.println("Employee count = " + EMPLOYEE_LIST.size());
    }

    public ArrayList<Employee> getAllEmployees() {
        return EMPLOYEE_LIST;
    }
}