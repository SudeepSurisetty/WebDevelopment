       
<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<html>
    <head>
        <title>Select Computer</title>
    </head>
    <body>
        <div>
        	<h2> Shopping Cart <h2>
            <jsp:include page="menu.jsp"/><br/><br/>
            <h3> Shop for Computers</h3>
            <form id="form" action="cart.do" method="post" >
                <a href="myCart.jsp"><h3>My Cart</h3></a>
                <br />
                <br />                 
                <input type='checkbox' name='computers' id='computers' value='Apple Macbook Pro 13.3 Display' />Apple Macbook Pro 13.3 Display [$1299.99]<br />
                <input type='checkbox' name='computers' id='computers' value='Asus Laptop with Centrino 2 Black' />Asus Laptop with Centrino 2 Black [$949.95]<br />
                <input type='checkbox' name='computers' id='computers' value='HP Pavilion with Centrino 2' />HP Pavilion with Centrino 2 [$1199.95]<br />
                <input type='checkbox' name='computers' id='computers' value='Toshiba Satellite Laptop with Centrino 2' />Toshiba Satellite Laptop with Centrino 2 [$899.99]<br />
                <input type='checkbox' name='computers' id='computers' value='Sony VAIO Laptop with Centrino 2' />Sony VAIO Laptop with Centrino 2 [$779.99]<br />
        <input type='hidden' name='action' value='Addcomputers'/>
        <input type ='submit' value = 'Add to Cart' name = 'button'/><br /></p>
            </form>
        </div>
    </body>
</html>