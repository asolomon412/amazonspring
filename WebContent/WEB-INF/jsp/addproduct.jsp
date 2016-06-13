<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product</title>
</head>
<body>
	<h2>Add Product</h2>
	<form:form method="POST" action="${pageContext.request.contextPath}/submitaddproduct">
		<table>
			<tr>
				<td><form:label path="name">Name</form:label></td>
				<td><form:input path="name" /></td>
			</tr>
			<tr>
				<td><form:label path="description">Description</form:label></td>
				<td><form:input path="description" /></td>
			</tr>
			<tr>
				<td><form:label path="price">Price</form:label></td>
				<td><form:input path="price" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add Product" /></td>
			</tr>
		</table>
	</form:form>	
	<br>
	<a href="index.jsp">Home</a>
	<br><br>
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout" />
	</form:form>
</body>
</html>