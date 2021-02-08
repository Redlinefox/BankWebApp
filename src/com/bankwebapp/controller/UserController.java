package com.bankwebapp.controller;

import java.io.IOException;
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
import com.bankwebapp.model.Account;
import com.bankwebapp.model.User;

/**
 * Servlet implementation class UserController
 */
@WebServlet("/userController")
public class UserController extends HttpServlet {
	AccountDAO_Ops AD_Ops = new AccountDAO_Ops();
	User user = new User();
	Account account = new Account();
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		
		if (user.getUserType().equals("Customer")) {
			List<Account> listAccount = null;
			try {
				listAccount = AD_Ops.viewUserAccounts(user.getUserId());
				session.setAttribute("listAccount", listAccount);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			session.setAttribute("user", user);
			
			response.sendRedirect(request.getContextPath() + "/CustomerPage.jsp");
			
//			RequestDispatcher rd=request.getRequestDispatcher("/CustomerPage.jsp");
//			rd.forward(request, response);
		}
		else if (user.getUserType().equals("Employee")) {
			session.setAttribute("user", user);
			RequestDispatcher rd=request.getRequestDispatcher("/EmployeePage.jsp");
			rd.forward(request, response);
		}
	}
}
