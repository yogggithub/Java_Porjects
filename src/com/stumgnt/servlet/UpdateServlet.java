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
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
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
			int stuid = Integer.parseInt(request.getParameter("stuid"));
			String stuname = request.getParameter("stuname");
			int stuage = Integer.parseInt(request.getParameter("stuage"));
			String gender = request.getParameter("gender");
			String phone = request.getParameter("phone");
			String hobby = Arrays.toString(request.getParameterValues("hobby"));
			hobby = hobby.substring(1, hobby.length() - 1);
			String comments = request.getParameter("comments");
			Student stu = new Student(stuid, stuname, stuage, gender, phone, hobby, comments);

			StuService service = new StuServiceImpl();
			service.update(stu);
			
			response.sendRedirect("StuListServlet");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
