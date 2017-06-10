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
import model.dao.compra.FacturaDAO;
import model.dto.FacturaBoleta;
import util.Ayudante;

/**
 *
 * @author Ramon Paris
 */
public class FacturaServlet extends HttpServlet {

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
        FacturaDAO dao = new FacturaDAO();
        FacturaBoleta f;
        ArrayList<FacturaBoleta> lista = new ArrayList<FacturaBoleta>();
        
        switch(accion){
            case "listar":
                lista = dao.listar();
                request.setAttribute("lista", lista);                
                request.getRequestDispatcher("../Libro/Transacciones/index.jsp").forward(request, response);                
                break;
            case "insertar":
                f = new FacturaBoleta();
                f.setFechaCompra(request.getParameter("fecha")); 
                f.setIva(19);
                f.setMetodoPago(Integer.parseInt(request.getParameter("idMetodo")));
                f.setPrecioNeto(Integer.parseInt(request.getParameter("neto")));                
                f.setPrecioIva((int)(f.getPrecioNeto()*1.19));
                if (dao.insertar(f)>0){
                   response.sendRedirect(request.getContextPath()+"/Factura/listar");
                }                
                break;
            case "modificar":
                f = new FacturaBoleta();
                f.setFolio(Integer.parseInt(request.getParameter("folio")));
                f.setFechaCompra(request.getParameter("fecha")); 
                f.setIva(19);
                f.setMetodoPago(Integer.parseInt(request.getParameter("idMetodo")));
                f.setPrecioNeto(Integer.parseInt(request.getParameter("neto")));                
                f.setPrecioIva((int)(f.getPrecioNeto()*1.19));
                if (dao.modificar(f)>0){
                   response.sendRedirect(request.getContextPath()+"/Factura/listar");
                }                
                break;
            case "eliminar":
                if (dao.eliminar(Integer.parseInt(request.getParameter("id")))>0){
                   response.sendRedirect(request.getContextPath()+"/Factura/listar"); 
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
