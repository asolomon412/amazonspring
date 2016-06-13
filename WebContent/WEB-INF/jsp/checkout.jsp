<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Check Out</title>
</head>
<body>
	<h1>${message}</h1>
	<h1>Here is your receipt</h1>
	<!-- Do not display table if shopping cart is empty -->
	<c:choose>
		<c:when test="${sessionScope.shoppingcart != null}">
			<table border="1">
				<thead>
					<tr>
						<th>Product ID</th>
						<th>Name</th>
						<th>Description</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Total</th>
					</tr>
				</thead>

				<c:forEach var="product" items="${products}">
					<c:set var="itemtotal"
						value="${product.price * sessionScope.shoppingcart[product.productid.toString()]}" />
					<c:set var="ordertotal" value="${ordertotal + itemtotal}" />
					<tr>
						<td>${product.productid}</td>
						<td>${product.name}</td>
						<td>${product.description}</td>
						<td>${product.price}</td>
						<td><input type="number" name="qty"
							onchange="setQty(this, q${product.productid})"
							value="${sessionScope.shoppingcart[product.productid.toString()]}"></td>
						<td>${itemtotal}</td>
					</tr>
				</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<th>Order Total:</th>
					<th>${ordertotal}</th>
				</tr>
			</table>
			<a href="cancelcart">Delete Order</a><br>
			<a href="index.jsp">Home</a><br>
			<a href="showproducts">Continue Shopping</a>
		</c:when>
		<c:otherwise>
			<a href="index.jsp">Home</a>
			<br>
			<a href="showproducts">Continue Shopping</a>
		</c:otherwise>
	</c:choose>
	<br>
	<form:form
		action="${pageContext.request.contextPath}/logout" method="POST">
		<input type="submit" value="Logout" />
	</form:form>
</body>
</html>