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
import model.dao.Arriendo.ArriendoDAO;
import model.dao.Arriendo.DetalleArriendoDAO;
import model.dao.DetalleTransaccionDAO;
import model.dao.LibroDAO;
import model.dto.DetalleTransaccion;
import model.dto.Libro;
import model.dto.arriendo.Arriendo;
import model.dto.arriendo.DetalleArriendo;
import util.Ayudante;
import util.CurrentID;
import util.MaquinaDelTiempo;

/**
 *
 * @author Ramon Paris
 */
public class ArrendarServlet extends HttpServlet {
        public static ArrayList<Libro> Shopping = new ArrayList<Libro>();
        public static ArrayList<DetalleTransaccion> detalles = new ArrayList<DetalleTransaccion>();
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
        
        DetalleArriendoDAO detalleDAO  = new DetalleArriendoDAO();
        ArriendoDAO arriendoDAO = new ArriendoDAO();
        ArrayList<Arriendo> arriendos = new ArrayList<Arriendo>();
        
        DetalleTransaccionDAO transaccionDAO = new DetalleTransaccionDAO();
        
        
        CurrentID id = new CurrentID();
        switch(accion){
            case "listarDisponibles":
                detalles = transaccionDAO.listar();
                request.setAttribute("lista", detalles);
                request.getRequestDispatcher("../Libro/Transacciones/Arrendar/librosDisponibles.jsp").forward(request, response);
                break;
            case "agregarLibro":                
                out.println("hola");
                int index = Integer.parseInt(request.getParameter("index"));                              
                ArrendarServlet.Shopping.add(transaccionDAO.libro(detalles.get(index).getIsbn(), detalles.get(index).getIdIdioma()));                
                response.sendRedirect(request.getContextPath()+"/Arrendar/mostrarLibro");
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
                   for (Libro l : ArrendarServlet.Shopping){
                        out.println("<tbody>");
                        out.println("<td>"+transaccionDAO.buscarPorNroSerie(l.getNroSerie()).getTitulo()+"</td>");
                        out.println("<td>"+transaccionDAO.buscarPorNroSerie(l.getNroSerie()).getIdioma()+"</td>");                        
                        out.println("<td>"+transaccionDAO.buscarPorNroSerie(l.getNroSerie()).getPrecio()+"</td>");
                        out.println("<td><span class='btn btn-danger eliLibro' onclick='eliminarLibro("+i+","+transaccionDAO.buscarPorNroSerie(l.getNroSerie()).getPrecio()+")'><spam class=\"glyphicon glyphicon-remove\"></spam></span></td>");
                        i++;
                        out.println("</tbody>");     
                        acum = acum + transaccionDAO.buscarPorNroSerie(l.getNroSerie()).getPrecio();
                    }          
                   out.println("</table>");
                   out.println("Subtotal: <b>$<spam id='subtotal'>" + acum +"</spam></b><br>");
                   out.println("IVA: <b>$<spam id='iva'>" + acum*0.19 +"</spam></b><br>");
                   out.println("Total: <b>$<spam id='total'>" + acum *1.19+"</spam></b>");                
                break;
            case "insertar":
                DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Calendar cal = Calendar.getInstance();
                String fechaArriendo = sdf.format(cal.getTime());
                MaquinaDelTiempo m = new MaquinaDelTiempo();
                String fechaEstiDev = sdf.format(m.sumarRestarDiasFecha(cal.getTime(), 10));
                
                //Bringing values
                int trabajador = Integer.parseInt(request.getParameter("trabajador"));
                int cliente = Integer.parseInt(request.getParameter("cliente"));
                int dias = Integer.parseInt(request.getParameter("dias"));
                Arriendo a = new Arriendo();
                a.setRutCliente(cliente);
                a.setRutTrabajador(trabajador);
                //Inserting arriendos
                if (arriendoDAO.insertar(a) > 0){
                    out.println("Arriendo insertado");
                }
                
                DetalleArriendo d = new DetalleArriendo();
                DetalleTransaccion dt = null;
                LibroDAO lDAO = new LibroDAO();
                Libro l = null;
                for (Libro li : Shopping){
                    l = new Libro();
                    l.setIdEstado(3);
                    l.setNroSerie(li.getNroSerie());
                    //Updating book status
                    if (lDAO.modificar(l)>0){
                        out.println("Libro modificado");
                    }             
                    
                    //Inserting details arriendo
                    d = new DetalleArriendo();
                    d.setIdArriendo(id.currentId("arriendo"));
                    d.setNroSerie(li.getNroSerie());
                    d.setfArriendo(fechaArriendo);
                    d.setfDevEsti(fechaEstiDev);
                    d.setfDevReal("0001-01-01");
                    d.setMulta(0);
                    dt = transaccionDAO.buscarPorNroSerie(li.getNroSerie());
                    d.setSubtotal(dt.getPrecio() + dias*1000);
                    d.setMulta(0);
                    d.setTotal(d.getSubtotal());
                    
                    if (detalleDAO.insertar(d) > 0){
                        out.println("inserto detalle");
                    }  
                        
                }
                
                response.sendRedirect(request.getContextPath()+"/Arrendar/listar");
                break;
            case "listar":                
                ArrayList<Arriendo> lista = arriendoDAO.listar();
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("../Libro/Transacciones/Arrendar/lista.jsp").forward(request, response);
                break;
            case "recibo":                
                int idx = Integer.parseInt(request.getParameter("id"));                
                Arriendo arr = arriendoDAO.buscar(idx);                                                
                ArrayList<DetalleArriendo> listaDetalleA = detalleDAO.listarArriendo(arr.getId());
                ArrayList<DetalleTransaccion> listaDetalleT =  new ArrayList<DetalleTransaccion>();
                int price = 0;
                for (DetalleArriendo det : listaDetalleA){
                    price = price + (det.getTotal());
                    listaDetalleT.add(transaccionDAO.buscarPorNroSerie(det.getNroSerie()));
                }
                request.setAttribute("arriendo", arr);                
                request.setAttribute("lista", listaDetalleA);
                request.setAttribute("titulos", listaDetalleT);
                request.setAttribute("total", price);
                request.getRequestDispatcher("../Libro/Transacciones/Arrendar/recibo.jsp").forward(request, response);
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
