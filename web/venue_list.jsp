<%-- 
    Document   : venue_management
    Created on : 2023年4月23日, 上午02:40:35
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
<title>EPL Venue Booking System - Venue Booking</title>
</head>
<body>
    
    <%
            ArrayList<Venue> venues = (ArrayList<Venue>)request.getAttribute("venuesEnable");
            out.println("<h1>Venue Booking</h1>");
            out.println("<table border='1' >");
            out.println("<tr>");
            out.println("<th>Name</th><th>Type</th><th>Capacity</th><th>Location</th><th>Description</th><th>Person-in-charge</th><th>Booking Fee</th><th>Actions</th>");
            out.println("</tr>");
            
            for (int i = 0; i < venues.size(); i++) {
                Venue v = venues.get(i);
                out.println("<tr>");
                out.println("<td>" + v.getName() + "</td>");
                out.println("<td>" + v.getType() + "</td>");
                out.println("<td>" + v.getCapacity() + "</td>");
                out.println("<td>" + v.getLocation() + "</td>");
                out.println("<td>" + v.getDescription() + "</td>");
                out.println("<td>" + v.getInchargePerson() + "</td>");
                out.println("<td>" + v.getFee() + "</td>");
                out.println("<td><a href=\"handleBooking?action=bookingdetail&venue_id=" + v.getId() + "\">Book</a></td>");
                out.println("</tr>");
            }
            out.println("</table>");
        %>
        <p><a href="HomePage.jsp">Back to home page</a></p>
    <%--<table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Type</th>
                <th>Capacity</th>
                <th>Location</th>
                <th>Description</th>
                <th>Person-in-charge</th>
                <th>Booking Fee</th>
                <th>Enabled</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            Iterate through the list of venues from the database --%><%-- 
            <% for (Venue venue : venueList) { %>
                <tr>
                    <td><%= venue.getName() %></td>
                    <td><%= venue.getType() %></td>
                    <td><%= venue.getCapacity() %></td>
                    <td><%= venue.getLocation() %></td>
                    <td><%= venue.getDescription() %></td>
                    <td><%= venue.getPersonInCharge() %></td>
                    <td><%= venue.getBookingFee() %></td>
                    <td><%= venue.isEnabled() %></td>
                    <td>
                        <form action="edit_venue.jsp" method="post">
                            <input type="hidden" name="venueId" value="<%= venue.getId() %>">
                            <input type="submit" value="Edit">
                        </form>
                        <form action="delete_venue.jsp" method="post">
                            <input type="hidden" name="venueId" value="<%= venue.getId() %>">
                            <input type="submit" value="Delete">
                        </form>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <br>
    <form action="add_venue.jsp" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>
        <br>
        <label for="type">Type:</label>
        <input type="text" id="type" name="type" required>
        <br>
        <label for="capacity">Capacity:</label>
        <input type="number" id="capacity" name="capacity" required>
        <br>
        <label for="location">Location:</label>
        <input type="text" id="location" name="location" required>
        <br>
        <label for="description">Description:</label>
        <input type="text" id="description" name="description">
        <br>
        <label for="personInCharge">Person-in-charge:</label>
        <input type="text" id="personInCharge" name="personInCharge" required>
        <br>
        <label for="bookingFee">Booking Fee:</label>
        <input type="number" id="bookingFee" name="bookingFee" required>
        <br>
        <input type="submit" value="Add Venue">
    </form>--%>
</body>
</html>
