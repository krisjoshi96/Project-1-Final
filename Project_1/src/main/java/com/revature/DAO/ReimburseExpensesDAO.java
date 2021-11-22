package com.revature.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.revature.Util.ConnectionClass;
import com.revature.model.ReimburseExpenses;





public class ReimburseExpensesDAO {
	
	
	public static void create(ReimburseExpenses expense) {
	      
		try (Session session = ConnectionClass.getSession()) {
			session.beginTransaction();
			session.save(expense);
			session.getTransaction().commit();

		}
	}

	public static void update(List<Integer> ids, String status) {

		try (Session session = ConnectionClass.getSession()) {
			session.beginTransaction();

			for (int id : ids) {
				ReimburseExpenses exp = findById(id);
				exp.setStatus(status);
				session.update(exp);
			}

			session.getTransaction().commit();

		}
	}

	public static List<ReimburseExpenses> findByemployeeId(String employeeId) {

		try (Session session = ConnectionClass.getSession()) {
			session.beginTransaction();
			Query query = session.getNamedQuery("findExpenseByEmployeeId").setParameter("employeeId", employeeId);
			List<ReimburseExpenses> expenses = query.getResultList();

			return expenses;

		}

	}

	public static List<ReimburseExpenses> findExpenseByStatus(String status) {

		try (Session session = ConnectionClass.getSession()) {
			session.beginTransaction();
			Query query = session.getNamedQuery("findExpenseByStatus").setParameter("status", status);
			List<ReimburseExpenses> expenses = query.getResultList();

			return expenses;

		}

	}

	public static List<ReimburseExpenses> findExpenseByStat(String stat) {

		String sumq = "SELECT Emp_id, SUM(amount) as Expense_Amount FROM ReimburseExpenses GROUP BY Emp_id order by SUM(amount) desc";
		String avgq = "SELECT Emp_id, ROUND(AVG(amount)) as Expense_Amount FROM ReimburseExpenses GROUP BY Emp_id order by avg(amount) desc";
		String maxq = "SELECT DISTINCT ON (Emp_id) ReimburseId, Emp_id , Expense_Reason, Expense_Amount, Reimbursement_Status, ORDER BY employee_id, Expense_Amount DESC ";

		try (Session session = ConnectionClass.getSession()) {
			List<ReimburseExpenses> expenses = new ArrayList<>();
			session.beginTransaction();
			if (stat.equalsIgnoreCase("max")) {
				Query query = session.createNativeQuery(maxq, ReimburseExpenses.class);
				expenses = query.getResultList();
			}

			if (stat.equalsIgnoreCase("sum")) {
				List<Object[]> results = session.createNativeQuery(sumq).getResultList();
				for (Object[] record : results) {

					String employeeId = (String) record[0];
					double amount = (Double) record[1];

					ReimburseExpenses expense = new ReimburseExpenses();
					expense.setEmployeeId(employeeId);
					expense.setAmount(amount);
					expenses.add(expense);
				}
				;
			}

			if (stat.equalsIgnoreCase("avg")) {
				List<Object[]> results = session.createNativeQuery(avgq).getResultList();
				for (Object[] record : results) {

					String employeeId = (String) record[0];
					double amount = (Double) record[1];

					ReimburseExpenses expense = new ReimburseExpenses();
					expense.setEmployeeId(employeeId);
					expense.setAmount(amount);
					expenses.add(expense);
				}
				;
			}

			return expenses;

		}

	}

	public static ReimburseExpenses findById(int id) {

		try (Session session = ConnectionClass.getSession()) {
			return session.find(ReimburseExpenses.class, id);
		}
	}
	
	
	

}
