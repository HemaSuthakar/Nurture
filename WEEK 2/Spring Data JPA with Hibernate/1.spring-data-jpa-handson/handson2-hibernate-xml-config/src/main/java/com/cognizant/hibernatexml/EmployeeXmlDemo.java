package com.cognizant.hibernatexml;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hands on 2 - Hibernate XML Config implementation walk through.
 *
 * Demonstrates, end to end, the core Hibernate objects called out in the
 * hands-on doc: SessionFactory, Session, Transaction, beginTransaction(),
 * commit(), rollback(), session.save(), session.createQuery().list(),
 * session.get(), session.delete().
 *
 * Requires MySQL running locally with schema "ormlearn" (see main
 * orm-learn project's README) - hibernate.hbm2ddl.auto=update will create
 * the EMPLOYEE table automatically on first run.
 */
public class EmployeeXmlDemo {

	public static void main(String[] args) {
		SessionFactory factory = HibernateUtil.getSessionFactory();

		Integer newEmployeeId = addEmployee(factory, "Zara", "Khan", 50000);
		System.out.println("Inserted employee id=" + newEmployeeId);

		listEmployees(factory);

		Employee fetched = getEmployee(factory, newEmployeeId);
		System.out.println("Fetched: " + fetched);

		updateEmployeeSalary(factory, newEmployeeId, 65000);
		System.out.println("Updated: " + getEmployee(factory, newEmployeeId));

		deleteEmployee(factory, newEmployeeId);
		System.out.println("Deleted employee id=" + newEmployeeId);

		listEmployees(factory);

		factory.close();
	}

	/** session.save() inside a transaction, with commit/rollback handling. */
	public static Integer addEmployee(SessionFactory factory, String firstName, String lastName, int salary) {
		Session session = factory.openSession();
		Transaction tx = null;
		Integer employeeId = null;
		try {
			tx = session.beginTransaction();
			Employee employee = new Employee(firstName, lastName, salary);
			employeeId = (Integer) session.save(employee);
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

	/** session.createQuery().list() */
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

	/** session.get() */
	public static Employee getEmployee(SessionFactory factory, int id) {
		Session session = factory.openSession();
		try {
			return session.get(Employee.class, id);
		} finally {
			session.close();
		}
	}

	/** Load, mutate, save within a transaction (update). */
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

	/** session.delete() */
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
