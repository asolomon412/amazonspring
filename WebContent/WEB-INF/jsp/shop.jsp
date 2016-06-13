<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Shop</title>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>Product ID</th>
				<th>Name</th>
				<th>Description</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Add</th>
			</tr>
		</thead>
		<c:forEach var="product" items="${products}">
			<tr>
				<td>${product.productid}</td>
				<td>${product.name}</td>
				<td>${product.description}</td>
				<td>${product.price}</td>

				<td><input type="number" name="qty"
					onchange="setQty(this, q${product.productid})"
					value="${sessionScope.shoppingcart[product.productid.toString()]}"></td>
				<td><a id="q${product.productid}"
					href="${pageContext.request.contextPath}/addtocart?userid=0&productID=${product.productid}&qty=">Add
						to Cart</a></td>
			</tr>
		</c:forEach>
	</table>
	<a href="checkout">Check Out</a>
	<br>
	<!-- Only show Delete Order link if shopping cart has items - to prevent Session attribute required - not found in session error -->
	<c:if test="${sessionScope.shoppingcart != null}">
		<br>
		<a href="cancelcart">Delete Order</a><br>
	</c:if>
	<br>
	<a href="index.jsp">Home</a>
	<br>
	<form:form action="${pageContext.request.contextPath}/logout"
		method="POST">
		<input type="submit" value="Logout" />
	</form:form>

	<script type="text/javascript">
/*
 * Update qty parameter in add to cart url to value in the quantity input field
 */
function setQty(obj, id){
	//console.log(obj.value);
	//console.log(id.href);
	id.href =  id.href.replace(/qty=.*/, 'qty='.concat(obj.value));
	console.log(id.href);
}
</script>
</body>
</html>