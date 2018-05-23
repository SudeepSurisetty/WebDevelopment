<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Shopping Cart Finalize</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles.css">

</head>
<body>
<div class="header-container">

		<div class="site-name">Online Book Store</div>

		<div class="header-bar">
			<c:if test="${pageContext.request.userPrincipal.name != null}">
        Hello &nbsp;
					${pageContext.request.userPrincipal.name}
         &nbsp;|&nbsp;
           Logout
 		&nbsp;|&nbsp;
        </c:if>
	</div></div>
	

	<div class="container" style="backgorund-image:'../../../resources/tick.jpg'">
		<h3>Thank you for Order</h3>
		Your order number is: ${lastOrderedCart.orderNum}
		
		<a href="OrderConfirmation.pdf">Click Here to Generate PDF Report</a>
		<a href="done">DONE</a>
	</div>


	<jsp:include page="_footer.jsp" />


</body>
</html>