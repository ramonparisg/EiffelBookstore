/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.DetalleTransaccionDAO;
import model.dto.DetalleTransaccion;
import model.dto.Libro;
import model.dto.compra.DetalleCompra;
import util.Ayudante;

/**
 *
 * @author Ramon Paris
 */
public class VenderServlet extends HttpServlet {
    public static ArrayList<DetalleTransaccion> listaDV = new ArrayList<DetalleTransaccion>();
    public static ArrayList<Libro> Shopping = new ArrayList<Libro>();
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
        response.setContentType("text/html;charset=UTF-8");
        String ruta = request.getRequestURI();
        PrintWriter out = response.getWriter();
        String accion = Ayudante.getAccion(ruta);
        DetalleTransaccionDAO detalleDAO = new DetalleTransaccionDAO();
        switch(accion){
            case "listarDisponibles":
               
                listaDV = detalleDAO.listar();
                request.setAttribute("lista", listaDV);
                request.getRequestDispatcher("../Libro/Transacciones/Vender/vender.jsp").forward(request, response);
                break;
            case "agregarLibro":
                int index = Integer.parseInt(request.getParameter("index"));                              
                Shopping.add(detalleDAO.libro(listaDV.get(index).getIsbn(), listaDV.get(index).getIdIdioma()));                
                response.sendRedirect(request.getContextPath()+"/Vender/mostrarLibro");
                break;    
            case "mostrarLibro":      
                int acum=0;
                    out.println("<table class=\"table\">\n" +
"              	       	<thead>\n" +
"	       		<th>Titulo</th>\n" +
"	       		<th>Idioma</th>\n" +
"	       		<th>Precio</th>\n" +
"	       		<th>Accion</th>\n" +
"                     	</thead>\n ");	       
                    int i=0;
                   for (Libro l : VenderServlet.Shopping){
                        out.println("<tbody>");
                        out.println("<td>"+detalleDAO.buscarPorNroSerie(l.getNroSerie()).getTitulo()+"</td>");
                        out.println("<td>"+detalleDAO.buscarPorNroSerie(l.getNroSerie()).getIdioma()+"</td>");                        
                        out.println("<td>"+detalleDAO.buscarPorNroSerie(l.getNroSerie()).getPrecio()+"</td>");
                        out.println("<td><span class='btn btn-danger eliLibro' onclick='eliminarLibro("+i+","+detalleDAO.buscarPorNroSerie(l.getNroSerie()).getPrecio()+")'><spam class=\"glyphicon glyphicon-remove\"></spam></span></td>");
                        i++;
                        out.println("</tbody>");     
                        acum = acum + detalleDAO.buscarPorNroSerie(l.getNroSerie()).getPrecio();
                    }          
                   out.println("</table>");
                   out.println("Subtotal: <b>$<spam id='subtotal'>" + acum +"</spam></b><br>");
                   out.println("IVA: <b>$<spam id='iva'>" + acum*0.19 +"</spam></b><br>");
                   out.println("Total: <b>$<spam id='total'>" + acum *1.19+"</spam></b>");
                break;
            case "eliminarLibro":
                index = Integer.parseInt(request.getParameter("i"));
                VenderServlet.Shopping.remove(index);
                response.sendRedirect(request.getContextPath()+"/Vender/mostrarLibro");
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
