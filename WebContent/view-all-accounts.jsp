<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View All Accounts</title>
<link rel="stylesheet" href="style.css">
</head>
<body>
	<nav>
		<h1 class="header" >List of Accounts</h1>
	</nav>
	<div class="hero-container" align="center">
		<table border="1">
			<tr>
				<th>Account#</th>
				<th>Type</th>
				<th>Balance</th>
				<th>Owner</th>
				<th>Approved</th>
				<th>Action</th>
			</tr>
			<c:forEach var="account" items="${listAllAccounts}">
				<tr>
					<td><c:out value="${account.accountID}"/></td>
					<td><c:out value="${account.accountType}"/></td>
					<td><fmt:formatNumber type = "currency" value = "${account.balance}" /></td>
					<td><c:out value="${account.fullName}"/></td>
					<td><c:out value="${account.approved}"/></td>
					<td>
						<c:choose>
							<c:when test="${account.approved=='false'}">
								<form action="controllerServlet" method="POST">
									<input type="hidden" name="action" value="approve">
									<input type="hidden" name="setApprove" value="${account.accountID}">
									<input type="submit" value="Approve"/>
								</form>
							</c:when>
							<c:otherwise></c:otherwise>
						</c:choose>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<button type="button" name="back" onclick="history.back()">Back</button>
</body>
</html>