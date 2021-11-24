package com.revature.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee {

	
	@Column(name = "id")
	private String id;
	@Id
	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	
	private String role;

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

	

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", password=" + password + ", role=" + role + "]";
	}
	
	
	public boolean isPasswordMatch(String pass) {
		return password.equals(pass);
	}
	public boolean isRoleMatch(String role) {
		return role.equals(role);
	}
}