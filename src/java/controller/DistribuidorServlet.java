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
import model.dao.compra.DistribuidorDAO;
import model.dto.compra.Distribuidor;
import util.Ayudante;

/**
 *
 * @author Ramon Paris
 */
public class DistribuidorServlet extends HttpServlet {

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
        DistribuidorDAO dao = new DistribuidorDAO();
        Distribuidor d;
        ArrayList<Distribuidor> lista = new ArrayList<Distribuidor>();
        switch (accion){
            case "listar":
                lista = dao.listar();
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("../Libro/Transacciones/Comprar/Distribuidor/index.jsp").forward(request, response);                
                break;
            case "insertar":
                d = new Distribuidor();
                d.setRut(Integer.parseInt(request.getParameter("rut")));
                d.setAnyo(Integer.parseInt(request.getParameter("anyo")));
                d.setDireccion(request.getParameter("dir"));
                d.setNombre(request.getParameter("nombre"));
                d.setTelefono(request.getParameter("tlf"));
                if (dao.insertar(d)>0)
                    response.sendRedirect(request.getContextPath()+"/Distribuidor/listar");
                else
                    response.sendRedirect(request.getContextPath()+"/Error.jsp");
                break;
            case "modificar":
                d = new Distribuidor();
                d.setRut(Integer.parseInt(request.getParameter("rut")));
                d.setAnyo(Integer.parseInt(request.getParameter("anyo")));
                d.setDireccion(request.getParameter("dir"));
                d.setNombre(request.getParameter("nombre"));
                d.setTelefono(request.getParameter("tlf"));
                if (dao.modificar(d)>0)
                    response.sendRedirect(request.getContextPath()+"/Distribuidor/listar");
                else
                    response.sendRedirect(request.getContextPath()+"/Error.jsp");
                break;
            case "eliminar":
                if (dao.eliminar(Integer.parseInt(request.getParameter("id")))>0)
                    response.sendRedirect(request.getContextPath()+"/Distribuidor/listar");
                else
                    response.sendRedirect(request.getContextPath()+"/Error.jsp");                                    
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
