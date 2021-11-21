
package com.revature.main;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.Util.ConnectionClass;

import com.revature.DAO.EmployeeDAO;
import com.revature.DAO.ReimburseExpensesDAO;

import com.revature.model.EmployeeBean;

import com.revature.model.ReimburseExpenses;

import com.revature.model.StatusUpdate;

import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;
 
public class App 
{

	
	private static Logger logger = LoggerFactory.getLogger(App.class);

	private static EmployeeBean employee;
	

	public static void main(String[] args) {

		ConnectionClass.getSessionFactory();
		// Connecting through Javalin
		Javalin app = Javalin.create(config -> {
			config.addStaticFiles("/public", Location.CLASSPATH);
		}).start(7777);
		
		// Root is redirecting to login page
		app.get("/", ctx -> {
			ctx.redirect("login.html");
		});
		
		// Login as Employee or Manager
		app.post("/login", ctx -> {
			String name = ctx.formParam("userId");
			logger.info("User trying to login with userId: "+name);
			logger.debug("XX User trying to login with userId: "+name);
			employee = EmployeeDAO.findById(name);
			if (Objects.isNull(employee)) {
				ctx.redirect("error1.html"); // When employee/manager userId wrong
			} else {
				String password = ctx.formParam("password");
				if (employee.isPasswordMatch(password)) {
					if (employee.getPosition().equalsIgnoreCase("manager")) {
						ctx.redirect("manager.html"); // When user is manager
					} else {
						ctx.redirect("employee.html"); // when user is employee
					}
				} else {

					ctx.redirect("error2.html");   // In case of wrong password
				}
				logger.info("userId: " + name + " password: " + password);
			}
			
//			ctx.html("ok");

		});

		// Submitting new expense
		app.post("/expenses", ctx -> {
			String reason = ctx.formParam("description"); // Getting data from html form
			double amount = Double.valueOf(ctx.formParam("amount"));
			String status = "Pending";
			ReimburseExpenses expense = new ReimburseExpenses( employee.getName(), amount, reason, status);
			ReimburseExpensesDAO.create(expense);

			logger.info("description: " + reason + " Amount: " + amount);
			ctx.redirect("employee.html");

		});
			
		// Updating the status when approved/denied
		app.post("/update", ctx -> {
			StatusUpdate su = ctx.bodyAsClass(StatusUpdate.class);
			ReimburseExpensesDAO.update(su.getIds(), su.getStatus());
			ctx.status(200);

		});
		
		// Getting List of expenses by employee
		app.get("/expenses", ctx -> {
			String employeeId = employee.getName();
			List<ReimburseExpenses> expenses = ReimburseExpensesDAO.findByemployeeId(employeeId);
			ctx.json(expenses);
		});
			
		// Getting employee/manager name
		app.get("/employee", ctx -> {
			ctx.html(employee.getName());
		});
		
		// Retrieving list of expenses by pending/approved/denied status
		app.get("/mexpenses", ctx -> {
			String status = ctx.queryParam("status");
			List<ReimburseExpenses> expenses = ReimburseExpensesDAO.findExpenseByStatus(status);
			ctx.json(expenses);
		});	
		
		// Retrieving list of expenses for 'statistics page'
		app.get("/stats", ctx -> {
			String stat = ctx.queryParam("stat");
			List<ReimburseExpenses> expenses = ReimburseExpensesDAO.findExpenseByStat(stat);
			ctx.json(expenses);
		});	
		
	}

	
	
	
	
	
	

	
}
