/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.Venue;
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
@WebServlet(name = "HandleEditVenue", urlPatterns = {"/handleEditVenue"})
public class HandleEditVenue extends HttpServlet {
    private venueDB db;
    
    public void init(){
        String dbUrl = getServletContext().getInitParameter("dbUrl");
        String dbUser = getServletContext().getInitParameter("dbUser");
        String dbPassword = getServletContext().getInitParameter("dbPassword");
        db = new venueDB(dbUrl,dbUser,dbPassword);
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
            String name = request.getParameter("name");
            String type = request.getParameter("venue_type");
            int cap = Integer.parseInt(request.getParameter("capacity"));
            String location = request.getParameter("location");
            String desc = request.getParameter("description");
            String person = request.getParameter("incharge_person");
            int fee = Integer.parseInt(request.getParameter("fee"));
            boolean enabled;
            if(request.getParameter("enable").equalsIgnoreCase("true")){
                enabled = true;
            }else{
                enabled = false;
            }
            boolean Added = db.AddVenue(name,type,cap,location,desc,person,fee,enabled);
            if(Added){
                response.sendRedirect("handleVenue?action=list");
            }else{
                PrintWriter out = response.getWriter();
                out.println("<html>");
                out.println("<body>");
                out.println("Cannot add venue in database, please add venue again<br/>");
                out.println("<a href=\"editVenue.jsp\">Return to add venue page</a>");
                out.println("</body>");
                out.println("</html>");
            }
        }else if("edit".equalsIgnoreCase(action)){
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String type = request.getParameter("venue_type");
            int cap = Integer.parseInt(request.getParameter("capacity"));
            String location = request.getParameter("location");
            String desc = request.getParameter("description");
            String person = request.getParameter("incharge_person");
            int fee = Integer.parseInt(request.getParameter("fee"));
            boolean enabled;
            if(request.getParameter("enable").equalsIgnoreCase("true")){
                enabled = true;
            }else{
                enabled = false;
            }
            Venue v = new Venue();
            v.setId(id);
            v.setName(name);
            v.setType(type);
            v.setCapacity(cap);
            v.setLocation(location);
            v.setDescription(desc);
            v.setInchargePerson(person);
            v.setFee(fee);
            v.setEnabled(enabled);
            boolean Edited = db.editVenue(v);
            response.sendRedirect("handleVenue?action=list");
            
        } else{
            PrintWriter out = response.getWriter();
            out.println("No such action!!");
        }
    }
}
