<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Account Creation Success</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles.css">
</head>
<body>

	<jsp:include page="_header.jsp" />

	Email has been sent to your inbox , please click on the link to activate your account !
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	<a href="${contextPath}/login">Click her to login</a>
	
	<jsp:include page="_footer.jsp" />
	
</body>
</html>

	



	