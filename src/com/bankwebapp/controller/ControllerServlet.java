package com.bankwebapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankwebapp.dao.AccountDAO_Ops;
import com.bankwebapp.dao.UserDAO_Ops;
import com.bankwebapp.model.Account;
import com.bankwebapp.model.Transaction;
import com.bankwebapp.model.User;

/**
 * Servlet implementation class ControllerServlet
 */
@WebServlet("/controllerServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	Account account = new Account();
	UserDAO_Ops userOps = new UserDAO_Ops();
	AccountDAO_Ops accountOps = new AccountDAO_Ops();
	User user = new User();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = (String) request.getParameter("action");
		
		try {
			switch (action) {
			case "login":
				loginUser(request, response);
				break;
			case "signup":
				createNewUser(request, response);
				break;
			case "deposit":
				depositMoney(request, response);
				break;
			case "withdraw":
				withdrawMoney(request, response);
				break;
			case "transfer":
				transferMoney(request, response);
				break;
			case "newAccount":
				newAccount(request, response);
				break;
			case "viewAllAccounts":
				viewAllAccounts(request, response);
				break;
			case "approve":
				approveAccount(request, response);
				break;
			case "transactions":
				transactions(request, response);
				break;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	private void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		session.setAttribute("username", username);
		String password = request.getParameter("password");
		session.setAttribute("password", password);

		int userID = userOps.matchUsernamePass(username, password);
		if (userID == 0){
			out.println("Password does not match user account");
		}
		
		try {
			user = userOps.selectUser(userID);
			session.setAttribute("user", user);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (userID > 0) {
			if (user.getUserType().equals("Customer")) {
				List<Account> listAccount = null;
				try {
					listAccount = accountOps.viewUserAccounts(user.getUserId());
					session.setAttribute("listAccount", listAccount);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				session.setAttribute("user", user);
				response.sendRedirect(request.getContextPath() + "/CustomerPage.jsp");
			}
			else if (user.getUserType().equals("Employee")) {
				session.setAttribute("user", user);
				response.sendRedirect(request.getContextPath() + "/EmployeePage.jsp");
			}
		}
		else {
			RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
			rs.include(request, response);
		}
	}
	
	private void createNewUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String userType = request.getParameter("userType");
		
		User newUser = new User(username, password, firstName, lastName, userType);
		userOps.createUser(newUser);
		System.out.println("New user created");
		response.sendRedirect(request.getContextPath() + "/index.jsp");
	}
	
	private void depositMoney(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		Double money = Double.parseDouble(request.getParameter("depositAmount"));
		int accountID = Integer.parseInt(request.getParameter("account"));
		accountOps.deposit(money, accountID);
		
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		List<Account> listAccount = null;
		try {
			listAccount = accountOps.viewUserAccounts(user.getUserId());
			session.setAttribute("listAccount", listAccount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("user", user);
		response.sendRedirect(request.getContextPath() + "/CustomerPage.jsp");
	}
	
	private void withdrawMoney(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		Double money = Double.parseDouble(request.getParameter("withdrawAmount"));
		int accountID = Integer.parseInt(request.getParameter("account"));
		
		if(accountOps.checkBalance(accountID) > money) {
			accountOps.withdraw(money, accountID);
		}
		else {System.out.println("Error: cannot withdraw more than $" + accountOps.checkBalance(accountID));}
		
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		List<Account> listAccount = null;
		try {
			listAccount = accountOps.viewUserAccounts(user.getUserId());
			session.setAttribute("listAccount", listAccount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("user", user);
		response.sendRedirect(request.getContextPath() + "/CustomerPage.jsp");
	}
	
	private void transferMoney(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		Double money = Double.parseDouble(request.getParameter("transferAmount"));
		int accountID1 = Integer.parseInt(request.getParameter("account1"));
		int accountID2 = Integer.parseInt(request.getParameter("account2"));
		accountOps.transfer(money, accountID1, accountID2);
		
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		List<Account> listAccount = null;
		try {
			listAccount = accountOps.viewUserAccounts(user.getUserId());
			session.setAttribute("listAccount", listAccount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("user", user);
		response.sendRedirect(request.getContextPath() + "/CustomerPage.jsp");
	}

	private void newAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		String accountType = request.getParameter("accountType");
		double startAmount = Double.parseDouble(request.getParameter("startAmount"));
		User user=(User) session.getAttribute("user");
		
		accountOps.createAccount(user.getUserId(), accountType, startAmount);

		List<Account> listAccount = null;
		try {
			listAccount = accountOps.viewUserAccounts(user.getUserId());
			session.setAttribute("listAccount", listAccount);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("user", user);
		response.sendRedirect(request.getContextPath() + "/CustomerPage.jsp");
	}
	
	private void viewAllAccounts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		List<Account> listAllAccounts = null;
		listAllAccounts = accountOps.viewAllAccounts();
		session.setAttribute("listAllAccounts", listAllAccounts);
		response.sendRedirect(request.getContextPath() + "/view-all-accounts.jsp");
	}
	
	private void approveAccount(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		int account = Integer.parseInt(request.getParameter("setApprove"));
		User user=(User) session.getAttribute("user");
		
		accountOps.setApproved(user.getUserId(), account);
		
		List<Account> listAllAccounts = null;
		listAllAccounts = accountOps.viewAllAccounts();
		session.setAttribute("listAllAccounts", listAllAccounts);
		response.sendRedirect(request.getContextPath() + "/view-all-accounts.jsp");
		
	}

	public void transactions(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		
		List<Transaction> transactions = null;
		transactions = accountOps.viewTransactionLog();
		session.setAttribute("transLog", transactions);
		response.sendRedirect(request.getContextPath() + "/transactions.jsp");		
	}

}
