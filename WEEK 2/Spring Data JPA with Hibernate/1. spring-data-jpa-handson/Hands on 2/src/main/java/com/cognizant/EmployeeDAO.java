package com.cognizant;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeeDAO {

    // INSERT
    public Integer addEmployee(Employee employee) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = null;

        Integer employeeID = null;

        try {

            tx = session.beginTransaction();

            employeeID = (Integer) session.save(employee);

            tx.commit();

            System.out.println("Employee Added Successfully.");

        } catch (Exception e) {

            if (tx != null)
                tx.rollback();

            e.printStackTrace();

        } finally {

            session.close();

        }

        return employeeID;
    }

    // SELECT ALL
    public void listEmployees() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        List<Employee> employees = session.createQuery("FROM Employee", Employee.class).list();

        for (Employee emp : employees) {

            System.out.println(emp);

        }

        session.close();
    }

    // SELECT BY ID
    public Employee getEmployee(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Employee employee = session.get(Employee.class, id);

        session.close();

        return employee;
    }

    // UPDATE
    public void updateEmployee(int id, int salary) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            Employee emp = session.get(Employee.class, id);

            if (emp != null) {

                emp.setSalary(salary);

                session.update(emp);

                System.out.println("Employee Updated.");

            }

            tx.commit();

        } catch (Exception e) {

            if (tx != null)
                tx.rollback();

            e.printStackTrace();

        } finally {

            session.close();

        }
    }

    // DELETE
    public void deleteEmployee(int id) {

        Session session = HibernateUtil.getSessionFactory().openSession();

        Transaction tx = null;

        try {

            tx = session.beginTransaction();

            Employee emp = session.get(Employee.class, id);

            if (emp != null) {

                session.delete(emp);

                System.out.println("Employee Deleted.");

            }

            tx.commit();

        } catch (Exception e) {

            if (tx != null)
                tx.rollback();

            e.printStackTrace();

        } finally {

            session.close();

        }
    }
}