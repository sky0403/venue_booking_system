<%-- 
    Document   : guest_management
    Created on : 2023年4月24日, 上午02:38:47
    Author     : User
--%>
<%@page import="ict.bean.*,java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/index.css"/>
<jsp:useBean id="userInfo" class="ict.bean.UserInfo" scope="session"/>
<title>EPL Venue Booking System - Guest Management</title>
</head>
<body>
     <%
            ArrayList<Guest> guests = (ArrayList<Guest>)request.getAttribute("guests");
            String guestlist_id = request.getParameter("id");
            out.println("<h1>Guest Management</h1>");
            out.println("Guest List ID : " + guestlist_id);
            out.println("<table border='1' >");
            out.println("<tr>");
            out.println("<th>name</th><th>email</th><th>Actions</th>");
            out.println("</tr>");
            
            for (int i = 0; i < guests.size(); i++) {
                Guest g = guests.get(i);
                out.println("<tr>");
                out.println("<td>" + g.getName() + "</td>");
                out.println("<td>" + g.getEmail() + "</td>");
                out.println("<td><a href=\"handleGuest?action=delete&id=" + g.getGuestList_Id() + "&email=" + g.getEmail() + "\">Delete</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        %>
       
        <form action="handleEditGuest" method="get">
            <input type="hidden" name="action" value="add"/>
            <input name="id" type="hidden" value="<%=guestlist_id%>" readonly/><br/>
            Guest Name <input type="text" name="guest_name" value=""/><br/>
            Guest Email <input type="email" name="guest_email" value=""/><br/>
            <br/><br/>
             <input type="submit" value="submit"/><br/><p><a href="handleGuestList?action=list">Back to Guest List</a></p>
        </form>
        
</body>
</html>
