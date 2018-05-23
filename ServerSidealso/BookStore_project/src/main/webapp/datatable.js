/**
 * 
 */



//	 var table = $('#ordersTable').DataTable({
//			"sAjaxSource": "/ordersList",
//			"sAjaxDataProp": "res",
//			"LengthChange": false,
//			"order": [[ 0, "asc" ]],
//			"aoColumns": [
//			    { "mData": "orderNum"},
//			    { "mData": "orderDate" },
//				  { "mData": "customerName" },
//				  { "mData": "customerAddress" },
//				  { "mData": "customerEmail" },
//				  { "mData": "amount" },
//				  { "mData": "<a href=\"${pageContext.request.contextPath}/order?orderId=orderNum\">View</a>"}
//			]
//	 });



//$(document).ready(function(){
//	var data =eval('${JSONdata}');
//	var table = $('#ordersTable').DataTable( {
//	"aaData": data,
//	"aoColumns": [
//	{ "mData": "orderNum"},
//				    { "mData": "orderDate" },
//					  { "mData": "customerName" },
//					  { "mData": "customerAddress" },
//					  { "mData": "customerEmail" },
//					  { "mData": "amount" },
//					  { "mData": "<a href=\"${pageContext.request.contextPath}/order?orderId=orderNum\">View</a>"}
//	]
//	});
//	});
/*
{
	  "demo": [
	    [
	      "Trident",
	      "Internet Explorer 4.0",
	      "Win 95+",
	      "4",
	      "X"
	    ],
	    [
	        "Other browsers",
	        "All others",
	        "-",
	        "-",
	        "U"
	      ]
	    ]}
*/



//var transform = {'<>':'li','html':[
//                    {'<>':'span','html':'${orderNum} ${amount} ${customerName} ${customerAddress} ${customerEmail} ${customerPhone}'}
//                ]};
//
//
//var data = JSONdata;
//
//
//    
//$('ul').json2html(data,transform);