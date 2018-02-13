       
<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<html>
    <head>
        <title>Select Books</title>
    </head>
    <body>
        <div>
        	<h2> Shopping Cart <h2>
            <jsp:include page="menu.jsp"/><br/><br/>
            <h3> Shop for Books</h3>
            <form id="form" action="cart.do" method="post" >
                <a href="myCart.jsp"><h3>My Cart</h3></a>
                <br />
                <br />                 
                <input type='checkbox' name='books' id='books' value='Java Servlet Programming' />Java Servlet Programming [$29.95]<br />
                <input type='checkbox' name='books' id='books' value='Oracle Database Programming' />Oracle Database Programming [$48.95]<br />
                <input type='checkbox' name='books' id='books' value='System Analysis and Design with UML' />System Analysis and Design with UML [$14.95]<br />
                <input type='checkbox' name='books' id='books' value='Object Oriented Software Engineering' />Object Oriented Software Engineering [$35.99]<br />
                <input type='checkbox' name='books' id='books' value='Java Web Services' />Java Web Services [$27.99]<br />
        <input type='hidden' name='action' value='Add'/>
        <input type ='submit' value = 'Add to Cart' name = 'button'/><br /></p>
            </form>
        </div>
    </body>
</html>