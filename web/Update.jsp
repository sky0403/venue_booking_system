<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*" %>
 
<%
    if(request.getParameter("submit")!=null)
    {   
        String id = request.getParameter("id");
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
        pst = con.prepareStatement("update user set user_name = ?,password =?,role= ?, blacklist = ? where user_id = ?");
        pst.setString(1,username);
        pst.setString(2,password);
        pst.setString(3,role);
        pst.setBoolean(4,blacklist);
        pst.setString(5,id);
        int x = pst.executeUpdate();    
        
        %>
        
        <script>  
            alert("Record Updated");          
       </script>
    <%            
    }
 
%>
 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User detail</title>
        
         <link rel="stylesheet" type="text/css" href="CSS/bootstrap.css"/>
        <link rel="stylesheet" type="text/css" href="CSS/bootstrap.min.css"/>
        
        
        
    </head>
    <body>
        <h1>Edit User detail</h1>
        
        
        <div class="row">
            <div class="col-sm-4">
                <form  method="POST" action="#" >
                    
                    <%    
                        Connection con;
                        PreparedStatement pst;
                        ResultSet rs;
        
                         Class.forName("com.mysql.jdbc.Driver");
                          con = DriverManager.getConnection("jdbc:mysql://localhost:3306/itp4511_assignment","root","");
                          
                          String id = request.getParameter("id");
                          
                        pst = con.prepareStatement("select * from user where user_id = ?");
                        pst.setString(1, id);
                        rs = pst.executeQuery();
                        
                         while(rs.next())
                         {
                    
                    %>
                    <div alight="left">
                        <label class="form-label">UserName</label>
                        <input type="text" class="form-control" placeholder="UserName" value="<%= rs.getString("user_name")%>" name="username" id="username" required >
                     </div>
                        
                    <div alight="left">
                        <label class="form-label">Password</label>
                        <input type="text" class="form-control" placeholder="password" name="password" value="<%= rs.getString("password")%>" id="password" required >
                     </div>
                        
                     <div alight="left">
                        <label class="form-label">Role</label>
                                <div>
                                    <label for="member" class="radio-inline">
                                        <input type="radio" name="role"  value="<%= rs.getString("role")%>" id="member">Member</label></br>
                                    <label for="staff" class="radio-inline">
                                        <input type="radio" name="role"  value="<%= rs.getString("role")%>"id="staff">Staff</label></br>
                                    <label for="management" class="radio-inline">
                                        <input type="radio" name="role"  value="<%= rs.getString("role")%>" id="management">Senior_management</label></br>
                                </div>
                    </div>
                    <% }  %>
                    
                    
                    
                         </br>
                        
                     <div alight="right">
                         <input type="submit" id="submit" value="submit" name="submit" class="btn btn-info">
                         <input type="reset" id="reset" value="reset" name="reset" class="btn btn-warning">
                     </div>  
                        
                         <div align="right">
                            
                             <p><a href="user_management.jsp">Click Back</a></p>
                            
                            
                         </div>
  
                </form>
            </div>          
        </div>
  
    </body>
</html>