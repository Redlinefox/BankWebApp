<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<meta http-equiv="Cache-Control" content="no-cache, no-store, must-revalidate">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Expires" content="0">
	<title>Customer Page</title>
	<link rel="stylesheet" href="style.css">
</head>
<body>
	<nav>
		<h1 class="header" >Bankai Bank</h1>
	</nav>
	<div class="index-container" align="center">
	<h1>Hello <c:out value="${user.firstName}"/></h1>
		<table border="1">
			<tr>
				<th>Account#</th>
				<th>Type</th>
				<th>Balance</th>
				<th>Last Update</th>
				<th>Action</th>
			</tr>
			<c:forEach var="account" items="${listAccount}">
				<tr>
					<td><c:out value="${account.accountID}"/></td>
					<td><c:out value="${account.accountType}"/></td>
					<td><fmt:formatNumber type = "currency" value = "${account.balance}" /></td>
					<td><c:out value="${account.last_update}"/></td>
					<td>
						<a href="deposit.jsp?account=${account.accountID}">Deposit</a>
						<a href="withdraw.jsp?account=${account.accountID}">Withdraw</a>
						<a href="transfer.jsp?account=${account.accountID}">Transfer</a>
					</td>
				</tr>
			</c:forEach>
			<tr>
				<td colspan="5">
					<form action="new-account.html">
						<input type="submit" value="+ Add Account">
					</form>
				</td>
			</tr>
			
		</table>
	</div>
	<form action="logout" method="get">
    	<input type="submit" value="Logout"/>
	</form>
</body>
</html>