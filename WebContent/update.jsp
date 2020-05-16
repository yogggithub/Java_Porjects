<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Student Information</title>
</head>
<body>
	<form action="UpdateServlet" method="post">
		<input type="hidden" name="stuid" value="${stu.stuid }">
		<table border="1">
			<tr>
				<td>Student Name</td>
				<td><input type="text" name="stuname" value="${stu.stuname }"></td>
			</tr>
			<tr>
				<td>Student Age</td>
				<td><input type="text" name="stuage" value="${stu.stuage }"></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td>
					<input type="radio" name="gender" value="Male"
							<c:if test="${stu.gender == 'Male'}">checked</c:if>>Male
					<input type="radio" name="gender" value="Female"
							<c:if test="${stu.gender == 'Female'}">checked</c:if>>Female
					<input type="radio" name="gender" value="Other"
							<c:if test="${stu.gender == 'Other'}">checked</c:if>>Other</td>
			</tr>
			<tr>
				<td>Phone</td>
				<td><input type="text" name="phone" value="${stu.phone}"></td>
			</tr>
			<tr>
				<td>Hobby</td>
				<td>
					<input type="checkbox" name="hobby" value="Swimming"
							<c:if test="${fn:contains(stu.hobby, 'Swimming') }">checked</c:if>>Swimming
					<input type="checkbox" name="hobby" value="Football"
							<c:if test="${fn:contains(stu.hobby, 'Football') }">checked</c:if>>Football
					<input type="checkbox" name="hobby" value="Basketball"
							<c:if test="${fn:contains(stu.hobby, 'Basketball') }">checked</c:if>>Basketball
					<input type="checkbox" name="hobby" value="Reading"
							<c:if test="${fn:contains(stu.hobby, 'Reading') }">checked</c:if>>Reading
					<input type="checkbox" name="hobby" value="Other"
							<c:if test="${fn:contains(stu.hobby, 'Other') }">checked</c:if>>Other
				</td>
			</tr>
			<tr>
				<td>Comments</td>
				<td><textarea rows="3" cols="20" name="comments">${stu.comments }</textarea></td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
</body>
</html>