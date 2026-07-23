package com.cognizant.hibernateannotation;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hands on 3 - Hibernate Annotation Config implementation walk through.
 *
 * Same CRUD walkthrough as Hands on 2, but the Employee class is mapped
 * with @Entity / @Table / @Id / @GeneratedValue / @Column instead of an
 * Employee.hbm.xml file - compare the two side by side.
 *
 * Requires MySQL running locally with schema "ormlearn" (see main
 * orm-learn project's README) - hibernate.hbm2ddl.auto=update will create
 * the EMPLOYEE table automatically on first run.
 */
public class EmployeeAnnotationDemo {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();

		int newEmployeeId = addEmployee(factory, "Arjun", "Rao", 55000);
		System.out.println("Inserted employee id=" + newEmployeeId);

		listEmployees(factory);

		Employee fetched = getEmployee(factory, newEmployeeId);
		System.out.println("Fetched: " + fetched);

		updateEmployeeSalary(factory, newEmployeeId, 70000);
		System.out.println("Updated: " + getEmployee(factory, newEmployeeId));

		deleteEmployee(factory, newEmployeeId);
		System.out.println("Deleted employee id=" + newEmployeeId);

		listEmployees(factory);

		factory.close();
	}

	public static int addEmployee(SessionFactory factory, String firstName, String lastName, int salary) {
		Session session = factory.openSession();
		Transaction tx = null;
		int employeeId = 0;
		try {
			tx = session.beginTransaction();
			Employee employee = new Employee(firstName, lastName, salary);
			employeeId = (int) session.save(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return employeeId;
	}

	@SuppressWarnings("unchecked")
	public static List<Employee> listEmployees(SessionFactory factory) {
		Session session = factory.openSession();
		List<Employee> employees = null;
		try {
			employees = session.createQuery("FROM Employee").list();
			System.out.println("All employees: " + employees);
		} finally {
			session.close();
		}
		return employees;
	}

	public static Employee getEmployee(SessionFactory factory, int id) {
		Session session = factory.openSession();
		try {
			return session.get(Employee.class, id);
		} finally {
			session.close();
		}
	}

	public static void updateEmployeeSalary(SessionFactory factory, int id, int newSalary) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Employee employee = session.get(Employee.class, id);
			employee.setSalary(newSalary);
			session.update(employee);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}

	public static void deleteEmployee(SessionFactory factory, int id) {
		Session session = factory.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Employee employee = session.get(Employee.class, id);
			if (employee != null) {
				session.delete(employee);
			}
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
}
