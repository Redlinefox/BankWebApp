<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transactions</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<nav>
		<h1 class="header" >List of Transactions</h1>
	</nav>
	<div class="hero-container" align="center">
		<table border="1">
			<tr>
				<th>Transaction#</th>
				<th>Account1</th>
				<th>Account2</th>
				<th>Type</th>
				<th>Amount</th>
				<th>Timestamp</th>
			</tr>
			<c:forEach var="transaction" items="${transLog}">
				<tr>
					<td>${transaction.trans_id}</td>
					<td>${transaction.account1}</td>
					<td>${transaction.account2}</td>
					<td>${transaction.type}</td>
					<td><fmt:formatNumber type = "currency" value = "${transaction.amount}" /></td>
					<td>${transaction.time}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<button type="button" name="back" onclick="history.back()">back</button>
</body>
</html>