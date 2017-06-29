/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.DetalleTransaccionDAO;
import model.dao.LibroDAO;
import model.dao.Venta.BoletaDAO;
import model.dao.Venta.VentaDAO;
import model.dao.compra.FacturaDAO;
import model.dto.DetalleTransaccion;
import model.dto.FacturaBoleta;
import model.dto.Libro;
import model.dto.compra.DetalleCompra;
import model.dto.venta.Venta;
import util.Ayudante;
import util.CurrentID;

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
        VentaDAO ventaDAO = new VentaDAO();
        CurrentID id = new CurrentID();
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
            case "insertar":                      
                
                DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Calendar cal = Calendar.getInstance();
                String fecha = sdf.format(cal.getTime());
                //BRINGING VALUES
                int trabajador = Integer.parseInt(request.getParameter("trabajador"));
                int cliente = Integer.parseInt(request.getParameter("cliente"));
                int idMetodo = Integer.parseInt(request.getParameter("idMetodo"));
                
                //INSERTING Boleta
                int subtotal=0;
                int total=0;
                DetalleTransaccion dt = new DetalleTransaccion();
                for(Libro x : Shopping){
                    dt = detalleDAO.buscarPorNroSerie(x.getNroSerie());
                    subtotal = subtotal + dt.getPrecio();
                }
                BoletaDAO boleta = new BoletaDAO();
                
                if (boleta.insertar(new FacturaBoleta(0,fecha,subtotal, (int) (subtotal*0.19),"",idMetodo)) > 0){
                    out.println("Boleta insertada");
                }else{
                    request.setAttribute("error", "Error al insertar factura");
                    request.getRequestDispatcher(request.getContextPath()+"/Error.jsp").forward(request, response);
                }
                
                VentaDAO venta = new VentaDAO();
                if (venta.insertar(new Venta(0,0,id.currentId("boleta"),cliente,trabajador)) > 0){
                    
                }else{
                    request.setAttribute("error", "Error al insertar compra");
                    request.getRequestDispatcher(request.getContextPath()+"/Error.jsp").forward(request, response);
                }
                
                
                //Inserting libros
                LibroDAO libro = new LibroDAO();
                Libro l = null;
                
                for(Libro li : Shopping){                    
                    
                    l = new Libro();                    
                    l.setIdEstado(2);
                    l.setNroSerie(li.getNroSerie());                              
                    //Modificar libro
                    if (libro.modificar(l) > 0){

                    }else{
                        request.setAttribute("error", "Error al insertar Libro");
                        request.getRequestDispatcher(request.getContextPath()+"/Error.jsp").forward(request, response);
                    }

                    //Insertar libro-venta
                    if (libro.insertarMuchosAMuchos("venta", id.currentId("venta"), li.getNroSerie()) > 0){

                    }else{
                        request.setAttribute("error", "Error al insertar libro_venta");
                        request.getRequestDispatcher(request.getContextPath()+"/Error.jsp").forward(request, response);
                    }                                           
                }
                
                //Éxito
//                response.sendRedirect(request.getContextPath()+"/Compra/exito");
                response.sendRedirect(request.getContextPath()+"/Vender/listar");
                break;
                
            case "listar":                
                ArrayList<Venta> lista = ventaDAO.listar();
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("../Libro/Transacciones/Vender/lista.jsp").forward(request, response);
                break;
            case "recibo":                
                int idx = Integer.parseInt(request.getParameter("id"));
                BoletaDAO bolDAO = new BoletaDAO();
                FacturaBoleta f = bolDAO.buscar(idx);
                Venta v = ventaDAO.buscar(idx);
                int price=0;                             
                ArrayList<DetalleTransaccion> detalles = detalleDAO.listarVentas(idx);
                for (DetalleTransaccion d : detalles){
                    price = price + (d.getPrecio());
                }
                request.setAttribute("factura", f);
                request.setAttribute("venta", v);
                request.setAttribute("lista", detalles);
                request.setAttribute("subtotal", price);
                request.getRequestDispatcher("../Libro/Transacciones/Vender/recibo.jsp").forward(request, response);
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
