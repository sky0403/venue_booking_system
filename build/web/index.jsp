<%-- 
    Document   : index
    Created on : 2023年4月12日, 下午10:50:06
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>EPL System</title>
        <link href="CSS/index.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <h1>Welcome to the EPL Venue Booking System</h1>
        <form action="main" method="post">
            <input type="hidden" name="action" value="authenticate"/>
            <label for="username">Username:</label>
            <input type="text" name="username" required>
            <br>
            <label for="password">Password:</label>
            <input type="password" name="password" required>
            <br>
            <input type="submit" value="Log In">
        </form>
        <a href="register.jsp">Register for an account</a>
    </body>
</html>
