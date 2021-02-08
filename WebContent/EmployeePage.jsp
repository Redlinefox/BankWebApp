<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Page</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<nav>
		<h1 class="header">Employee Page</h1>
	</nav>
	<div class="hero-container" align="center">
		<h1>
			Hello
			<c:out value="${user.firstName}" />
		</h1>
		<br>
		<form action="controllerServlet" method="post">
			<input type="hidden" name="action" value="viewAllAccounts"> <input
				type="submit" value="View All Accounts">
		</form>
		<br>
		<form action="controllerServlet" method="post">
			<input type="hidden" name="action" value="transactions"> <input
				type="submit" value="View Transactions">
		</form>
		<br>
		<form action="logout" method="get">
			<input type="submit" value="Logout" />
		</form>
	</div>
</body>
</html>