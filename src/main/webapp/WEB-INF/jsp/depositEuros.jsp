<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Deposit Euros</title>
</head>
<body>
	<form action="../depositEurosPost" method="post">
		<input type="hidden" name="accountNumber" value="${accountNumber}"/>
		<br>
		<br>
		Euro Deposit Amount:
		<input type="text" name="depositAmount"/>
		<br>
		<br>
		<input type="submit" value="Submit"/>
	</form>
</body>
</html>