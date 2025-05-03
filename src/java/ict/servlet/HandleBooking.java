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
@WebServlet(name = "HandleBooking", urlPatterns = {"/handleBooking"})
public class HandleBooking extends HttpServlet {
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
        if("memberlist".equalsIgnoreCase(action)){
            ArrayList<Booking> Bookings = booking.queryBookingByUser(user.getId());
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
            rd  = getServletContext().getRequestDispatcher("/booking_management.jsp");
            rd.forward(request,response);
        }else if("stafflist".equalsIgnoreCase(action)){
            ArrayList<Booking_request> booking_requests = booking.queryBookingByIncharge(user.getUsername());
            request.setAttribute("booking_requests",booking_requests);
            RequestDispatcher rd;
            rd  = getServletContext().getRequestDispatcher("/booking_management.jsp");
            rd.forward(request,response);
        }
        else if("bookingdetail".equalsIgnoreCase(action)){
            String venue_id = request.getParameter("venue_id");
            if(venue_id!=null){
                ArrayList<Booking> Bookings = booking.queryBooking();
                int booking_id = Bookings.get(Bookings.size()-1).getBooking_id() + 1;
                int venue_id2 = Integer.parseInt(venue_id);
                Venue venueInfo = venue.queryVenueByID(venue_id2);
                request.setAttribute("v", venueInfo);
                ArrayList<GuestList> lists = guest.queryGuestList(user.getId());
                request.setAttribute("guestlist", lists);
                request.setAttribute("booking_id", booking_id);
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/createBooking.jsp");
                rd.forward(request,response);
            }
        }else if("confirm".equalsIgnoreCase(action)){
            String id = request.getParameter("id");
            if(id!=null){
                int booking_id = Integer.parseInt(id);
                boolean isConfirmed = booking.confirmbooking(booking_id);
                response.sendRedirect("handleBooking?action=stafflist");
            }
        }else if("reject".equalsIgnoreCase(action)){
            String id = request.getParameter("id");
            if(id!=null){
                int booking_id = Integer.parseInt(id);
                boolean isConfirmed = booking.rejectbooking(booking_id);
                response.sendRedirect("handleBooking?action=stafflist");
            }
        }else if("checkinForm".equalsIgnoreCase(action)){
            String id = request.getParameter("id");
            if(id!=null){
                int booking_id = Integer.parseInt(id);
                request.setAttribute("booking_id", booking_id);
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/CheckinForm.jsp");
                rd.forward(request,response);
            }
        }else if("checkoutForm".equalsIgnoreCase(action)){
            String id = request.getParameter("id");
            if(id!=null){
                int booking_id = Integer.parseInt(id);
                request.setAttribute("booking_id", booking_id);
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/CheckoutForm.jsp");
                rd.forward(request,response);
            }
        }
        else if("checkin".equalsIgnoreCase(action)){
            String id = request.getParameter("id");
            String Checkin_time = request.getParameter("Checkin_time");
            if(id!=null){
                int booking_id = Integer.parseInt(id);
                boolean Checkin = booking.checkin(booking_id,Checkin_time);
                response.sendRedirect("handleBooking?action=stafflist");
            }
        }else if("checkout".equalsIgnoreCase(action)){
            String id = request.getParameter("id");
            String Checkout_time = request.getParameter("Checkout_time");
            if(id!=null){
                int booking_id = Integer.parseInt(id);
                boolean checkout = booking.checkout(booking_id,Checkout_time);
                response.sendRedirect("handleBooking?action=stafflist");
            }
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
