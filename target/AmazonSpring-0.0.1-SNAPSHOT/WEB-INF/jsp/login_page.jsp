<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title>Login Form | www.beingjavaguys.com</title>
</head>
<body>
 <center>
  <br /> <br /> <br />
  <h2>Login Here as a Seller or Buyer</h2>
  <br />
  <div style="text-align: center; padding: 30px;border: 1px solid green;width: 250px;">
  <c:url var="loginUrl" value="/login" />  
   <form method="POST" action="${loginUrl}">
    <table>
     <tr>
      <td colspan="2" style="color: red">${message}</td>

     </tr>
     <tr>
      <td>User Name:</td>
      <td><input type="text" name="username" />
      </td>
     </tr>
     <tr>
      <td>Password:</td>
      <td><input type="password" name="password" />
      </td>
     </tr>
     <tr>
      <td> </td>
      <td><input type="submit" value="Login" />
      </td>

     </tr>
    </table>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
   </form>
   <br>
   <a href="index.jsp">Home</a><br>
   	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout" />
	</form:form>
   <h1><a href="adduser">Sign Up</a></h1>
  </div>
 </center>
</body>
</html>