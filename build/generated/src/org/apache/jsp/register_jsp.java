package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class register_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
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
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Registration Page</title>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"CSS/bootstrap.css\"/>\n");
      out.write("        \n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <div class=\"row col-md-6 col-md-offset-3\">\n");
      out.write("                <div class=\"panel panel-primary\">\n");
      out.write("                    <div class=\"panel-heading text-center\">\n");
      out.write("                        <h1>Registration Form</h1>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"panel-body\">\n");
      out.write("                        <form action=\"Signup_db.jsp\" method=\"post\">\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label for=\"username\">Username</label>\n");
      out.write("                                <input type=\"text\" class=\"form-control\" id=\"username\" name=\"username\"/>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label for=\"password\">Password</label>\n");
      out.write("                                <input type=\"password\" class=\"form-control\" id=\"password\" name=\"password\"/>\n");
      out.write("                            </div>\n");
      out.write("                            <div class=\"form-group\">\n");
      out.write("                                <label for=\"role\">Role</label>\n");
      out.write("                                <div>\n");
      out.write("                                    <label for=\"member\" class=\"radio-inline\">\n");
      out.write("                                        <input type=\"radio\" name=\"role\"  value=\"member\" id=\"member\">Member</label></br>\n");
      out.write("                                    <label for=\"staff\" class=\"radio-inline\">\n");
      out.write("                                        <input type=\"radio\" name=\"role\"  value=\"staff\"id=\"staff\">Staff</label></br>\n");
      out.write("                                    <label for=\"management\" class=\"radio-inline\">\n");
      out.write("                                        <input type=\"radio\" name=\"role\"  value=\"management\" id=\"management\">Senior_management</label></br>\n");
      out.write("                                </div>\n");
      out.write("                            </div>\n");
      out.write("\n");
      out.write("                            <input type=\"submit\" value=\"Sign up\">\n");
      out.write("                            <input type=\"reset\" value=\"Cancel\">\n");
      out.write("                        </form>\n");
      out.write("                        <a href=\"index.jsp\">Back to Login in</a>\n");
      out.write("                    </div>\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("    </div>        \n");
      out.write("    </body>\n");
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
