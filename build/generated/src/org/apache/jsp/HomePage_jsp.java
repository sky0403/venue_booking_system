package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class HomePage_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("<head>\n");
      out.write("<meta charset=\"UTF-8\">\n");
      out.write("<link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/index.css\"/>\n");
      out.write("<title>EPL Venue Booking System - Dashboard</title>\n");
      out.write("</head>\n");
      out.write("<body>\n");
      out.write("    <h1>Welcome to the EPL Venue Booking System</h1>\n");
      out.write("    ");
      ict.bean.UserInfo userInfo = null;
      synchronized (session) {
        userInfo = (ict.bean.UserInfo) _jspx_page_context.getAttribute("userInfo", PageContext.SESSION_SCOPE);
        if (userInfo == null){
          userInfo = new ict.bean.UserInfo();
          _jspx_page_context.setAttribute("userInfo", userInfo, PageContext.SESSION_SCOPE);
        }
      }
      out.write("\n");
      out.write("    <h1><b>Hello, ");
      out.write(org.apache.jasper.runtime.JspRuntimeLibrary.toString((((ict.bean.UserInfo)_jspx_page_context.findAttribute("userInfo")).getUsername())));
      out.write("</b></h1>\n");
      out.write("    ");
 if (userInfo.getPosition().equalsIgnoreCase("member") ) { 
      out.write("\n");
      out.write("        <h2>Member Dashboard</h2>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"booking_management.jsp\">View/Update Booking Requests</a></li>\n");
      out.write("            <li><a href=\"handleGuestList?action=list\">Manage Guest Lists</a></li>\n");
      out.write("            <li><a href=\"user_management.jsp\">View/Edit Personal Information</a></li>\n");
      out.write("            <li><a href=\"Venue_list\"</li>Venue List</a></li>\n");
      out.write("        </ul>\n");
      out.write("    ");
 } else if (userInfo.getPosition().equalsIgnoreCase("staff")) { 
      out.write("\n");
      out.write("        <h2>Staff Dashboard</h2>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"handleVenue?action=list\">View/Update Booking Requests</a></li>\n");
      out.write("            <li><a href=\"handleVenue?action=list\">Manage Venues</a></li>\n");
      out.write("            <li><a href=\"user_management.jsp\">View/Edit User Information</a></li>\n");
      out.write("        </ul>\n");
      out.write("    ");
 } else if (userInfo.getPosition().equalsIgnoreCase("senior")) { 
      out.write("\n");
      out.write("        <h2>Senior Management Dashboard</h2>\n");
      out.write("        <ul>\n");
      out.write("            <li><a href=\"booking_management.jsp\">View/Update Booking Requests</a></li>\n");
      out.write("            <li><a href=\"venue_management.jsp\">Manage Venues</a></li>\n");
      out.write("            <li><a href=\"guest_management.jsp\">Manage Guest Lists</a></li>\n");
      out.write("            <li><a href=\"user_management.jsp\">Manage User Accounts</a></li>\n");
      out.write("            <li><a href=\"analytic_report.jsp\">View Analytic/Report</a></li>\n");
      out.write("        </ul>\n");
      out.write("    ");
 } 
      out.write("\n");
      out.write("    <form action=\"main\" method=\"post\">\n");
      out.write("        <input type=\"hidden\" name=\"action\" value=\"logout\">\n");
      out.write("        <input type=\"submit\" value=\"Log Out\">\n");
      out.write("    </form>\n");
      out.write("</body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
