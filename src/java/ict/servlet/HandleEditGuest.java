/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.Venue;
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
@WebServlet(name = "HandleEditGuest", urlPatterns = {"/handleEditGuest"})
public class HandleEditGuest extends HttpServlet {
    private guestDB db;
    
    public void init(){
        String dbUrl = getServletContext().getInitParameter("dbUrl");
        String dbUser = getServletContext().getInitParameter("dbUser");
        String dbPassword = getServletContext().getInitParameter("dbPassword");
        db = new guestDB(dbUrl,dbUser,dbPassword);
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
        
        if("add".equalsIgnoreCase(action)){
            int guestlist_id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("guest_name");
            String email = request.getParameter("guest_email");
            boolean Added = db.AddGuest(guestlist_id,name,email);
            if(Added){
                response.sendRedirect("handleGuest?action=list&id=" + guestlist_id);
            }else{
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<body>");
                out.println("Cannot add venue in database, please add venue again<br/>");
                out.println("<a href=\"editVenue.jsp\">Return to add venue page</a>");
                out.println("</body>");
                out.println("</html>");
            }
        }else{
            PrintWriter out = response.getWriter();
            out.println("No such action!!");
        }
    }
}
