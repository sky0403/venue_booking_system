<%-- 
    Document   : editVenue
    Created on : 2023年4月27日, 下午01:03:22
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Guest List</title>
    </head>
    <body>
        <jsp:useBean id="gl" scope="request" class="ict.bean.GuestList"/>
        <jsp:useBean id="userInfo" class="ict.bean.UserInfo" scope="session"/>
        <%
            String type = gl.getId()!= 0? "edit" : "add";
            String input = gl.getId()!=0?"text":"hidden";
            String id = String.valueOf(gl.getId())!=null ? String.valueOf(gl.getId()) : "";
            int guestlistid = id!=""?gl.getId():0;
            String name = "";
            String userid = "";
            if(id!=null){
                name = gl.getListname()!=null? gl.getListname():"";
            }
        %>
        <h1><%=type%></h1>
        <form action="handleEditGuestList" method="get">
            <input type="hidden" name="action" value="<%=type%>"/>
            <input type="hidden" name="userid" value="<jsp:getProperty name="userInfo" property="id"/>"/>
            <% if(input == "text"){out.print("ID");}else{} %> <input name="id" type="<%=input%>" value="<%=guestlistid%>" readonly/><br/>
            Guest List Name <input type="text" name="name" value="<%=name%>"/><br/>
            <br/><br/>
             <input type="submit" value="submit"/><br/>
        </form>
        <a href="handleGuestList?action=list">Back to Guest List</a>
    </body>
</html>
