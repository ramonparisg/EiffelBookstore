package org.apache.jsp.Libro;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.dao.LibroDAO;
import model.dto.Libro;

public final class formulario_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
 String accion="";
   if (request.getParameter("accion")!=null)
        accion = request.getParameter("accion"); 
      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>                \n");
      out.write("        <title>");
      out.print(accion);
      out.write(" autor</title>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../template.jsp", out, false);
      out.write("\n");
      out.write("    </head>\n");
      out.write("    \n");
      out.write("    <body>\n");
      out.write("        ");
 
            Libro l = new Libro();
            if (request.getParameter("id")!=null){
                LibroDAO dao = new LibroDAO();
                l = dao.buscar(request.getParameter("id"));
            }
        
      out.write("\n");
      out.write("        \n");
      out.write("        <h1 class=\"text-center\">");
      out.print(accion );
      out.write(" Autor</h1>\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <form method=\"post\" action=\"");
      out.print(request.getContextPath());
      out.write("/Autor/");
      out.print(accion );
      out.write("\">\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label>Nombre</label> \n");
      out.write("                    <input class=\"form-control\" type=\"text\" name=\"nombre\" value=\"");
      out.print(l.get );
      out.write("\" required>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label>Apellido Paterno</label> \n");
      out.write("                    <input class=\"form-control\" type=\"text\" name=\"apePat\" value=\"");
      out.print(a.getApePat() );
      out.write("\" required>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <label>Apellido Materno</label> \n");
      out.write("                    <input class=\"form-control\" type=\"text\" name=\"apeMat\" value=\"");
      out.print(a.getApeMat() );
      out.write("\">\n");
      out.write("                </div>           \n");
      out.write("                <input type=\"text\" name=\"id\" value=\"");
      out.print(a.getIdAutor());
      out.write("\" hidden=\"true\">\n");
      out.write("                <input type=\"submit\" class=\"form-control btn btn-success\">\n");
      out.write("\n");
      out.write("            </form>\n");
      out.write("        </div>\n");
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
