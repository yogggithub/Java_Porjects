<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Management System</title>
</head>
<body>
	<h1>Student Management System</h1>
	<c:if test="${not empty user || not empty cookie['autologin'] }">
		<h2>Welcome, 
		<c:if test="${not empty user}">${user.username }</c:if>
		<c:if test="${empty user}">${cookie['autologin'].value }</c:if>
		</h2>
		<h3>
			<a href="StuListServlet">Show All Student</a>
		</h3>
		<h3>
			<a href="StuPaginatedListServlet?page=1">Paginated Show Student </a>
		</h3>
	</c:if>

	<c:if test="${empty user && empty cookie['autologin']}">
		<h3>
			<a href="login.jsp">Please Login</a>
		</h3>
	</c:if>
</body>
</html>