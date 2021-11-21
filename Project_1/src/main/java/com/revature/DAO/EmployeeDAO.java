package com.revature.DAO;


import org.hibernate.Session;



import com.revature.model.EmployeeBean;
import com.revature.Util.ConnectionClass;





public class EmployeeDAO {
	
	public static EmployeeBean findById(String name) {

		try (Session session = ConnectionClass.getSession()) {
			return session.find(EmployeeBean.class, name);
		}

	}

	public static void create(EmployeeBean employee) {

		try (Session session = ConnectionClass.getSession()) {
			session.beginTransaction();
			session.save(employee);
			session.getTransaction().commit();
			
		}

	}
	
	
	
	
	
	
	

}
