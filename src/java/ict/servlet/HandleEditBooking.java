/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.Venue;
import ict.db.bookingDB;
import ict.db.guestDB;
import ict.db.venueDB;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "HandleEditBooking", urlPatterns = {"/handleEditBooking"})
public class HandleEditBooking extends HttpServlet {
    private bookingDB db;
    private venueDB vdb;
    
    public void init(){
        String dbUrl = getServletContext().getInitParameter("dbUrl");
        String dbUser = getServletContext().getInitParameter("dbUser");
        String dbPassword = getServletContext().getInitParameter("dbPassword");
        db = new bookingDB(dbUrl,dbUser,dbPassword);
        vdb = new venueDB(dbUrl,dbUser,dbPassword);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        processRequest(request,response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if("booking".equalsIgnoreCase(action)){
            int user_id = Integer.parseInt(request.getParameter("user_id"));
            int guestlist_id = Integer.parseInt(request.getParameter("guestlist"));
            int venue_id = Integer.parseInt(request.getParameter("venue_id"));
            String booking_date = request.getParameter("booking_date");
            String booking_Starttime = request.getParameter("booking_Starttime");
            String booking_Endtime = request.getParameter("booking_Endtime");
            String guest_invitations = request.getParameter("guest_invitations");
            String[] start_parts = booking_Starttime.split(":");
            String[] end_parts = booking_Endtime.split(":");
            int start_hours = Integer.parseInt(start_parts[0]);
            int start_minutes = Integer.parseInt(start_parts[1]);
            int end_hours = Integer.parseInt(end_parts[0]);
            int end_minutes = Integer.parseInt(end_parts[1]);
            int hours = end_hours - start_hours;
            int fee = Integer.parseInt(request.getParameter("fee"));
            int amount = fee * hours;
            String person = request.getParameter("inchargeperson");
            int booking_id = Integer.parseInt(request.getParameter("booking_id"));
            Venue v = vdb.queryVenueByID(venue_id);
            
            boolean Added = db.createBooking(user_id,guestlist_id,venue_id,booking_date,booking_Starttime,booking_Endtime,guest_invitations,amount);
            
            if(Added){
                boolean Added2 = db.createBookingRequest(booking_id,v.getInchargePerson());
                response.sendRedirect("HomePage.jsp");
            }else{
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<body>");
                out.println("Cannot create booking  in database, please check your booking again<br/>");
                out.println("<a href=\"handleVenue?action=booking\">Return to venue list page</a>");
                out.println("</body>");
                out.println("</html>");
            }
           /*boolean Added = db.AddGuest(guestlist_id,name,email);
            if(Added){
                response.sendRedirect("HomePage.jsp");
            }else{
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<body>");
                out.println("Cannot add venue in database, please add venue again<br/>");
                out.println("<a href=\"editVenue.jsp\">Return to add venue page</a>");
                out.println("</body>");
                out.println("</html>");
            }*/
        }else{
            PrintWriter out = response.getWriter();
            out.println("No such action!!");
        }
    }
}
