<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/styles.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.10.12/css/jquery.dataTables.min.css">
<script
	src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>


<script>
	window.contextRoot = '${contextRoot}';

	$(document)
			.ready(
					function() {
						var dat = eval('${JSONProductdata}');
						var table = $('#productsTable')
								.DataTable(
										{
											"lengthMenu" : [
													[ 3, 5, 10, -1 ],
													[ '3 Records', '5 Records',
															'10 Records', 'ALL' ] ],
											"pageLength" : 3,
											"aaData" : dat,
											"aoColumns" : [
													{
														data : 'code',
														bSortable : false,
														mRender : function(
																data, type, row) {
															var str = '';
															str += '<img class=\"product-image\" src="'
																	+ window.contextRoot
																	+ '/productImage?code='
																	+ data
																	+ '"/>';
															return str;
														}
													},
													{
														data : 'code1'
													},
													{
														data : 'name'
													},
													{
														data : 'price',
														mRender : function(
																data, type, row) {
															return '$ ' + data;
														}
													},
													{
														data : 'buyCode',
														bSortable : false,
														mRender : function(
																data, type, row) {
															var str = '';
															str += '<a href="'
																	+ window.contextRoot
																	+ '/buyProduct?code='
																	+ data
																	+ '">Buy</a>';
															
															return str;
														}
													}, 
													
													/*{
														data : 'editCode',
														bSortable : false,
														mRender : function(
																data, type, row) {
															var str = '';
															if(userRole == 'ROLE_MANAGER'){
															str += '<a href="'
																	+ window.contextRoot
																	+ '/product?code='
																	+ data
																	+ '">Buy</a>';
															}
															return str;
														
													}
													}*/
										
													]
										});

					});
</script>
</head>
<body>
	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />


	<h1>Products List</h1>
	<table id="productsTable" class="table display">

		<!-- Header Table -->
		<thead>
			<tr>
				<th></th>
				<th>Code</th>
				<th>Name</th>
				<th>Price</th>
				<th></th>
			</tr>
		</thead>
		<!-- Footer Table -->
		<tfoot>
			<tr>
				<th></th>
				<th>Code</th>
				<th>Name</th>
				<th>Price</th>
				<th></th>
			</tr>
		</tfoot>
	</table>

	<jsp:include page="_footer.jsp" />
</body>
</html>