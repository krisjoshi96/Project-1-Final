package com.revature.model;





import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import com.fasterxml.jackson.annotation.JsonFormat;




@Entity
@Table(name = "Reimburse_Expenses")
@NamedQueries({

		@NamedQuery(name = "viewExpenses", query = "FROM ReimburseExpenses r"),
		@NamedQuery(name = "findExpenseByStatus", query = "FROM ReimburseExpenses r WHERE r.status = :status"),
		@NamedQuery(name = "findExpenseByEmployeeId", query = "FROM ReimburseExpenses r WHERE r.employeeId = :employeeId ORDER BY r.id DESC ")
})





public class ReimburseExpenses {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "Emp_id")
	private String employeeId;
	@Column(name = "Expense_Amount")
	private double amount;
	@Column(name = "Expense_Reason")
	private String reason;
	@Column(name = "Reimbursement_Status")
	private String status;
	
	
	public ReimburseExpenses() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ReimburseExpenses( String employeeId, double amount, String reason, String status) {
		super();
	
		this.employeeId = employeeId;
		this.amount = amount;
		this.reason = reason;
		this.status = status;
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


	@Override
	public String toString() {
		return "ReimburseExpenses [id=" + id + ", employeeId=" + employeeId + ", amount=" + amount + ", reason="
				+ reason + ", status=" + status + "]";
	}


	
	
	
	
	
	
	
}
