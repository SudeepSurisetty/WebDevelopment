
<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<html>
    <head>
        <title>Select Music Albums</title>
    </head>
    <body>
        <div>
        	<h2> Shopping Cart <h2>
            <jsp:include page="menu.jsp"/><br/><br/>
            <h3> Shop for Music</h3>
            <form id="form" action="cart.do" method="post" >
                <a href="myCart.jsp"><h3>My Cart</h3></a>
                <br />
                <br />                 
                <input type='checkbox' name='music' id='music' value='I am Going to Tell you a Secret by Maradonna' />I am Going to Tell you a Secret by Maradonna [$26.99]<br />
                <input type='checkbox' name='music' id='music' value='Baby One More Time by Britney Spears' />Baby One More Time by Britney Spears [$10.95]<br />
                <input type='checkbox' name='music' id='music' value='Justified by Justin Timberlake' />Justified by Justin Timberlake [$9.97]<br />
                <input type='checkbox' name='music' id='music' value='Loose by Nelly Furtado' />Loose by Nelly Furtado [$13.98]<br />
                <input type='checkbox' name='music' id='music' value='Gold Digger by Kanye West' />Gold Digger by Kanye West [$27.99]<br />
        <input type='hidden' name='action' value='Addmusic'/>
        <input type =  'submit' value = 'Add to Cart' name = 'button'/><br /></p>
        </form>
        </div>
    </body>
</html>