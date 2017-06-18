package org.apache.jsp.Libro.Transacciones;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.dto.FacturaBoleta;
import java.util.ArrayList;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\n");

    String tabla ="";
    if (request.getParameter("tabla")!=null){
        tabla = request.getParameter("tabla");
    }
    


      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Tabla Distribuidor</title>\n");
      out.write("        ");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "../../template.jsp", out, false);
      out.write("\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");
 
            ArrayList<FacturaBoleta> lista = (ArrayList) request.getAttribute("lista");
            
        
      out.write("\n");
      out.write("        \n");
      out.write("        <h1 class=\"text-center\">");
      out.print(tabla );
      out.write(" </h1>\n");
      out.write("        <div class=\"container\">\n");
      out.write("            <p><a href=\"");
      out.print(request.getContextPath() );
      out.write("/Libro/Transacciones/formulario.jsp?accion=insertar\" class=\"glyphicon glyphicon-plus btn btn-success\" ></a></p>\n");
      out.write("            <table class=\"table\">\n");
      out.write("                <thead>\n");
      out.write("                    <th>Folio</th>    \n");
      out.write("                    <th>Fecha de emisión</th>                   \n");
      out.write("                    <th>Precio neto</th>\n");
      out.write("                    <th>IVA</th>                   \n");
      out.write("                    <th>Precio con IVA</th>                   \n");
      out.write("                    <th>ID método pago</th>                   \n");
      out.write("                    <th>Acciones</th>\n");
      out.write("                </thead>\n");
      out.write("                \n");
      out.write("                    ");
 for (FacturaBoleta r : lista){
      out.write("\n");
      out.write("                    <tbody>\n");
      out.write("                        <td>");
      out.print(r.getFolio() );
      out.write("</td>\n");
      out.write("                        <td>");
      out.print(r.getFechaCompra() );
      out.write("</td>                        \n");
      out.write("                        <td>");
      out.print(r.getPrecioNeto() );
      out.write("</td>\n");
      out.write("                        <td>");
      out.print(r.getIva() );
      out.write("%</td>\n");
      out.write("                        <td>");
      out.print(r.getPrecioIva() );
      out.write("</td>\n");
      out.write("                        <td>");
      out.print(r.getMetodoPago() );
      out.write("</td>\n");
      out.write("                        \n");
      out.write("                        \n");
      out.write("                        <td>\n");
      out.write("                        <a href=\"");
      out.print(request.getContextPath() );
      out.write("/Libro/Transacciones/formulario.jsp?accion=modificar&id=");
      out.print(r.getFolio() );
      out.write("&tabla=");
      out.print(tabla );
      out.write("\" class=\"btn btn-warning\"><spam class=\"glyphicon glyphicon-pencil\"></spam></a>\n");
      out.write("                        <a href=\"");
      out.print(request.getContextPath());
      out.write("/Factura/eliminar?id=");
      out.print(r.getFolio() );
      out.write("\" class=\"btn btn-danger\"><spam class=\"glyphicon glyphicon-remove\"></spam></a> \n");
      out.write("                        </td> \n");
      out.write("                    </tbody>\n");
      out.write("                    ");
 }
      out.write("\n");
      out.write("                \n");
      out.write("            </table>\n");
      out.write("        </div>\n");
      out.write("    </body>\n");
      out.write("</html>");
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
