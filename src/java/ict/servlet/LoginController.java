/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ict.servlet;

import ict.bean.UserInfo;
import ict.db.userDB;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "LoginController", urlPatterns = {"/main"})
public class LoginController extends HttpServlet {
    private userDB userdb;
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doPost(request,response);
    }
    
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
        String action = request.getParameter("action");
        if(!isAuthenticated(request)&&!("authenticate".equals(action))){
            doLogin(request, response);
            return;
        }
        if("authenticate".equals(action)){
            doAuthenticate(request,response);
        }else if("logout".equals(action)){
            doLogout(request,response);
        }else{
            response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }
    
    private void doAuthenticate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String targetURL;
        
        UserInfo bean = userdb.isVaildUser(username,password);
        if(bean.getId() != 0){
            HttpSession session = request.getSession(true);
            UserInfo userbean = new UserInfo();
            userbean.setId(bean.getId());
            userbean.setUsername(bean.getUsername());
            userbean.setPassword(bean.getPassword());
            userbean.setPosition(bean.getPosition());
            userbean.setBlackList(bean.getBlackList());
            session.setAttribute("userInfo", userbean); //set attribute with session
            session.setAttribute("user_id", userbean.getId());
            targetURL = "/HomePage.jsp";
        }
        else{
            targetURL = "/loginError.jsp";
        }
        /*boolean isMember = userdb.isMember(username,password);
        boolean isStaff = userdb.isStaff(username,password);
        boolean isSenior = userdb.isSenior(username,password);
        if(isMember){
            HttpSession session = request.getSession(true);
            UserInfo bean = new UserInfo();
            bean.setId()
            bean.setUsername(username);
            bean.setPassword(password);
            bean.setPosition("member");
            session.setAttribute("userInfo", bean); //set attribute with session
            targetURL = "/HomePage.jsp";
        }else if(isStaff){
            HttpSession session = request.getSession(true);
            UserInfo bean = new UserInfo();
            bean.setUsername(username);
            bean.setPassword(password);
            bean.setPosition("Staff");
            session.setAttribute("userInfo", bean); //set attribute with session
            targetURL = "/HomePage.jsp";
        }else if(isSenior){
            HttpSession session = request.getSession(true);
            UserInfo bean = new UserInfo();
            bean.setUsername(username);
            bean.setPassword(password);
            bean.setPosition("Senior");
            session.setAttribute("userInfo", bean); //set attribute with session
            targetURL = "/HomePage.jsp";
        }
        else{
            targetURL = "/loginError.jsp";
        }
        */
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
        /*if("abc".equals(username)&& "123".equals(password)){ //use session to login
            HttpSession session = request.getSession(true);
            UserInfo bean = new UserInfo();
            bean.setUsername(username);
            bean.setPassword(password);
            session.setAttribute("userInfo", bean); //set attribute with session
            targetURL = "/welcome.jsp";
        } else{
            targetURL = "/loginError.jsp";
        }
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);*/
    }
    private boolean isAuthenticated(HttpServletRequest request){
        boolean result = false;
        HttpSession session = request.getSession(); 
        if(session.getAttribute("userInfo") != null){ //get attribute from session
            result = true;
        }
        return result;
    }
    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        String targetURL ="index.jsp";
        RequestDispatcher rd;   
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request,response);
    }
    private void doLogout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        HttpSession session = request.getSession(false);
        if(session != null){
            session.removeAttribute("userInfo");
            session.invalidate();
        }
        doLogin(request, response);
    }
    
    public void init(){
        String dbUser = this.getServletContext().getInitParameter("dbUser");
        String dbPassword = this.getServletContext().getInitParameter("dbPassword");
        String dbUrl  = this.getServletContext().getInitParameter("dbUrl");
        userdb = new userDB(dbUrl,dbUser,dbPassword);
    }
}
