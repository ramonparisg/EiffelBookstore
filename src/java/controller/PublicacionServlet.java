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
import model.dao.AuxiliarDAO;
import model.dto.Auxiliar;
import util.Ayudante;

/**
 *
 * @author Ramon Paris
 */
public class PublicacionServlet extends HttpServlet {

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
        AuxiliarDAO dao = new AuxiliarDAO();
        Auxiliar categoria;
        ArrayList<Auxiliar> lista = new ArrayList<Auxiliar>();
        String tabla = "Publicacion";
        switch(accion){
            case "listar":
                lista = dao.listar(tabla);
                request.setAttribute("lista", lista);
                request.setAttribute("tabla", tabla);
                request.getRequestDispatcher("../Libro/Titulo/Auxiliar/index.jsp").forward(request, response);
                break;
            case "insertar":
                categoria = new Auxiliar();
                categoria.setDetalle(request.getParameter("detalle"));
                if (dao.agregar(categoria, tabla) >0){
                    response.sendRedirect(("../"+tabla+"/listar"));
                }else
                    request.getRequestDispatcher(request.getContextPath()+"/Error.jsp").forward(request, response);
                break;
            case "modificar":
                categoria = new Auxiliar();
                categoria.setId(Integer.parseInt(request.getParameter("id")));
                categoria.setDetalle(request.getParameter("detalle"));
                if (dao.modificar(categoria, tabla) >0){
                    response.sendRedirect(("../"+tabla+"/listar"));
                }else
                    request.getRequestDispatcher(request.getContextPath()+"/Error.jsp").forward(request, response);
                break;
            case "eliminar":
                int id = Integer.parseInt(request.getParameter("id"));
                int res = dao.eliminar(id,tabla);
                if (res >0){
                    response.sendRedirect(("../"+tabla+"/listar"));
                }else{
                    request.getRequestDispatcher("../Error.jsp").forward(request, response);
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
