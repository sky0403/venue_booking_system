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
<title>EPL Venue Booking System - Guest Management</title>
</head>
<body>
    <%
            ArrayList<GuestList> guestlist = (ArrayList<GuestList>)request.getAttribute("guestlist");
            out.println("<h1>Guest List Management</h1>");
            out.println("<table border='1' >");
            out.println("<tr>");
            out.println("<th>Guest List name</th><th>Actions</th>");
            out.println("</tr>");
            
            for (int i = 0; i < guestlist.size(); i++) {
                GuestList gl = guestlist.get(i);
                out.println("<tr>");
                out.println("<td>" + gl.getListname() + "</td>");
                out.println("<td><a href=\"handleGuestList?action=delete&id=" + gl.getId() + "\">Delete</a></td>");
                out.println("<td><a href=\"handleGuest?action=list&id=" + gl.getId() + "\">Edit</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        %>
        <a href="editGuestList.jsp">Add new guest list</a><p><a href="HomePage.jsp">Back to home page</a></p>
</body>
</html>
