/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.Arriendo.ArriendoDAO;
import model.dao.Arriendo.DetalleArriendoDAO;
import model.dao.DetalleTransaccionDAO;
import model.dao.LibroDAO;
import model.dto.DetalleTransaccion;
import model.dto.Libro;
import model.dto.arriendo.Arriendo;
import model.dto.arriendo.DetalleArriendo;
import util.Ayudante;
import util.MaquinaDelTiempo;

/**
 *
 * @author Ramon Paris
 */
public class DevolverServlet extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        String accion = Ayudante.getAccion(ruta);
        ArriendoDAO arriendoDAO = new ArriendoDAO();
        DetalleTransaccionDAO transaccionDAO = new DetalleTransaccionDAO();
        DetalleArriendoDAO detalleDAO  = new DetalleArriendoDAO();
        
        switch(accion){
            case "listar":                                
                ArrayList<Arriendo> lista = arriendoDAO.listar();
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("../Libro/Transacciones/Devolver/lista.jsp").forward(request, response);
                break;
            case "devolver":
                int idx = Integer.parseInt(request.getParameter("id"));                
                Arriendo arr = arriendoDAO.buscar(idx);                                                
                ArrayList<DetalleArriendo> listaDetalleA = detalleDAO.listarArriendo(arr.getId());
                ArrayList<DetalleTransaccion> listaDetalleT =  new ArrayList<DetalleTransaccion>();                
                for (DetalleArriendo det : listaDetalleA){                    
                    listaDetalleT.add(transaccionDAO.buscarPorNroSerie(det.getNroSerie()));
                }
                request.setAttribute("arriendo", arr);                
                request.setAttribute("lista", listaDetalleA);
                request.setAttribute("titulos", listaDetalleT);                
                request.getRequestDispatcher("../Libro/Transacciones/Devolver/formulario.jsp").forward(request, response);
                break;
            case "insertar":
                int index = Integer.parseInt(request.getParameter("index"));
                int nroSerie = Integer.parseInt(request.getParameter("nro"));
                int id = Integer.parseInt(request.getParameter("id"));
                String fecha = request.getParameter("fecha");
                LibroDAO l = new LibroDAO();
                DetalleArriendoDAO d = new DetalleArriendoDAO();
                DetalleArriendo da = d.buscar(id, nroSerie);
                MaquinaDelTiempo m = new MaquinaDelTiempo();
                int dif =0,multa=0;
                
                try {
                    dif = m.DiasDiferencia(da.getfArriendo(), da.getfDevEsti());
                } catch (ParseException ex) {
                    Logger.getLogger(DevolverServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
        
                if (dif > 0){
                    multa = dif * 2000;
                }
                out.println(nroSerie + " " + id + " " + fecha);
                l.modificar(new Libro(nroSerie,1,"0"));
                if (d.modificar(new DetalleArriendo(id,nroSerie,fecha,multa+da.getSubtotal(),multa))>0){
                    out.println("<div style='margin:10px auto;' class='text-center'><i class=\"fa fa-check-circle\" aria-hidden=\"true\" style='color:green;font-size:50px;'></i> <br> ¡Devolución exitosa!</div>");
                }else
                    out.println("<div style='margin:10px auto;'><i class=\"fa fa-exclamation-circle\" aria-hidden=\"true\" style='color:red;font-size:50px;'></i> <br> ¡Algo falló!</div>");
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
