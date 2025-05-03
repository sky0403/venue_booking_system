<%-- 
    Document   : loginError
    Created on : Mar 15, 2023, 10:39:25 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Error</title>
    </head>
    <body>
        <p>Incorrect Password</p>
        <p>
            <% out.println("<a href=\"" + request.getContextPath() + "/main\">Login again</a>");%>
        </p>
    </body>
</html>
