<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>

<script type="text/javascript" src="js/jquery-3.5.0.js"></script>

<script>
	$(function() {
		$(".mandatory").after("<span><font color='red'>\t*</font></span>");

		$(".mandatory").blur(function() {
			var value = this.value;

			if ($(this).is("#username")) {
				$("#usernamechecked").empty();
				if (value.length < 6) {
					$("#usernamechecked").html("<font color='red'>  Too short</font>");
					return false;
				} else {
					$.post("CheckUserNameServlet",{username : $("#username").val()},
						function(data, status) {
							if (data == 1) {
								$("#usernamechecked").html("<font color='red'>  User name already exist.</font>");
							} else{
								$("#usernamechecked").html("Valid");
								return true;
							}
						});
				}
			} else if ($(this).is("#password")) {
				$("passwordchecked").empty();
				if (value.length < 6) {
					$("#passwordchecked").html("<font color='red'>  Too short</font>");
					return false;
				} else {
					$("#passwordchecked").html("Valid");
					return true;
				}
			} else {
				$("confirmpasswordchecked").empty();
				if (value != $("#password").val()) {
					$("#confirmpasswordchecked").html("<font color='red'> Password must be identical</font>");
					return false;
				} else {
					return true;
				}
			}
		}).focus(function() {
			$(this).triggerHandler(blur);
		}).keyup(function() {
			$(this).triggerHandler(blur);
		});

		$("form").submit(function() {
			if($(".mandatory").trigger(blur)){
				$.post("UserRegistServlet",{username : $("#username").val(), password:$("#password").val()});
			}
		});
	});
	
</script>
</head>
<body>
	<form action="UserRegistServlet" method="post">
		<table>
			<tr>
				<td>Username</td>
				<td><input type="text" class="mandatory" name="username"
					id="username"> <span id="usernamechecked"></span></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="password" class="mandatory" id="password"
					name="password"><span
					id="passwordchecked"></span></td>
			</tr>
			<tr>
				<td>Confirm Password</td>
				<td><input type="password" class="mandatory"
					id="confirmpassword" name="confirmpassword"> <span
					id="confirmpasswordchecked"></span></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="Register"></td>
			</tr>

		</table>
	</form>
</body>
</html>