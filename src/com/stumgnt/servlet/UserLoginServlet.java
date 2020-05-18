package com.stumgnt.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stumgnt.domain.User;
import com.stumgnt.service.UserService;
import com.stumgnt.service.impl.UserServiceImpl;

/**
 * Servlet implementation class StuLogin
 */
@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserLoginServlet() {
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

		try {
			String username = (String) request.getParameter("username");
			String password = (String) request.getParameter("password");
			String autologin = request.getParameter("autologin");

			UserService service = new UserServiceImpl();
			User user = service.login(username, password);
			if (user != null) {
				if (autologin != null) {
					Cookie cookie = new Cookie("autologin", username);
					cookie.setMaxAge(60 * 10);	// expire after 10 mins
					cookie.setPath(request.getContextPath());
					response.addCookie(cookie);
				}
				request.getSession().setAttribute("user", user);
				response.sendRedirect("index.jsp");
			} else {
				request.getSession().setAttribute("failure", "Password incorrect");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
