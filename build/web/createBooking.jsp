<%-- 
    Document   : editVenue
    Created on : 2023年4月27日, 下午01:03:22
    Author     : user
--%>

<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.sql.Date"%>
<%@page import="ict.bean.GuestList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Detail</title>
    </head>
    <body>
        <jsp:useBean id="v" scope="request" class="ict.bean.Venue"/>
        <jsp:useBean id="userInfo" class="ict.bean.UserInfo" scope="session"/>
        
        <%
            ArrayList<GuestList> guestlist = (ArrayList<GuestList>)request.getAttribute("guestlist");
            String booking_id = String.valueOf(request.getAttribute("booking_id"));
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now(); 
            String currentdate = dtf.format(now);
            String id = String.valueOf(v.getId())!=null ? String.valueOf(v.getId()) : "";
            String user_id = String.valueOf(userInfo.getId());
            String name = "";
            String venue_type = "";
            String capacity = "";
            String location = "";
            String desc = "";
            String person = "";
            String fee = "";
            String enable = "";
            if(id!=null){
                name = v.getName()!=null? v.getName():"";
                venue_type = v.getType()!=null? v.getType():"";
                capacity = v.getCapacity() != 0? String.valueOf(v.getCapacity()):"";
                location = v.getLocation() != ""?v.getLocation() : "";
                desc = v.getDescription()!= ""? v.getDescription():"";
                person = v.getInchargePerson() != ""? v.getInchargePerson(): "";
                fee = v.getFee()!= 0? String.valueOf(v.getFee()):"";
                enable = v.getEnabled()? "v":"x";
            }
        %>
        <h1>Booking Form</h1>
        <form action="handleEditBooking" method="get">
            <input type="hidden" name="action" value="booking"/>
            <input type="hidden" name="booking_id" value="<%=booking_id%>"/>
            <input type="hidden" name="user_id" value="<%=user_id%>"/>
            <input type="hidden" name="venue_id" value="<%=id%>"/>
            <input type="hidden" name="fee" value="<%=fee%>"/>
            <input type="hidden" name="inchargeperson" value="<%=person%>"/>
            Venue_Name <input type="text" name="name" value="<%=name%>" readonly/><br/>
            Guest List:
            <select name="guestlist" required>
                <% for(int i = 0; i<guestlist.size();i++){
                        GuestList gl = guestlist.get(i);
                        out.println("<option value=" + gl.getId() + ">" + gl.getId() + "</option>");
                    }
                %>
             </select>
            <br/>
            Booking_Date: <input type="date" name="booking_date" min="<%=currentdate%>"/><br/>
            Booking_Start_Time: <input type="time" name="booking_Starttime" id="booking_Starttime" min="09:00" max="17:00" step="3600" required /><br/>
            Booking_End_Time: <input type="time" name="booking_Endtime" id="booking_Endtime" min="10:00" max="18:00" step="3600" required/><br/>
            Guest invitations: <textarea cols="20" rows="4" name="guest_invitations" required></textarea>
            <br/><br/>
             <button type="button" onclick="if (checkTime()) this.form.submit()">Submit</button>
        </form>
        <script>
            function checkTime() {
              var startTime = document.getElementById("booking_Starttime").value;
              var endTime = document.getElementById("booking_Endtime").value;
              if (startTime >= endTime) {
                alert("End time must be greater than start time!");
                return false;
              }
              return true;
            }
        </script>
        <a href="handleVenue?action=booking">Back to Venue List</a>
    </body>
</html>
