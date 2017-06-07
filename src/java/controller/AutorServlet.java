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
import model.dao.AutorDAO;
import model.dto.Autor;
import util.Ayudante;
import util.Conexion;

/**
 *
 * @author Ramon Paris
 */
public class AutorServlet extends HttpServlet {

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
        AutorDAO dao = new AutorDAO();
        Autor a;
        ArrayList<Autor> lista = new ArrayList<Autor>();
        
        switch(accion){
            case "listar":
                lista = dao.listar();
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("../Libro/Titulo/Autor/index.jsp").forward(request, response);                
                break;
            case "eliminar":
                int id = Integer.parseInt(request.getParameter("id"));
                int res = dao.eliminar(id);
                if (res >0){
                    response.sendRedirect(("../Autor/listar"));
                }else{
                    request.getRequestDispatcher("../Error.jsp").forward(request, response);
                }                
                break;
            case "insertar":
                a = new Autor();
                a.setNombre(request.getParameter("nombre"));
                a.setApePat(request.getParameter("apePat"));
                a.setApeMat(request.getParameter("apeMat"));
                if (dao.insertar(a)>0){
                    response.sendRedirect(("../Autor/listar"));
                }else
                    request.getRequestDispatcher("../Error.jsp").forward(request, response);
                break;
            case "modificar":
                a = new Autor();
                a.setIdAutor(Integer.parseInt(request.getParameter("id")));
                a.setNombre(request.getParameter("nombre"));
                a.setApePat(request.getParameter("apePat"));
                a.setApeMat(request.getParameter("apeMat"));
                if (dao.modificar(a)>0){
                    response.sendRedirect(("../Autor/listar"));
                }else
                    request.getRequestDispatcher("../Error.jsp").forward(request, response);
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
