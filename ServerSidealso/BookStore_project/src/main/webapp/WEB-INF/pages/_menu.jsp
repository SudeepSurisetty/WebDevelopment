<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>



	<div class="menu-container">
		<a href="${pageContext.request.contextPath}/" class="active">Home</a> | 
		<security:authorize
			access="hasAnyRole('ROLE_MANAGER')">
		<a
			href="${pageContext.request.contextPath}/productList"> Product
			List </a> 
			| </security:authorize>
			<security:authorize
			access="hasAnyRole('ROLE_EMPLOYEE')">
			<a href="${pageContext.request.contextPath}/productsList"> Search Product
			List </a> 
			|   </security:authorize>
			<a href="${pageContext.request.contextPath}/shoppingCart">
			My Cart </a> |
		
   <security:authorize
			access="hasAnyRole('ROLE_MANAGER','ROLE_EMPLOYEE')">
			<a href="${pageContext.request.contextPath}/ordersList"> Order
				List </a>
     |
   </security:authorize>
   <security:authorize
			access="hasAnyRole('ROLE_MANAGER')">
			<a href="${pageContext.request.contextPath}/orderList"> Save Order
				List </a>
     |
   </security:authorize>

		<security:authorize access="hasRole('ROLE_MANAGER')">
			<a href="${pageContext.request.contextPath}/product"> Create
				Product </a>
         |
   </security:authorize>
	
	</div>
</body>
</html>