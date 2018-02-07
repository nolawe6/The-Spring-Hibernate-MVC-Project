<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Transfer Fund</title>
</head>
<body>
	<form action="../../transferFundPost" method="post">
		<input type="hidden" name="fromAccountNumber" value="${fromAccountNumber}"/>
		Transfer to this Account Number: 
		<input type="text"  name="toAccountNumber" value="${toAccountNumber}" />
		<br>
		<br>
		Transfer Amount
		<input type="text" name="transferAmount" />
		<br>
		<br>
		Description
		<input type="text" name="description"/>
		<br>
		<br>
		<input type="submit" value="Submit"/>
	</form>
</body>
</html>