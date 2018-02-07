<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accounts</title>
</head>
<body>
<H1>Bank Account Report</H1>
<a href="createAccount">Create Account</a>
<br>
<br>
<c:forEach var="account" items="${accountList}">
	<hr>
	<a href="deposit/${account.accountNumber}">Deposit | </a>
	
	<a href="withdraw/${account.accountNumber}">Withdraw | </a>
	
	<a href="depositEuros/${account.accountNumber}">Deposit Euro | </a>
	
	<a href="withdrawEuros/${account.accountNumber}">Withdraw Euro | </a>
	
	<a href="transferFund/fromAccountNumber/${account.accountNumber}">Transfer Funds</a>
	<table border= "1px, solid, black">
		<caption>Statement for Account: ${account.accountNumber}</caption>
		<caption>Account Holder: ${account.customer.name}</caption>
		
		<tr>
			<th>Date</th>
			<th>Description</th>
			<th>Amount</th>
		</tr>
			<c:forEach var="entry" items="${account.entryList}">
		<tr>
			<td align="left">${entry.date}</td>
			<td align="center">${entry.description}</td>
			<td align="right">${entry.amount}</td>
		</tr>
				</c:forEach>
		<tr>
			<td></td>
			<td align="center">Current Balance: </td>
			<td align="right">${account.getBalance()}</td>
		</tr>
		<br>
		<br>
		
		
	</table>
	</c:forEach>
	<br>
	<br>
	<hr>
</body>
</html>