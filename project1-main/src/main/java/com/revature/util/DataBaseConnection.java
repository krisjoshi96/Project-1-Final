package com.revature.util;

import java.util.Objects;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.revature.model.Employee;
import com.revature.model.Expense;

public class DataBaseConnection {
	
	private static SessionFactory sessionFactoryObj;

	public static SessionFactory getSessionFactory() {

		if (Objects.nonNull(sessionFactoryObj)) {
			return sessionFactoryObj;
		}
		// Creating Configuration Instance & Passing Hibernate Configuration File
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");
		configObj.addAnnotatedClass(Employee.class);
		configObj.addAnnotatedClass(Expense.class);

		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();

		// Creating Hibernate SessionFactory Instance
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;
	}
	
	public static Session getSession() {
		return getSessionFactory().openSession();
	}
}
