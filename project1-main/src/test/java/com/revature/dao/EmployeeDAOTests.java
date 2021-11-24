package com.revature.dao;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.model.Employee;

public class EmployeeDAOTests {
	
private static	Logger logger = LoggerFactory.getLogger(EmployeeDAOTests.class);

	//@Test
	public void testCreateAndFind() {
		Employee employee = new Employee();
		String id = UUID.randomUUID().toString();
		employee.setId(id);
		employee.setName("Micky");
		employee.setPassword("macky");
		employee.setRole("Manager");
		EmployeeDAO.create(employee);
		logger.info(employee.toString());
		
		Employee employee2 = EmployeeDAO.findById("Micky");
		assertEquals(employee2.getId(), employee.getId());
		assertEquals(employee2.getName(), employee.getName());
		assertEquals(employee2.getRole(), employee.getRole());
		assertEquals(employee2.getPassword(), employee.getPassword());
		logger.info(employee2.toString());
	}
	
}
