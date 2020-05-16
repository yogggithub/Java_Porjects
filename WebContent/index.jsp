<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>
	<h2>Welcome, ${user.username }</h2>
	<h3>
		<a href="StuListServlet">Show All Student</a>
	</h3>
	<h3>
		<a href="StuPaginatedListServlet?page=1">Paginated Show Student </a>
	</h3>
</body>
</html>