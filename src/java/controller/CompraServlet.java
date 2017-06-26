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
import model.dao.LibroDAO;
import model.dao.compra.CompraDAO;
import model.dao.compra.DetalleCompraDAO;
import model.dao.compra.FacturaDAO;
import model.dto.FacturaBoleta;
import model.dto.Libro;
import model.dto.compra.Compra;
import model.dto.compra.DetalleCompra;
import util.Ayudante;
import util.CurrentID;

/**
 *
 * @author Ramon Paris
 */
public class CompraServlet extends HttpServlet {
    public static ArrayList<DetalleCompra> listaDC = new ArrayList<DetalleCompra>();
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
        CurrentID id = new CurrentID();
        CompraDAO dao = new CompraDAO();
        switch(accion){
            case "mostrarLibro":                                
                    out.println("<table class=\"table\">\n" +
"              	       	<thead>\n" +
"	       		<th>Cantidad</th>\n" +
"	       		<th>Titulo</th>\n" +
"	       		<th>Idioma</th>\n" +
"	       		<th>Precio</th>\n" +
"	       		<th>Accion</th>\n" +
"                     	</thead>\n ");	       
                    int i=0;
                    for (DetalleCompra d : CompraServlet.listaDC){
                        out.println("<tbody>");
                        out.println("<td>"+d.getCantidad()+"</td>");
                        out.println("<td>"+d.getTitulo()+"</td>");
                        out.println("<td>"+d.getIdioma()+"</td>");
                        out.println("<td>"+d.getPrecio()+"</td>");
                        out.println("<td><span class='btn btn-danger eliLibro' onclick='eliminarLibro("+i+","+d.getCantidad()+","+d.getPrecio()+")'><spam class=\"glyphicon glyphicon-remove\"></spam></span></td>");
                        i++;
                        out.println("</tbody>");                                                
                    }                                    
                break;
            case "agregarLibro":
                String isbn = request.getParameter("isbn");
                String titulo = request.getParameter("titulo");
                int idIdioma = Integer.parseInt(request.getParameter("idIdioma"));
                String idioma = request.getParameter("idioma");
                int cantidad = Integer.parseInt(request.getParameter("cantidad"));
                int precio = Integer.parseInt(request.getParameter("precio"));
                DetalleCompra dc = new DetalleCompra(isbn,titulo,idioma,idIdioma,cantidad,precio);
                CompraServlet.listaDC.add(dc);
                response.sendRedirect(request.getContextPath()+"/Compra/mostrarLibro");

                break;
            case "eliminarLibro":
                int index = Integer.parseInt(request.getParameter("i"));
                CompraServlet.listaDC.remove(index);
                response.sendRedirect(request.getContextPath()+"/Compra/mostrarLibro");
                break;
            case "insertar":                      
                
                DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Calendar cal = Calendar.getInstance();
                String fecha = sdf.format(cal.getTime());
                //BRINGING VALUES
                int distribuidor = Integer.parseInt(request.getParameter("distribuidor"));
                int idMetodo = Integer.parseInt(request.getParameter("idMetodo"));
                
                //INSERTING FACTURA
                int subtotal=0;
                int total=0;
                for(DetalleCompra d : CompraServlet.listaDC){
                    subtotal = subtotal + d.getPrecio() * d.getCantidad();
                }
                FacturaDAO factura = new FacturaDAO();
                
                if (factura.insertar(new FacturaBoleta(0,fecha,subtotal, (int) (subtotal*0.19),"",idMetodo)) > 0){
                    out.println("Factura insertada");
                }else{
                    request.setAttribute("error", "Error al insertar factura");
                    request.getRequestDispatcher(request.getContextPath()+"/Error.jsp").forward(request, response);
                }
                
                CompraDAO compra = new CompraDAO();
                if (compra.insertar(new Compra(0,0,distribuidor,id.currentId("factura"))) > 0){
                    
                }else{
                    request.setAttribute("error", "Error al insertar compra");
                    request.getRequestDispatcher(request.getContextPath()+"/Error.jsp").forward(request, response);
                }
                
                
                //Inserting libros
                LibroDAO libro = new LibroDAO();
                Libro l;
                for(DetalleCompra d : CompraServlet.listaDC){                    
                    for (int k =1;k<=d.getCantidad();k++){
                        l = new Libro();
                        l.setIsbn(d.getIsbn());
                        l.setIdEstado(1);
                        l.setNroSerie(0);
                        out.println("Item: "+k );           
                        //Insertar libro
                        if (libro.insertar(l) > 0){
                            
                        }else{
                            request.setAttribute("error", "Error al insertar Libro");
                            request.getRequestDispatcher(request.getContextPath()+"/Error.jsp").forward(request, response);
                        }
                        
                        //Insertar libro-compra
                        if (libro.insertarMuchosAMuchos("compra", id.currentId("compra"), id.currentId("libro")) > 0){
                            
                        }else{
                            request.setAttribute("error", "Error al insertar libro_compra");
                            request.getRequestDispatcher(request.getContextPath()+"/Error.jsp").forward(request, response);
                        }
                        
                        //Insertar libro-idioma
                        if (libro.insertarMuchosAMuchos("idioma", d.getIdIdioma(), id.currentId("libro")) > 0){
                        
                        }else{
                            request.setAttribute("error", "Error al insertar libro_idioma");
                            request.getRequestDispatcher(request.getContextPath()+"/Error.jsp").forward(request, response);
                        }                       
                    }
                }
                
                //Éxito
//                response.sendRedirect(request.getContextPath()+"/Compra/exito");
                response.sendRedirect(request.getContextPath()+"/Libro/Transacciones/Comprar/exito.jsp");
                break;
            case "listar":                
                ArrayList<Compra> lista = dao.listar();
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("../Libro/Transacciones/Comprar/lista.jsp").forward(request, response);
                break;
            case "recibo":
                int idx = Integer.parseInt(request.getParameter("id"));
                FacturaDAO factDAO = new FacturaDAO();
                FacturaBoleta f = factDAO.buscar(idx);
                int price=0;
                DetalleCompraDAO dcDAO = new DetalleCompraDAO();                
                ArrayList<DetalleCompra> detalles = dcDAO.listar(idx);
                for (DetalleCompra d : detalles){
                    price = price + (d.getPrecio()*d.getCantidad());
                }
                request.setAttribute("factura", f);
                request.setAttribute("lista", detalles);
                request.setAttribute("subtotal", price);
                request.getRequestDispatcher("../Libro/Transacciones/Comprar/recibo.jsp").forward(request, response);
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
