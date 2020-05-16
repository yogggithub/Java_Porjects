<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Student</title>
</head>
<body>
	<form action="AddServlet" method="post">
		<table border="1">
			<tr>
				<td>Student Name</td>
				<td><input type="text" name="stuname"></td>
			</tr>
			<tr>
				<td>Student Age</td>
				<td><input type="text" name="stuage"></td>
			</tr>
			<tr>
				<td>Gender</td>
				<td>
					<input type="radio" name="gender" value="Male">Male
					<input type="radio" name="gender" value="Female">Female 
					<input type="radio" name="gender" value="Other">Other
				</td>
			</tr>
			<tr>
				<td>Phone</td>
				<td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td>Hobby</td>
				<td><input type="checkbox" name="hobby" value="Swimming">Swimming
					<input type="checkbox" name="hobby" value="Football">Football
					<input type="checkbox" name="hobby" value="Basketball">Basketball
					<input type="checkbox" name="hobby" value="Reading">Reading
					<input type="checkbox" name="hobby" value="Other">Other</td>
			</tr>
			<tr>
				<td>Comments</td>
				<td><textarea rows="3" cols="20" name="comments"></textarea></td>
			</tr>
			<tr align="center">
				<td colspan="2"><input type="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
</body>
</html>