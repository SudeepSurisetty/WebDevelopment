<%@ page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Shopping Cart</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
    </head>
    <body>
        <div>
            <h1>Login</h1>
            <form name='myForm' method="post" action="login.do?action=login">
                <input type="text" name="username" placeholder="Username" /><br/><br/>
                <input type="password" name="password" placeholder="Password"  /><br/><br/>
                <input type="submit" value="Log In" />
            </form>
        </div>
    </body>
</html>