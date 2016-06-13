<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Rate Seller</title>
</head>
<body>
	<h2>Rate this seller</h2>
	<form:form method="POST" action="${pageContext.request.contextPath}/submitsellerrating">
		<form:label path="username">Username</form:label>
		<form:input path="username" /><br>	
			
		<form:label path="userid">Userid</form:label>
		<form:input path="userid" /><br>
		
		<form:label path="rating">Rating</form:label>
		<form:select path="rating">
			<form:option value="0">0</form:option>
			<form:option value="1">1</form:option>
			<form:option value="2">2</form:option>
			<form:option value="3">3</form:option>
			<form:option value="4">4</form:option>
		</form:select>
		<br>
		<input type="submit" value="Rate" />
	</form:form><br>
	<a href="index.jsp">Home</a>
</body>
</html>