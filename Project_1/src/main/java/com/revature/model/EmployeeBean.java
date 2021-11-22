package com.revature.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="employee_table")


public class EmployeeBean {

	@Column(name="Emp_Id")
	private String id;
	@Id
	

	@Column(name="User_Name")
	private String name;
	

	@Column(name="Password")
	private String password;
	
	
	private String position;


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}



	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}




	@Override
	public String toString() {
		return "EmployeeBean [id=" + id + ", name=" + name + ", password=" + password + ", position=" + position + "]";
	}


	public boolean isPasswordMatch(String match) {
		return password.equals(match);
	}
	public boolean isRoleMatch(String position) {
		return position.equals(position);
	}
	
	
	
	
	

}
