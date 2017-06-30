<%-- 
    Document   : comprar
    Created on : 17-06-2017, 22:45:28
    Author     : Ramon Paris
--%>

<%@page import="controller.ArrendarServlet"%>
<%@page import="util.MaquinaDelTiempo"%>
<%@page import="model.dto.DetalleTransaccion"%>
<%@page import="model.dao.DetalleTransaccionDAO"%>
<%@page import="controller.VenderServlet"%>
<%@page import="model.dto.Libro"%>
<%@page import="model.dto.Persona"%>
<%@page import="model.dao.PersonaDAO"%>
<%@page import="model.dto.compra.Distribuidor"%>
<%@page import="model.dao.compra.DistribuidorDAO"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="model.dto.compra.DetalleCompra"%>
<%@page import="controller.CompraServlet"%>
<%@page import="model.dao.AuxiliarDAO"%>
<%@page import="model.dto.Auxiliar"%>
<%@page import="model.dto.Titulo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.TituloDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

        <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../../../template.jsp" />
        <title>JSP Page</title>
    </head>
    <body class="container">
        <% DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Calendar cal = Calendar.getInstance();                        
            MaquinaDelTiempo m = new MaquinaDelTiempo();
        %>
        <h1>Arrendar <span class="pull-right"><%=sdf.format(cal.getTime())%></span></h1>
        
           <form method="post" action="<%=request.getContextPath()%>/Arrendar/insertar">
               <label>Cliente</label>
           <select name="cliente" class="form-control" id="distribuidor">    
                        <option></option>
                        <%  
                            
                            PersonaDAO cliente = new PersonaDAO();
                            ArrayList<Persona> listaCliente = cliente.listar("cliente");
                            
                            
                            for(Persona c : listaCliente){ 
                        %>
                        <option value="<%=c.getRut() %>" name="cliente" required><%=c.getNombre() + " " + c.getApePat() + " " + c.getApeMat()  %></option>
                        
                        
                        <% } %>    
                    </select> 
                <label>Trabajador</label>
           <select name="trabajador" class="form-control" id="distribuidor">    
                        <option></option>
                        <%  
                            
                            PersonaDAO trabajador = new PersonaDAO();
                            ArrayList<Persona> listaTrabajador = cliente.listar("trabajador");
                            
                            
                            for(Persona t : listaTrabajador){ 
                        %>
                        <option value="<%=t.getRut() %>" name="trabajador" required><%=t.getNombre() + " " + t.getApePat() + " " + t.getApeMat()  %></option>
                        
                        
                        <% } %>    
                    </select>  
            <table class="table">
                <thead>
                    <th>Nro Serie</th>
                    <th>Titulo</th>
                    <th>Idioma</th>
                    <th>Precio</th>  
                </thead>
                 <%  DetalleTransaccionDAO detalleDAO = new DetalleTransaccionDAO();
                     int acum=0;
                     DetalleTransaccion dt = new DetalleTransaccion();
                     for (Libro l : ArrendarServlet.Shopping){%> 
                 <tbody>
                     <%
                          dt = detalleDAO.buscarPorNroSerie(l.getNroSerie());
                         
                     %>
                    <td><%=l.getNroSerie() %></td>
                    <td><%=dt.getTitulo() %></td>
                    <td><%=dt.getIdioma() %></td>
                    <td><%=dt.getPrecio() %></td>
                 </tbody>
                 
                 <% acum = acum + dt.getPrecio();  } 
%>
            </table>
            
            
            <div class="form-group">
                <label>Días arrendados</label>
                <input class="form-control" type="number" name="dias" required> 
            </div>
                             
       
        Subtotal: <input type="text" disabled="true" value="<%=acum %>" name="subtotal" id="subtotal"><br>
        IVA 19%: <input type="text" disabled="true" value="<%=acum*0.19 %>" name="iva" id="iva"><br>
        Total: <input type="text" disabled="true" value="<%=acum*1.19 %>" name="total" id="total"><br>
       <div class="form-group">
        <input type="submit" class="form-control btn btn-success"/>
       </div>
       </form>
       <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous">  </script>       
        
        
    </body>
</html>
    

