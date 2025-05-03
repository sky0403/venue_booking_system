/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.Booking;
import ict.bean.Booking_request;
import ict.bean.Guest;
import ict.bean.GuestList;
import ict.bean.Report;
import ict.bean.UserInfo;
import ict.bean.Venue;
import ict.db.bookingDB;
import ict.db.guestDB;
import ict.db.venueDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@WebServlet(name = "HandleReport", urlPatterns = {"/handleReport"})
public class HandleReport extends HttpServlet {
    private bookingDB booking;
    private venueDB venue;
    private guestDB guest;
    
    public void init(){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
        booking = new bookingDB(dbUrl,dbUser,dbPassword);
        venue = new venueDB(dbUrl,dbUser,dbPassword);
        guest = new guestDB(dbUrl,dbUser,dbPassword);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        processRequest(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("userInfo");
        if("list".equalsIgnoreCase(action)){
            int venue_id = Integer.parseInt(request.getParameter("venue_id"));
            ArrayList<Booking> Bookings = booking.queryBookingByVenue(venue_id);
            ArrayList<Venue> venues = new ArrayList<Venue>();
            ArrayList<Booking_request> booking_requests = new ArrayList<Booking_request>();
            for(int i = 0; i< Bookings.size();i++){
                Booking b = Bookings.get(i);
                Venue v = venue.queryVenueByID(Bookings.get(i).getVenue_id());
                venues.add(v);
                Booking_request bq = booking.queryBookingRequestById(b.getBooking_id());
                booking_requests.add(bq);
            }
            request.setAttribute("Bookings", Bookings);
            request.setAttribute("venues", venues);
            request.setAttribute("booking_requests",booking_requests);
            RequestDispatcher rd;
            rd  = getServletContext().getRequestDispatcher("/report_management.jsp");
            rd.forward(request,response);
        }else if("income".equalsIgnoreCase(action)){
            ArrayList<Report> reports = new ArrayList<Report>();
            String startmonth = request.getParameter("startmonth");
            String endmonth = request.getParameter("endmonth");
            
            ArrayList<Venue> venues = venue.queryVenue();
            for(int i = 0;i<venues.size(); i++){
                Venue v = (Venue)venues.get(i);
                Report rp = new Report();
                rp = booking.queryAmount(v.getId(), startmonth, endmonth);
                reports.add(rp);
            }
            request.setAttribute("venues", venues);
            request.setAttribute("reports",reports);
            RequestDispatcher rd;
            rd  = getServletContext().getRequestDispatcher("/income_management.jsp");
            rd.forward(request,response);
        }
        /*else if("delete".equalsIgnoreCase(action)){
            String id = request.getParameter("id");
            String email = request.getParameter("email");
            if(id!=null){
                int guestlist_id = Integer.parseInt(id);
                boolean isDeleted = guest.delGuest(guestlist_id,email);
                response.sendRedirect("handleGuest?action=list&id=" + guestlist_id);
            }
        }else if("getEditVenue".equalsIgnoreCase(action)){
            String id = request.getParameter("id");
            if(id!=null){
                int venue_id = Integer.parseInt(id);
                Venue venueInfo = venue.queryVenueByID(venue_id);
                request.setAttribute("v", venueInfo);
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/editVenue.jsp");
                rd.forward(request,response);
            }
        }else if("search".equalsIgnoreCase(action)){
            String name = request.getParameter("name");
            if(name != null){
                ArrayList<Venue> customers = venue.queryCustByName(name);
                request.setAttribute("customers", customers);
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/listCustomer.jsp");
                rd.forward(request,response);
            }
        }
        else if("edit".equalsIgnoreCase(action)){
            ArrayList<Venue> customers = venue.queryCust();
            request.setAttribute("venues", venues);
            RequestDispatcher rd;
            rd  = getServletContext().getRequestDispatcher("/listCustomer2.jsp");
            rd.forward(request,response);
        }*/
        else{
            PrintWriter out = response.getWriter();
            out.println("No such action");
        }
    }
}
