package com.cognizant;

public class MainApp {

    public static void main(String[] args) {

        EmployeeDAO dao = new EmployeeDAO();

        // INSERT
        Employee emp = new Employee("John", "David", 45000);

        Integer id = dao.addEmployee(emp);

        System.out.println("Generated ID : " + id);

        // SELECT ALL
        System.out.println("\n===== Employee List =====");

        dao.listEmployees();

        // SELECT BY ID
        System.out.println("\n===== Get Employee =====");

        Employee e = dao.getEmployee(id);

        System.out.println(e);

        // UPDATE
        System.out.println("\n===== Update Employee =====");

        dao.updateEmployee(id, 60000);

        System.out.println(dao.getEmployee(id));

        // DELETE
        System.out.println("\n===== Delete Employee =====");

        dao.deleteEmployee(id);

        System.out.println("\n===== Remaining Employees =====");

        dao.listEmployees();

        HibernateUtil.shutdown();
    }
}