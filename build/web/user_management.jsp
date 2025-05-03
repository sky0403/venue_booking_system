<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
<%
if(request.getParameter("submit")!=null)
    {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        String blacklistString = request.getParameter("blacklist");
        Boolean blacklist = Boolean.parseBoolean(blacklistString);

        Connection con;
        PreparedStatement pst;
        ResultSet rs;
        
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itp4511_assignment","root","");
        pst = con.prepareStatement("insert into user(user_name, password, role, blacklist) values(?,?,?,?);");
        pst.setString(1,username);
        pst.setString(2,password);
        pst.setString(3,role);
        pst.setBoolean(4,blacklist);
        int x = pst.executeUpdate();       
        %>
    <script>  
        alert("User Addded");    
    </script>
    <%            
    }
   %>
 
 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="CSS/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css"/>
    </head>
    <body>
        <h1>Add User</h1>
        </br>
        <div class="row">
            <div class="col-sm-4">
                <form  method="POST" action="#" >
                    
                    <div alight="left">
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
                         </br>
                        
                     <div alight="right">
                         <input type="submit" id="submit" value="submit" name="submit" class="btn btn-info">
                         <input type="reset" id="reset" value="reset" name="reset" class="btn btn-warning">
                     </div>  
                        
                </form>
            </div>
            
             <div class="col-sm-8">
                 <div class="panel-body">
                     <table id="tbl-student" class="table table-responsive table-bordered" cellpadding ="0" width="100%">
                         <thead>
                             <tr>
                                    <th>UserID</th>
                                    <th>UserName</th>
                                    <th>Password</th>
                                    <th>Role</th>
                                    <th>Blacklist</th>
                                    <th>Edit</th>
                                    <th>Delete</th>
                             </tr>  
                            
                             <%  
 
                                Connection con;
                                PreparedStatement pst;
                                ResultSet rs;
        
                                Class.forName("com.mysql.jdbc.Driver");
                                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itp4511_assignment","root","");
                                
                                  String query = "select * from user";
                                  Statement st = con.createStatement();
                                  
                                    rs =  st.executeQuery(query);
                                    
                                        while(rs.next())
                                        {
                                            String id = rs.getString("user_id");
                                   %>
            
                             <tr>
                                 <td><%=rs.getString("user_id") %></td>
                                 <td><%=rs.getString("user_name") %></td>
                                 <td><%=rs.getString("password") %></td>
                                 <td><%=rs.getString("role") %></td>
                                 <td><%=rs.getString("blacklist") %></td>
                                 <td><a href="Update.jsp?id=<%=id%>">Edit</a></td>
                                 <td><a href="delete.jsp?id=<%=id%>">Delete</a></td>
                             </tr>
                            
                            
                                <%
                                
                                 }
                               %>
                            
                     </table>    
                 </div>
 
            </div>  
        </div>
 
    </body>
</html>