/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.Guest;
import ict.bean.Venue;
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

/**
 *
 * @author user
 */
@WebServlet(name = "HandleGuest", urlPatterns = {"/handleGuest"})
public class HandleGuest extends HttpServlet {
    private guestDB guest;
    
    public void init(){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl = this.getServletContext().getInitParameter("dbUrl");
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
        if("list".equalsIgnoreCase(action)){
            int id = Integer.parseInt(request.getParameter("id"));
            ArrayList<Guest> guests = guest.queryGuest(id);
            request.setAttribute("guests", guests);
            request.setAttribute("guestList_id", id);
            RequestDispatcher rd;
            rd  = getServletContext().getRequestDispatcher("/guest_management.jsp");
            rd.forward(request,response);
        }
        else if("delete".equalsIgnoreCase(action)){
            String id = request.getParameter("id");
            String email = request.getParameter("email");
            if(id!=null){
                int guestlist_id = Integer.parseInt(id);
                boolean isDeleted = guest.delGuest(guestlist_id,email);
                response.sendRedirect("handleGuest?action=list&id=" + guestlist_id);
            }
        }/*else if("getEditVenue".equalsIgnoreCase(action)){
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
