<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Product List</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles.css">
<script>
function ajaxEvent() {

	var xmlHttp;
	try // Firefox, Opera 8.0+, Safari
	{
		xmlHttp = new XMLHttpRequest();
	} catch (e) {
		try // Internet Explorer
		{
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("Your browser does not support AJAX!");
				return false;
			}
		}
	}

	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4) {
			//document.getElementById("productdiv").innerHTML = xmlHttp.responseText;
			document.getElementsByClassName("product-preview-container") = xmlHttp.responseText;
		}
	}
	
	var queryString = document.getElementById("queryString").value;

	xmlHttp.open("POST", "${pageContext.request.contextPath}/ajaxservice.htm?product="+queryString, true);
	xmlHttp.send();
}
</script>

</head>
<body>
	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />

	<fmt:setLocale value="en_US" scope="session" />

	<div class="page-title">Product List</div><br/>
	
	 
	<!-- 04152018 -->
	<!-- 
	Search Products:
	<input type="text" id="queryString" size="30" onkeyup="ajaxEvent()" placeholder="Check if product exists or not by entering name" />
	<br/>
	<div id="productdiv" style="background-color:green">
		<label id="productdivv"> </label>
	</div>
	-->
	<!-- 04152018 -->


	<c:forEach items="${paginationProducts.list}" var="prodInfo">
		<div class="product-preview-container">
			<img class="product-image"
					src="${pageContext.request.contextPath}/productImage?code=${prodInfo.code}" />
			<ul>
				
				<li>Code: ${prodInfo.code}</li>
				<li>Name: ${prodInfo.name}</li>
				<li>Price: <fmt:formatNumber value="${prodInfo.price}"
						type="currency" /></li>
				<li><a
					href="${pageContext.request.contextPath}/buyProduct?code=${prodInfo.code}">
						Buy Now</a></li>
				<!-- For Manager edit Product -->
				<security:authorize access="hasRole('ROLE_MANAGER')">
					<li><a style="color: red;"
						href="${pageContext.request.contextPath}/product?code=${prodInfo.code}">
							Edit Product</a></li>
				</security:authorize>
			</ul>
		</div>

	</c:forEach>
	<br />


	<c:if test="${paginationProducts.totalPages > 1}">
		<div class="page-navigator">
			<c:forEach items="${paginationProducts.navigationPages}" var="page">
				<c:if test="${page != -1 }">
					<a href="productList?page=${page}" class="nav-item">${page}</a>
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