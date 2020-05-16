package com.stumgnt.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.stumgnt.domain.Student;
import com.stumgnt.service.StuService;
import com.stumgnt.service.impl.StuServiceImpl;

/**
 * Servlet implementation class AddServlet
 */
@WebServlet("/AddServlet")
public class AddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddServlet() {
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
		try {

			request.setCharacterEncoding("UTF-8");

			String stuname = request.getParameter("stuname");
			int stuage = Integer.parseInt(request.getParameter("stuage"));
			String gender = request.getParameter("gender");
			String phone = request.getParameter("phone");
			String hobby = Arrays.toString(request.getParameterValues("hobby"));
			hobby = hobby.substring(1, hobby.length() - 1);

			/*
			 * Traverse String array method
			 * String[] hobbies = request.getParameterValues("hobby");
			 * String hobby = "";
			 * for (int i = 0; i < hobbies.length; i++) {
			 * if (i < hobbies.length -1) {
			 * hobby += hobbies[i] + ", ";
			 * } else {
			 * hobby += hobbies[i];
			 * }
			 * }
			 */
			String comments = request.getParameter("comments");
			Student stu = new Student(stuname, stuage, gender, phone, hobby, comments);

			StuService service = new StuServiceImpl();
			service.insert(stu);

			response.sendRedirect("StuListServlet");
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
