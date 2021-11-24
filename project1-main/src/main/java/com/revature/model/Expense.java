package com.revature.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "expenses")
@NamedQueries({

		@NamedQuery(name = "viewAllExpenses", query = "FROM Expense e"),
		@NamedQuery(name = "findExpenseByStatus", query = "FROM Expense e WHERE e.status = :status"),
		@NamedQuery(name = "findExpenseByEmployeeId", query = "FROM Expense e WHERE e.employeeId = :employeeId ORDER BY e.id DESC ")

})
public class Expense {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(name = "employee_id")
	private String employeeId;
	private double amount;
	private String reason;
	private String status;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a", timezone = "EDT")
	private Timestamp timestamp;

	public Expense() {
		super();
	}

	public Expense(String employeeId, double amount, String reason, String status) {
		super();
		this.employeeId = employeeId;
		this.amount = amount;
		this.reason = reason;
		this.status = status;
		this.timestamp = new Timestamp(System.currentTimeMillis());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Expense [id=" + id + ", employeeId=" + employeeId + ", amount=" + amount + ", reason=" + reason
				+ ", status=" + status + ", timestamp=" + timestamp + "]";
	}

}
