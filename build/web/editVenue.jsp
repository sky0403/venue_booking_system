<%-- 
    Document   : editVenue
    Created on : 2023年4月27日, 下午01:03:22
    Author     : user
--%>

<%@page import="ict.bean.UserInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="v" scope="request" class="ict.bean.Venue"/>
        <%
            ArrayList<UserInfo> users = (ArrayList<UserInfo>) request.getAttribute("users");
            String type = v.getId()!= 0? "edit" : "add";
            String input = v.getId()!=0?"text":"hidden";
            String id = String.valueOf(v.getId())!=null ? String.valueOf(v.getId()) : "";
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
        <h1><%=type%></h1>
        <form action="handleEditVenue" method="get">
            <input type="hidden" name="action" value="<%=type%>"/>
            <% if(input == "text"){out.print("ID");}else{} %> <input name="id" type="<%=input%>" value="<%=id%>" readonly/><br/>
            Name <input type="text" name="name" value="<%=name%>"/><br/>
            Venue_Type <input type="text" name="venue_type" value="<%=venue_type%>"/> <br/>
            Venue_Capacity <input type="text" name="capacity" value="<%=capacity%>"/> <br/>
            Location <input type="text" name="location" value="<%=location%>"/> <br/>
            Description <br/><textarea name="description" rows ="4" cls="50"><%=desc%></textarea><br/>
            Incharge_person
            <select name="incharge_person" required>
                <% for(int i = 0; i<users.size();i++){
                        UserInfo ui = users.get(i);
                        out.println("<option value=" + ui.getUsername()+ ">" + ui.getUsername() + "</option>");
                    }
                %>
             </select>
            
            <br/>
            Booking_Fee <input type="text" name="fee" value="<%=fee%>"/> <br/>
            Enabled
            <select name="enable">
                <option value="true" <%if(enable == "v"){ out.println("selected");}%>>v</option>
                <option value="false"<%if(enable == "x"){ out.println("selected");}%>>x</option>
             </select>
            <br/><br/>
             <input type="submit" value="submit"/><br/>
        </form>
        <a href="HomePage.jsp">Back to Home Page</a>
    </body>
</html>
