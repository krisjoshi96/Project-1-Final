package com.revature.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import com.revature.model.Expense;
import com.revature.util.DataBaseConnection;

public class ExpenseDAO {
	public static void create(Expense expense) {
      
		try (Session session = DataBaseConnection.getSession()) {
			session.beginTransaction();
			session.save(expense);
			session.getTransaction().commit();

		}
	}

	public static void update(List<Integer> ids, String status) {

		try (Session session = DataBaseConnection.getSession()) {
			session.beginTransaction();

			for (int id : ids) {
				Expense exp = findById(id);
				exp.setStatus(status);
				session.update(exp);
			}

			session.getTransaction().commit();

		}
	}

	public static List<Expense> findByemployeeId(String employeeId) {

		try (Session session = DataBaseConnection.getSession()) {
			session.beginTransaction();
			Query query = session.getNamedQuery("findExpenseByEmployeeId").setParameter("employeeId", employeeId);
			List<Expense> expenses = query.getResultList();

			return expenses;

		}

	}

	public static List<Expense> findExpenseByStatus(String status) {

		try (Session session = DataBaseConnection.getSession()) {
			session.beginTransaction();
			Query query = session.getNamedQuery("findExpenseByStatus").setParameter("status", status);
			List<Expense> expenses = query.getResultList();

			return expenses;

		}

	}

	public static List<Expense> findExpenseByStat(String stat) {

		String sumq = "SELECT employee_id, SUM(amount) as amount FROM expenses GROUP BY employee_id order by SUM(amount) desc";
		String avgq = "SELECT employee_id, ROUND(AVG(amount)) as amount FROM expenses GROUP BY employee_id order by avg(amount) desc";
		String maxq = "SELECT DISTINCT ON (employee_id) id, employee_id , reason, amount, status, timestamp FROM expenses ORDER BY employee_id, amount DESC ";

		try (Session session = DataBaseConnection.getSession()) {
			List<Expense> expenses = new ArrayList<>();
			session.beginTransaction();
			if (stat.equalsIgnoreCase("max")) {
				Query query = session.createNativeQuery(maxq, Expense.class);
				expenses = query.getResultList();
			}

			if (stat.equalsIgnoreCase("sum")) {
				List<Object[]> results = session.createNativeQuery(sumq).getResultList();
				for (Object[] record : results) {

					String employeeId = (String) record[0];
					double amount = (Double) record[1];

					Expense expense = new Expense();
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

					Expense expense = new Expense();
					expense.setEmployeeId(employeeId);
					expense.setAmount(amount);
					expenses.add(expense);
				}
				;
			}

			return expenses;

		}

	}

	public static Expense findById(int id) {

		try (Session session = DataBaseConnection.getSession()) {
			return session.find(Expense.class, id);
		}
	}

}