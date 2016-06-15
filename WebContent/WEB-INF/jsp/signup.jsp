<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign Up for an account</title>
</head>
<body>
	<h2>New Usr Signup</h2>
	<form:form method="POST" commandName="command"
		action="${pageContext.request.contextPath}/submitadduser">
		<table>
			<tr>
				<td><form:label path="username">Username</form:label></td>
				<td><form:input path="username" /></td>
			</tr>
			<tr>
				<td><form:label path="password">Password</form:label></td>
				<td><form:password path="password" /></td>
			</tr>
			<tr>
				<td><form:label path="userrole.role">Role</form:label></td>
				<td>BUYER:<form:radiobutton checked="true" path="userrole.role" value="ROLE_BUYER" /><br>
					SELLER:<form:radiobutton path="userrole.role" value="ROLE_SELLER" /><br>
					ADMIN:<form:radiobutton path="userrole.role" value="ROLE_ADMIN" />
				</td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add User" /></td>
			</tr>
		</table>
	</form:form>	
	<br>
	<a href="index.jsp">Home</a>

</body>
</html>