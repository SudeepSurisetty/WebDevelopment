<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Enter Customer Information</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<!--  <script>


$(document).ready(function(){
	$(form).submit(function(){
		var fieldValue = $("#phone").val();
		console.log( fieldValue );
		if(isNaN(fieldValue))
			alert("Only numerics allowed");
	});
});
</script>-->
</head>
<body>
	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />

	<div class="page-title">Enter Customer Information</div>

	<form:form method="POST" modelAttribute="customerForm"
		action="${pageContext.request.contextPath}/shoppingCartCustomer">

		<table>
			<tr>
				<td>Name *</td>
				<td><form:input path="name" /></td>
				<td><form:errors path="name" class="error-message" /></td>
			</tr>

			<tr>
				<td>Email *</td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" class="error-message" /></td>
			</tr>

			<tr>
				<td>Phone *</td>
				<td id="phone"><form:input path="phone"  maxlength="10"/></td>
				<td><form:errors path="phone" class="error-message" /></td>
			</tr>

			<tr>
				<td>Address *</td>
				<td><form:input path="address" /></td>
				<td><form:errors path="address" class="error-message" /></td>
			</tr>

			<tr>
				<td>&nbsp;</td>
				<td><input type="submit" value="Submit" /> <input type="reset"
					value="Reset" /></td>
			</tr>
		</table>

	</form:form>


	<jsp:include page="_footer.jsp" />

</body>
</html>