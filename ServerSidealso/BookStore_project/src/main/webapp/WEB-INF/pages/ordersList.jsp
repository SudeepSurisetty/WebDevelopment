<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
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
<script src="${pageContext.request.contextPath}/datatable.js"></script>

<!-- <script type="text/javascript">
/* var transform = {'<>':'li','html':[
    {'<>':'span','html':'${orderNum} ${amount} ${customerName} ${customerAddress} ${customerEmail} ${customerPhone}'}
]}; */

$(document).ready( function () {
	console.log("inside js");
	var arr = ${JSONdata};
	console.log(arr[0])
	//{orderNum: 16, amount: 120, customerName: "trial1", customerAddress: "NZ", customerEmail: "trial@rediff.com", …}
	var k = $("#ordersTable").append("<tbody>");

	console.log(k)
	for(var i=0; i<arr.length;i++){
		k.append("<tr>");
		k.append("<td>date<td>");
		k.append("<td>"+arr[i].orderNum+"<td>");
		k.append("<td>"+arr[i].customerName+"<td>");
		k.append("<td>"+arr[i].customerAddress+"<td>");
		k.append("<td>"+arr[i].customerEmail+"<td>");
		k.append("<td>"+arr[i].amount+"<td>");
		k.append("<td>view<td>");
		k.append("</tr>");
	}
	$("#ordersTable").append("</tbody>");
});






/* $('ul').json2html(data,transform); */

</script>-->

<script>

window.contextRoot = '${contextRoot}';

$(document).ready(function(){
	var dat =eval('${JSONdata}');
	var table = $('#ordersTable').DataTable( {
		"lengthMenu" : [ [ 3, 5, 10, -1 ],
			[ '3 Records', '5 Records', '10 Records', 'ALL' ] ],
	"pageLength" : 5,
	"aaData": dat,
	"aoColumns": [
	{ data : 'id',
		bSortable : false,
		mRender: function(data,type,row){
			var str='';
			str += '<a href="'+window.contextRoot+'/order?orderId='+data+'">View</a>';
			//return window.contextRoot+'  '+data;
			return str;
		}},
					{ data : 'orderNum' },
				    { data : 'amount',
						mRender : function(data,type,row){
							return '$ '+data;
						}},
					{ "mData": "customerName" },
					{ "mData": "customerAddress" },
					{ "mData": "customerEmail" },
					{ "mData": "customerPhone" }
	]
	});
	var info = table.page.info();
	$('#PageId').val(
			
		    (info.page+1)
		);
	$('#PageSize').val(
			
		    (info.length)
		);
	
	console.log(info.page+1);
	console.log(info.length);
	
	 

	});
	
	
/*$('.orderTable_length').change(function () {
	  $('#PageSize').val(
				
			    (info.length)
			);
	  console.log(info.length);
});*/

document
.getElementsByClassName('orderTable_length')
.addEventListener('change', function () {
	var table = $('#ordersTable').DataTable();
	var info = table.page.info();
$('#PageId').val(
			
		    (info.page+1)
		);
	$('#PageSize').val(
			
		    (info.length)
		);
	console.log(info.page+1);
	console.log(info.length);
});


</script>
</head>
<body>
	<jsp:include page="_header.jsp" />
	<jsp:include page="_menu.jsp" />


	<h1>Orders Table</h1>
	<table id="ordersTable" class="table display">

		<!-- Header Table -->
		<thead>
			<tr>
				<th>View</th>
				<th>Order Number</th>
				<th>Amount</th>
				<th>Customer Name</th>
				<th>Customer Address</th>
				<th>Customer Email</th>
				<th>Customer Phone</th>
			</tr>
		</thead>
		<!-- Footer Table -->
		<tfoot>
			<tr>
				<th>View</th>
				<th>Order Number</th>
				<th>Amount</th>
				<th>Customer Name</th>
				<th>Customer Address</th>
				<th>Customer Email</th>
				<th>Customer Phone</th>
			</tr>
		</tfoot>
	</table>
	
	<!--  
		<security:authorize access="hasRole('ROLE_MANAGER')">
		<form:form action="${pageContext.request.contextPath}/saveToXls"
			method="post">
			<input type="hidden"
				name="currPageId" id="PageId"/>
			<input type="hidden"
				name="pageSize" id="PageSize"/>
			<input type="submit" value="Save To Excel"
				style="border-radius: 15px; padding: 5px 10px; color: #fff" />
		</form:form>
	</security:authorize>
-->


	<jsp:include page="_footer.jsp" />
</body>
</html>