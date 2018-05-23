<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Account Info</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles.css">

</head>
<body>

	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />

	<div class="page-title">Account Info</div>

	<div class="account-container">


		<ul>
			<li><strong>User Name: </strong>${pageContext.request.userPrincipal.name}</li>
			<li><strong>Role: </strong>
				<ul>
					&nbsp<c:forEach items="${userDetails.authorities}" var="auth">
						<li>${auth.authority }</li>
					</c:forEach>
				</ul>
			</li>
		</ul>
	</div>


	<jsp:include page="_footer.jsp" />
</body>
</html>