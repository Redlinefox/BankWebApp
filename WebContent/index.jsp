<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bankai Bank Web Application</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<nav>
		<h1 class="header" >Bankai Bank</h1>
	</nav>
	<div class="index-container">
		<form action="controllerServlet" method="POST">
			<div class="index-wrapper">
				<div class="index-item">
					<label for="loginLabel">Login: </label>
					<input type="text" name="username" size="20">
				</div>
				<div class="index-item">
					<label for="passwordLable">Password: </label>
					<input type="password" name="password" size="20">
				</div>
			</div>
			<div class="login">
				<input type="hidden" name="action" value="login">
				<input type="submit" value="Login">
			</div>
			
		</form>
		<br>
		<br>
		
		<form action="signup.html">
			<input type="submit" value="No Account? Sign Up Here">
		</form>
	</div>
	
</body>
</html>