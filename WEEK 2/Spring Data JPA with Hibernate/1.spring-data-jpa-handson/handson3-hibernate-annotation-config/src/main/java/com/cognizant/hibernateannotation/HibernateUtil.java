package com.cognizant.hibernateannotation;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			// Configuration() auto-detects annotated classes declared via
			// <mapping class="..."/> in hibernate.cfg.xml - no separate
			// AnnotationConfiguration class is needed since Hibernate 3.6+.
			return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
		} catch (Throwable ex) {
			throw new ExceptionInInitializerError("SessionFactory creation failed: " + ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}
