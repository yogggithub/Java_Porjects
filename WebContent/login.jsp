<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<!-- 
<script type="text/javascript" src="js/jquery-3.5.0.js"></script>

<script>

	$("form").submit(function() {
		var value = this.value;

		$.post("CheckUserNameServlet",{username : $("#username").val()},
			function(data, status) {
				if (data == 0) {
					$("#span1").html("<font color='red'>  User name incorrect</font>");
				} else{
					$.post("UserLoginServlet",{username: $("#username").val(), password:$("#password").val()},
						function(data, status){
							alert($("#password").val());
							if(!$("#password").val()){
								$("#span").html("Incorrect Password");
							}		
						});
				}
			});
	});
</script>
 -->
</head>
<body>
	<h1>Student Management System</h1>
	

	<form action="UserLoginServlet" method="post">
		<table>
			<tr>
				<td>Username</td>
				<td><input type="text" name="username"><span id="span1"></span></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" name="password"><span id="span2"></span></td>
			</tr>
			<tr>
				<td><input type="checkbox" name="autologin">Auto Login</td>
				<td align="center"><input type="submit" value="Login"></td>
			</tr>
		</table>
	</form>
	<a href="register.jsp">Register Now</a>
</body>
</html>