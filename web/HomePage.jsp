<%-- 
    Document   : HomePage
    Created on : 2023年4月22日, 下午04:37:46
    Author     : User
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="CSS/index.css"/>
<title>EPL Venue Booking System - Dashboard</title>
</head>
<body>
    <h1>Welcome to the EPL Venue Booking System</h1>
    <jsp:useBean id="userInfo" class="ict.bean.UserInfo" scope="session"/>
    <h1><b>Hello, <jsp:getProperty name="userInfo" property="username"/></b></h1>
    <% if (userInfo.getPosition().equalsIgnoreCase("member") ) { %>
        <h2>Member Dashboard</h2>
        <ul>
            <li><a href="handleBooking?action=memberlist">View/Update Booking Requests</a></li>
            <li><a href="handleGuestList?action=list">Manage Guest Lists</a></li>
            <li><a href="handleVenue?action=booking"</li>Venue List</a></li>
        </ul>
    <% } else if (userInfo.getPosition().equalsIgnoreCase("staff")) { %>
        <h2>Staff Dashboard</h2>
        <ul>
            <li><a href="handleBooking?action=stafflist">View/Update Booking Requests</a></li>
            <li><a href="handleVenue?action=list">Manage Venues</a></li>
        </ul>
    <% } else if (userInfo.getPosition().equalsIgnoreCase("senior")) { %>
        <h2>Senior Management Dashboard</h2>
        <ul>
            <li><a href="user_management.jsp">Manage User Accounts</a></li>
            <li><a href="handleVenue?action=report">View Analytic/Report</a></li>
        </ul>
    <% } %>
    <form action="main" method="post">
        <input type="hidden" name="action" value="logout">
        <input type="submit" value="Log Out">
    </form>
</body>
</html>
