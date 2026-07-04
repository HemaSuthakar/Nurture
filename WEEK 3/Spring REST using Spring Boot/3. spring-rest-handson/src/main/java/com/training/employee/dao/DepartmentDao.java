package com.training.employee.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.training.employee.model.Department;

@Repository
public class DepartmentDao {

    private static ArrayList<Department> DEPARTMENT_LIST;

    // Constructor reads the department list bean defined in employee.xml
    // and initializes the static DEPARTMENT_LIST.
    public DepartmentDao(@Qualifier("departmentList") ArrayList<Department> departmentList) {
        DEPARTMENT_LIST = departmentList;
    }

    public ArrayList<Department> getAllDepartments() {
        return DEPARTMENT_LIST;
    }
}
