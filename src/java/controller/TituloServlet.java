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
import model.dao.TituloDAO;
import model.dto.Titulo;
import util.Ayudante;

/**
 *
 * @author Ramon Paris
 */
public class TituloServlet extends HttpServlet {

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
        TituloDAO dao = new TituloDAO();
        Titulo t;
        ArrayList<Titulo> lista = new ArrayList<Titulo>();
        switch(accion){
            case "listar":
                lista = dao.listar();
                request.setAttribute("lista", lista);                
                request.getRequestDispatcher("../Libro/Titulo/index.jsp").forward(request, response);
                break;
            case "insertar":
                t = new Titulo();
                t.setIsbn((request.getParameter("isbn")));
                t.setNombre(request.getParameter("nombre"));
                t.setAnyoPublicacion(request.getParameter("anyo"));
                t.setIdEditorial(Integer.parseInt(request.getParameter("idEditorial")));
                t.setIdPublicacion(Integer.parseInt(request.getParameter("idPublicacion")));
                t.setNroPaginas(Integer.parseInt(request.getParameter("nro")));
                t.setPrecioReferencia(Integer.parseInt(request.getParameter("precio")));
                if (dao.insertar(t)>0){
                    dao.insertarMuchosAMuchos("autor", Integer.parseInt(request.getParameter("idAutor")), t.getIsbn());                    
                    dao.insertarMuchosAMuchos("categoria", Integer.parseInt(request.getParameter("idCategoria")), t.getIsbn());
                    response.sendRedirect(request.getContextPath()+"/Titulo/listar");
                }else
                    request.getRequestDispatcher("../Error.jsp").forward(request, response);
                break;
            case "modificar":
                t = new Titulo();
                t.setIsbn((request.getParameter("isbn")));
                t.setNombre(request.getParameter("nombre"));
                t.setAnyoPublicacion(request.getParameter("anyo"));
                t.setIdEditorial(Integer.parseInt(request.getParameter("idEditorial")));
                t.setIdPublicacion(Integer.parseInt(request.getParameter("idPublicacion")));
                t.setNroPaginas(Integer.parseInt(request.getParameter("nro")));
                t.setPrecioReferencia(Integer.parseInt(request.getParameter("precio")));
                if (dao.modificar(t)>0){
                    dao.modificarMuchosAMuchos("autor", Integer.parseInt(request.getParameter("idAutor")), t.getIsbn());                    
                    dao.modificarMuchosAMuchos("categoria", Integer.parseInt(request.getParameter("idCategoria")), t.getIsbn());
                    response.sendRedirect(request.getContextPath()+"/Titulo/listar");
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
