package com.stumgnt.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stumgnt.domain.User;
import com.stumgnt.service.UserService;
import com.stumgnt.service.impl.UserServiceImpl;

/**
 * Servlet implementation class StuLogin
 */
@WebServlet("/UserLogin")
public class UserLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLogin() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// encode with utf-8
		request.setCharacterEncoding("UTF-8");

		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");

		UserService service = new UserServiceImpl();
		try {
			if (service.login(username, password)) {
				User user = new User(username, password);
				request.setAttribute("user", user);
				request.getRequestDispatcher("index.jsp").forward(request, response);
			} else {
				response.getWriter().write("Invalid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
