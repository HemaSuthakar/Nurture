package com.cognizant.springlearn.service;

import com.cognizant.springlearn.dao.EmployeeDao;
import com.cognizant.springlearn.exception.EmployeeNotFoundException;
import com.cognizant.springlearn.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDao employeeDao;

    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }

    public Employee getEmployeeById(Long id) throws EmployeeNotFoundException {
        return employeeDao.findById(id);
    }

    public Employee addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException {
        return employeeDao.updateEmployee(employee);
    }

    public void deleteEmployee(Long id) throws EmployeeNotFoundException {
        employeeDao.deleteEmployee(id);
    }
}
