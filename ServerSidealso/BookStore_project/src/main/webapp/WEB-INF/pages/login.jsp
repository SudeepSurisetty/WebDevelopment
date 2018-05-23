<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles.css">

</head>
<body>
<body>


	<jsp:include page="_header.jsp" />
	<!--<jsp:include page="_menu.jsp" />-->



	<div class="page-title">Login Form</div>
	<div class="imgcontainer">
		 <img src="https://www.w3schools.com/howto/img_avatar.png" alt="Avatar"
			class="avatar">

	</div>

	<div class="login-container">

		<br>
		<!-- /login?error=true -->
		<c:if test="${param.error == 'true'}">
			<div style="color: red; margin: 10px 0px;">

				Login Failed!!!<br /> Reason :
				${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}

			</div>
		</c:if>

		<form method="POST"
			action="${pageContext.request.contextPath}/j_spring_security_check">

			<strong>User Name *</strong> <input type="text" name="userName" size="30" required="required"" 
				placeholder="Enter username" /> 
				<strong>Password *</strong>
			<input type="password" name="password" size="30" required="required" placeholder="Enter password" />

			<input type="submit" value="Login" /> <input type="reset"
				value="Reset" />
				
			<a href="${pageContext.request.contextPath}/forgot-password" style="color:blue;text-align:center;margin: 5px 5px 5px 20px;width: 100%; padding: 10px 15px;
	margin: 8px 0;
	display: block;
	border: 1px solid #ccc;
	box-sizing: border-box;
	text-decoration: none;
	background-color: #299aed;
	color: white;">
			Forgot Password</a>
		


		</form>

		<span class="error-message">${error }</span>

	</div>


	<jsp:include page="_footer.jsp" />

</body>
</body>
</html>