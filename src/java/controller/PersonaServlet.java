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
import model.dao.CorreoDAO;
import model.dao.DireccionDAO;
import model.dao.PersonaDAO;
import model.dao.TelefonoDAO;
import model.dto.Correo;
import model.dto.Direccion;
import model.dto.Persona;
import model.dto.Telefono;
import util.Ayudante;

/**
 *
 * @author Ramon Paris
 */
public class PersonaServlet extends HttpServlet {

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
        PersonaDAO dao = new PersonaDAO();
        Persona p;
        
        Direccion d;
        DireccionDAO dDAO = new DireccionDAO();
        
        Correo c;
        CorreoDAO cDAO = new CorreoDAO();
        
        Telefono t;
        TelefonoDAO tdao = new TelefonoDAO();
        
        ArrayList<Persona> lista = new ArrayList<Persona>();
        String tabla= request.getParameter("tabla");
        switch(accion){
            case "listar":
                lista = dao.listar(tabla);
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("../Libro/Persona/listar.jsp").forward(request, response);
                break;
            case "insertar":
                boolean exito=false;
                boolean persona = false;
                boolean email = false;
                boolean tlfno = false;
                boolean dir = false;
                int rut = Integer.parseInt(request.getParameter("rut"));
                String nombre = request.getParameter("nombre");
                String apePat = request.getParameter("apePat");
                String apeMat = request.getParameter("apeMat");
                String fnac = request.getParameter("fnac");
                p=new Persona(rut,nombre,apePat,apeMat,fnac);
                
                int telefono = Integer.parseInt(request.getParameter("telefono"));
                t = new Telefono(telefono,rut);
                
                String correo = request.getParameter("correo"); 
                c = new Correo(correo,rut);
                
                String nombreCalle = request.getParameter("nombreCalle");
                int nroCalle = Integer.parseInt(request.getParameter("nroCalle"));
                d = new Direccion(0,nombreCalle,nroCalle,rut);
                
                if (dao.insertar(p, tabla) > 0)
                    persona=true;
                else
                    persona=false;
                
                if (tdao.insetar(t, tabla)>0)
                    tlfno = true;
                else
                    tlfno = false;
                
            
                if (cDAO.insetar(c, tabla)>0)
                    email = true;
                else
                    email = false;
                
                
                if (dDAO.insertar(d, tabla)>0)
                    dir = true;
                else
                    dir = false;
                
                response.sendRedirect("../Persona/listar?tabla="+tabla);
                break;
            case "modificar":
                
                rut = Integer.parseInt(request.getParameter("rut"));
                nombre = request.getParameter("nombre");
                apePat = request.getParameter("apePat");
                apeMat = request.getParameter("apeMat");
                fnac = request.getParameter("fnac");
                p=new Persona(rut,nombre,apePat,apeMat,fnac);
                
                telefono = Integer.parseInt(request.getParameter("telefono"));
                t = new Telefono(telefono,rut);
                
                correo = request.getParameter("correo"); 
                c = new Correo(correo,rut);
                
                nombreCalle = request.getParameter("nombreCalle");
                nroCalle = Integer.parseInt(request.getParameter("nroCalle"));
                d = new Direccion(0,nombreCalle,nroCalle,rut);
                
                dao.insertar(p, tabla);
                
                
                tdao.insetar(t, tabla);
                
                
            
                cDAO.insetar(c, tabla);

                dDAO.insertar(d, tabla);

                response.sendRedirect("../Persona/listar?tabla="+tabla);
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
