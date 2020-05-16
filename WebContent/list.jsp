<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Management System</title>

<script type="text/javascript">

	// Promp and ask user to confirm to delete record
	function doDelete(stuid){
		if(confirm("Would you going to delete the student?")){
			location.href="DeleteServlet?id=" + stuid;
		}
	}
</script>
</head>

<body>
	<h1>Students List</h1>
	<form action="SearchServlet" method="post">
		<table border="1">
			<tr>
				<td colspan="7">Search by Name: <input type="text"
					name="searchByName">&nbsp;&nbsp; Search by Gender: <select
					name="searchByGender">
						<option value="">Please select one
						<option value="Male">Male
						<option value="Female">Female
						<option value="Other">Other
				</select> &nbsp;&nbsp; <input type="submit" value="Search">&nbsp;&nbsp;
				</td>
				<td colspan="2" align="center"><a href="add.jsp">Add
						Student</a></td>
			</tr>
			<tr align="center">
				<td>ID</td>
				<td>Name</td>
				<td>Age</td>
				<td>Gender</td>
				<td>Phone</td>
				<td>Hobby</td>
				<td>Comments</td>
				<td colspan="2">Action</td>
			</tr>
			<c:forEach items="${list }" var="stu">
				<tr align="center">
					<td>${stu.stuid }</td>
					<td>${stu.stuname }</td>
					<td>${stu.stuage }</td>
					<td>${stu.gender }</td>
					<td>${stu.phone }</td>
					<td>${stu.hobby }</td>
					<td>${stu.comments }</td>
					<td><a href="EditServlet?id=${stu.stuid}">Update</a></td>
					<td><a href="#" onclick="doDelete(${stu.stuid})">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</form>
</body>
</html>


