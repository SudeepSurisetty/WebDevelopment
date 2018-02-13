<%@page import="java.util.ArrayList"%>
<%@page import="Bean.Item"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping Cart</title>
    </head>
    <body>
        
            <h1> Shopping Cart <h1>
            <h2><jsp:include page="menu.jsp"/></h2><br/><br/>

			<h3> Items Added in your cart:</h3>
		<%
			ArrayList<Item> a1 = (ArrayList<Item>)session.getAttribute("myItemSet");
            if (a1 == null) {
                out.println("<div><p> No Items Added for you.</p></div>");
            } else {
                //else if there are items, display every item in the set

                    for (Item item : a1) {
                    out.println("Item name: " + item.getName() + "[<a href='cart.do?action=remove&crn=" + item.getId() + "'>Remove Item</a>]<br />");
                
                    
                }
            }
        %>


      
    </body>
</html>
        
        
        