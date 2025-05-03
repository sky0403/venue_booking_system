<%-- 
    Document   : register
    Created on : 2023年4月29日, 上午11:00:05
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
        <link rel="stylesheet" type="text/css" href="CSS/bootstrap.css"/>
        
    </head>
    <body>
        <div class="container">
            <div class="row col-md-6 col-md-offset-3">
                <div class="panel panel-primary">
                    <div class="panel-heading text-center">
                        <h1>Registration Form</h1>
                    </div>
                    <div class="panel-body">
                        <form action="Signup_db.jsp" method="post">
                            <div class="form-group">
                                <label for="username">Username</label>
                                <input type="text" class="form-control" id="username" name="username"/>
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" id="password" name="password"/>
                            </div>
                            <div class="form-group">
                                <label for="role">Role</label>
                                <div>
                                    <label for="member" class="radio-inline">
                                        <input type="radio" name="role"  value="member" id="member">Member</label></br>
                                    <label for="staff" class="radio-inline">
                                        <input type="radio" name="role"  value="staff"id="staff">Staff</label></br>
                                    <label for="management" class="radio-inline">
                                        <input type="radio" name="role"  value="management" id="management">Senior_management</label></br>
                                </div>
                            </div>

                            <input type="submit" value="Sign up">
                            <input type="reset" value="Cancel">
                        </form>
                        <a href="index.jsp">Back to Login in</a>
                    </div>

    </div>
    </div>        
    </body>
</html>
