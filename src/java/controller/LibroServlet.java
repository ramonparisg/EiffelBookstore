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
import model.dao.LibroDAO;
import model.dto.Libro;
import util.Ayudante;

/**
 *
 * @author Ramon Paris
 */
public class LibroServlet extends HttpServlet {

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
        LibroDAO dao = new LibroDAO();
        Libro l;
        ArrayList<Libro> lista = new ArrayList<Libro>();
        switch(accion){
            case "listar":
                lista = dao.listar();
                request.setAttribute("lista", lista);                
                request.getRequestDispatcher("../Libro/index.jsp").forward(request, response);
                break;
            case "insertar":
                l = new Libro();
                l.setIsbn(request.getParameter("isbn"));
                l.setNroSerie(Integer.parseInt(request.getParameter("nro")));
                l.setIdEstado(Integer.parseInt(request.getParameter("idEstado")));
                if (dao.insertar(l)>0){
                    response.sendRedirect(request.getContextPath()+"/Libro/listar");
                }
                break;
            case "modificar":
                l = new Libro();
                l.setIsbn(request.getParameter("isbn"));
                l.setNroSerie(Integer.parseInt(request.getParameter("nro")));
                l.setIdEstado(Integer.parseInt(request.getParameter("idEstado")));
                if (dao.modificar(l)>0){
                    response.sendRedirect(request.getContextPath()+"/Libro/listar");
                }
                break;
            case "eliminar":
                if (dao.eliminar(request.getParameter("id"))>0){
                    response.sendRedirect(request.getContextPath()+"/Libro/listar");
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
