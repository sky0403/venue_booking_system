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
            String id = String.valueOf(request.getAttribute("booking_id"));
        %>
        <h1>Check in</h1>
        <form action="handleBooking" method="get">
            <input type="hidden" name="action" value="checkout"/>
            <input type="hidden" name="id" value="<%=id%>"/>
            Checkout_time: <input type="time" name="Checkout_time" id="booking_Starttime" min="09:00" max="18:00" step="3600" required /><br/>
             <input type="submit" value="submit"/><br/>
        </form>
        <a href="handleBooking?action=stafflist">Back to List</a>
    </body>
</html>
