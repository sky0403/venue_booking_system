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
<title>EPL Venue Booking System - Booking Management</title>
</head>
<body>
    
    <%
            String list = request.getParameter("action");
            
            out.println("<h1>Booking Management</h1>");
            
            if(list.equalsIgnoreCase("memberlist")){
                ArrayList<Booking> Bookings = (ArrayList<Booking>)request.getAttribute("Bookings");
                ArrayList<Venue> venues = (ArrayList<Venue>)request.getAttribute("venues");
                ArrayList<Booking_request> booking_requests = (ArrayList<Booking_request>)request.getAttribute("booking_requests");

                out.println("<table border='1' >");
                out.println("<tr>");
                out.println("<th>Booking id</th><th>Guest List id</th><th>venue name</th><th>booking_date</th><th>start_time</th><th>finish_time</th><th>amount</th><th>Status</th>");
                out.println("</tr>");
                for (int i = 0; i < Bookings.size(); i++) {
                    Booking b = Bookings.get(i);
                    Venue v = venues.get(i);
                    Booking_request bq = booking_requests.get(i);
                    out.println("<tr>");
                    out.println("<td>" + b.getBooking_id() + "</td>");
                    out.println("<td>" + b.getGuestList_id() + "</td>");
                    out.println("<td>" + v.getName() + "</td>");
                    out.println("<td>" + b.getBooking_date() + "</td>");
                    out.println("<td>" + b.getBooking_StartTime() + "</td>");
                    out.println("<td>" + b.getBooking_EndTime() + "</td>");
                    out.println("<td>" + b.getAmount()+ "</td>");
                    out.println("<td>" + bq.getBooking_accept() + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
                out.println("<a href=\"handleVenue?action=booking\">Create new booking</a>");
            }else{
                ArrayList<Booking_request> booking_requests = (ArrayList<Booking_request>)request.getAttribute("booking_requests");
                ArrayList<Booking> Bookings = (ArrayList<Booking>)request.getAttribute("Bookings");
                ArrayList<Venue> venues = (ArrayList<Venue>)request.getAttribute("venues");
                out.println("<table border='1' >");
                out.println("<tr>");
                out.println("<th>Booking id</th><th>Status</th><th>Actions</th>");
                out.println("</tr>");
                for (int i = 0; i < booking_requests.size(); i++) {
                    Booking_request bq = booking_requests.get(i);
                    
                    out.println("<tr>");
                    out.println("<td>" + bq.getBooking_id() + "</td>");
                    
                    out.println("<td>" + bq.getBooking_accept() + "</td>");
                    out.println("<td><a href=\"handleBooking?action=confirm&id=" + bq.getBooking_id() + "\">confirm</a></td>");
                    out.println("<td><a href=\"handleBooking?action=reject&id=" + bq.getBooking_id() + "\">reject</a></td>");
                    out.println("<td><a href=\"handleBooking?action=checkinForm&id=" + bq.getBooking_id() + "\">Check_in</a></td>");
                    out.println("<td><a href=\"handleBooking?action=checkoutform&id=" + bq.getBooking_id() + "\">Check_out</a></td>");
                    out.println("</tr>");
                }
                out.println("</table>");

            }
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
