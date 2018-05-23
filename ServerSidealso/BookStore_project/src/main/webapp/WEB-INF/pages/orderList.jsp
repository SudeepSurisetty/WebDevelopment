<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product List</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles.css">



</head>
<body>
	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />

	<fmt:setLocale value="en_US" scope="session" />

	<div class="page-title">Order List</div>

	<div>Total Order Count: ${paginationResult.totalRecords}</div>



	<table border="1" style="width: 100%">
		<tr>
			<th>Order Num</th>
			<th>Order Date</th>
			<th>Customer Name</th>
			<th>Customer Address</th>
			<th>Customer Email</th>
			<th>Amount</th>
			<th>View</th>
		</tr>
		<c:forEach items="${paginationResult.list}" var="orderInfo">
			<tr>
				<td>${orderInfo.orderNum}</td>
				<td><fmt:formatDate value="${orderInfo.orderDate}"
						pattern="dd-MM-yyyy HH:mm" /></td>
				<td>${orderInfo.customerName}</td>
				<td>${orderInfo.customerAddress}</td>
				<td>${orderInfo.customerEmail}</td>
				<td style="color: red;"><fmt:formatNumber
						value="${orderInfo.amount}" type="currency" /></td>
				<td><a
					href="${pageContext.request.contextPath}/order?orderId=${orderInfo.id}">
						View</a></td>
			</tr>
		</c:forEach>
	</table>

	<security:authorize access="hasRole('ROLE_MANAGER')">
		<form:form action="${pageContext.request.contextPath}/saveToXls"
			method="post">
			<input type="hidden" value="${paginationResult.currentPage}"
				name="currPageId" />
			<input type="submit" value="Save To Excel"
				style="border-radius: 15px; padding: 5px 10px; color: #fff" />
		</form:form>
	</security:authorize>

	<c:if test="${paginationResult.totalPages > 1}">
		<div class="page-navigator">
			<c:forEach items="${paginationResult.navigationPages}" var="page">
				<c:if test="${page != -1 }">
					<a href="orderList?page=${page}" class="nav-item">${page}</a>
				</c:if>
				<c:if test="${page == -1 }">
					<span class="nav-item"> ... </span>
				</c:if>
			</c:forEach>

		</div>
	</c:if>




	<jsp:include page="_footer.jsp" />

</body>
</html>