<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Deposit</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<div class="hero-container">
		<h1>Deposit Form</h1>
		<form action="controllerServlet" method="POST">
			<label id="account">Account#: </label>
			<input type="number" name="account" size="20" value="<%= request.getParameter("account")%>">
			<br>
			<label id="amount">Amount: </label>
			<input type="number" name="depositAmount" min="0.00" value ="0.00" step="0.01">
			<br>
			<input type="hidden" name="action" value="deposit">
			<input type="submit" value="Confirm">
		</form>
	</div>
	<button type="button" name="back" onclick="history.back()">Back</button>
</body>
</html>