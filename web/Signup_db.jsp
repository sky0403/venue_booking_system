<%@ page  import  = "java.sql.*"%>
<%
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    String role = request.getParameter("role");
    String blacklistString = request.getParameter("blacklist");
    Boolean blacklist = Boolean.parseBoolean(blacklistString);
try{
    Class.forName("com.mysql.jdbc.Driver");
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/itp4511_assignment","root","");
    PreparedStatement ps = conn.prepareStatement("insert into user(user_name, password, role, blacklist) values(?,?,?,?);");
    ps.setString(1,username);
    ps.setString(2,password);
    ps.setString(3,role);
    ps.setBoolean(4,blacklist);
    int x = ps.executeUpdate();
    if(x > 0){
        out.println("Registration done successfully...<a href='index.jsp'>Back to Login in</a>");
    }else{
        out.print("Registration Failed...<a href='register.jsp'>Do it again</a>");
    }
}catch(Exception e){
            out.println(e);
}
%>