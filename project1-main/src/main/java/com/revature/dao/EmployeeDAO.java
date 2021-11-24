package com.revature.dao;

import org.hibernate.Session;

import com.revature.model.Employee;
import com.revature.util.DataBaseConnection;

public class EmployeeDAO {

	public static Employee findById(String name) {

		try (Session session = DataBaseConnection.getSession()) {
			return session.find(Employee.class, name);
		}

	}

	public static void create(Employee employee) {

		try (Session session = DataBaseConnection.getSession()) {
			session.beginTransaction();
			session.save(employee);
			session.getTransaction().commit();
			
		}

	}
	

}
