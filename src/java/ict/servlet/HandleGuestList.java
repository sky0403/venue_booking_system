/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.GuestList;
import ict.bean.UserInfo;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
@WebServlet(name = "HandleGuestList", urlPatterns = {"/handleGuestList"})
public class HandleGuestList extends HttpServlet {
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
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo) session.getAttribute("userInfo");
        if("list".equalsIgnoreCase(action)){
            ArrayList<GuestList> lists = guest.queryGuestList(user.getId());
            request.setAttribute("guestlist", lists);
            RequestDispatcher rd;
            rd  = getServletContext().getRequestDispatcher("/guest_list_management.jsp");
            rd.forward(request,response);
        }
        else if("delete".equalsIgnoreCase(action)){
            String id = request.getParameter("id");
            if(id!=null){
                int guestlist_id = Integer.parseInt(id);
                boolean isDeleted = guest.delGuestList(guestlist_id);
                response.sendRedirect("handleGuestList?action=list");
            }
        }else if("getEditGuestList".equalsIgnoreCase(action)){
            String id = request.getParameter("id");
            if(id!=null){
                int guestlist_id = Integer.parseInt(id);
                GuestList gl = guest.queryGuestListByID(guestlist_id);
                request.setAttribute("gl", gl);
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/editGuestList.jsp");
                rd.forward(request,response);
            }
        }/*else if("search".equalsIgnoreCase(action)){
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
