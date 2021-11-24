package com.revature.dao;

import static org.junit.Assert.assertEquals;

import java.util.UUID;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.model.Expense;

public class ExpenseDaoTests {
	
	private static	Logger logger = LoggerFactory.getLogger(ExpenseDaoTests.class);

	//@Test
	public void testCreate() {
		Expense expense = new Expense("Krishna",20.56, "Food", "Pending");
		
		ExpenseDAO.create(expense);
		logger.info(expense.toString());
		
		
	}
	

}
