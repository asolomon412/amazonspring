<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Sellers</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>User Name</th>
				<th>User ID</th>
				<th>Role</th>
				<th>Avg. Rating</th>	
				<th>Rate</th>				
			</tr>
		</thead>
		<c:forEach var="sellers" items="${userlist}">
			<tr>
				<td>${sellers.username}</td>
				<td>${sellers.userid}</td>
				<td>${sellers.userrole.role}</td>
				<td>${sellers.userrole.rating}</td>
				<td><a href="${pageContext.request.contextPath}/rateseller?userid=${sellers.userid}&username=${sellers.username}">Rate This Seller</a></td>											
			</tr>
		</c:forEach>
	</table><br>
	<a href="index.jsp">Home</a>
</body>
</html>