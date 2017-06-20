/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.compra.FacturaDAO;
import model.dto.FacturaBoleta;
import model.dto.compra.DetalleCompra;
import util.Ayudante;

/**
 *
 * @author Ramon Paris
 */
public class CompraServlet extends HttpServlet {
    public static ArrayList<DetalleCompra> listaDC = new ArrayList<DetalleCompra>();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String ruta = request.getRequestURI();
        String accion = Ayudante.getAccion(ruta);
        
        switch(accion){
            case "mostrarLibro":                
                try (PrintWriter out = response.getWriter()) {
                    out.println("<table class=\"table\">\n" +
"              	       	<thead>\n" +
"	       		<th>Cantidad</th>\n" +
"	       		<th>Titulo</th>\n" +
"	       		<th>Idioma</th>\n" +
"	       		<th>Precio</th>\n" +
"	       		<th>Accion</th>\n" +
"                     	</thead>\n ");	       
                    int i=0;
                    for (DetalleCompra d : CompraServlet.listaDC){
                        out.println("<tbody>");
                        out.println("<td>"+d.getCantidad()+"</td>");
                        out.println("<td>"+d.getTitulo()+"</td>");
                        out.println("<td>"+d.getIdioma()+"</td>");
                        out.println("<td>"+d.getPrecio()+"</td>");
                        out.println("<td><span class='btn btn-danger eliLibro' onclick='eliminarLibro("+i+","+d.getCantidad()+","+d.getPrecio()+")'><spam class=\"glyphicon glyphicon-remove\"></spam></span></td>");
                        i++;
                        out.println("</tbody>");                                                
                    }                    
                }
                break;
            case "agregarLibro":
                String isbn = request.getParameter("isbn");
                String titulo = request.getParameter("titulo");
                int idIdioma = Integer.parseInt(request.getParameter("idIdioma"));
                String idioma = request.getParameter("idioma");
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                int precio = Integer.parseInt(request.getParameter("precio"));
                DetalleCompra dc = new DetalleCompra(isbn,titulo,idioma,idIdioma,cantidad,precio);
                CompraServlet.listaDC.add(dc);
                response.sendRedirect(request.getContextPath()+"/Compra/mostrarLibro");

                break;
            case "eliminarLibro":
                int i = Integer.parseInt(request.getParameter("i"));
                CompraServlet.listaDC.remove(i);
                response.sendRedirect(request.getContextPath()+"/Compra/mostrarLibro");
                break;
            case "insertar":                                
                DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Calendar cal = Calendar.getInstance();
                String fecha = sdf.format(cal.getTime());
                //BRINGING VALUES
                int distribuidor = Integer.parseInt(request.getParameter("distribuidor"));
                int idMetodo = Integer.parseInt(request.getParameter("idMetodo"));
                
                //INSERTING FACTURA
                int subtotal=0;
                int total=0;
                for(DetalleCompra d : CompraServlet.listaDC){
                    subtotal = subtotal + d.getPrecio() * d.getCantidad();
                }
                FacturaDAO dao = new FacturaDAO();
                dao.insertar(new FacturaBoleta(0,fecha,subtotal, (int) (subtotal*0.19),"",idMetodo));
                
                
                //Inserting libros
                for(DetalleCompra d : CompraServlet.listaDC){
                    
                }
                
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
