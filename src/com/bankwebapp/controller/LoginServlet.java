package com.bankwebapp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bankwebapp.dao.UserDAO_Ops;
import com.bankwebapp.model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	UserDAO_Ops uDAO = new UserDAO_Ops();
	User user = new User();
	public LoginServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String username = request.getParameter("username");
		session.setAttribute("username", username);
		String password = request.getParameter("password");
		session.setAttribute("password", password);

		int userID = uDAO.matchUsernamePass(username, password);
		try {
			user = uDAO.selectUser(userID);
			session.setAttribute("user", user);
			//session.setAttribute("userType", user.getUserType());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (userID > 0) {
			RequestDispatcher rd=request.getRequestDispatcher("userController");
			rd.forward(request, response);
		}
		else {
			out.println("Username or Password incorrect");
			RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
			rs.include(request, response);
		}


	}

}
