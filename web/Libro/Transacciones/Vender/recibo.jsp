<%-- 
    Document   : recibo
    Created on : 18-06-2017, 12:51:54
    Author     : Ramon Paris
--%>

<%@page import="model.dto.venta.Venta"%>
<%@page import="model.dto.DetalleTransaccion"%>
<%@page import="model.dto.compra.DetalleCompra"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dto.FacturaBoleta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="../../../template.jsp" />
    </head>
    <body>
        <%
            FacturaBoleta f = (FacturaBoleta) request.getAttribute("factura");
            ArrayList<DetalleTransaccion> lista = (ArrayList) request.getAttribute("lista");
            Venta v = (Venta) request.getAttribute("venta");
            int subtotal = 0;           
            

        %>
        <div class="" style="width:800px;margin: 100px auto;">
        <h1 class="text-center">Recibo de Venta </h1>
        
        <h3 class="">Nro de Boleta <b><%=f.getFolio() %></b></h3>
        Fecha de compra: <b><%=f.getFechaCompra() %></b>
        
        <p>Trabajador: <b> <%=v.getRutTrabajador() %></b> <br>
            Cliente: <b> <%=v.getRutCliente() %></b></p>
        <table class="table table-striped">
           <thead>
                <th>Nro Serie</th>    
                <th>Titulo</th>
                <th>Idioma</th>
                <th>Precio</th>                
            </thead>
            <% 
                for (DetalleTransaccion d : lista){
                    subtotal= subtotal + d.getPrecio();
            %>
            <tbody>
                <td><%=d.getNroserie()%></td>
                <td><%=d.getTitulo() %></td>
                <td><%=d.getIdioma() %></td>
                <td><%=d.getPrecio() %></td>
            </tbody>
            <% }%>
        </table>        
        <div class="text-right" style="font-size: 18px;">
            
        Subtotal: <b>$<%=subtotal %> </b><br>
        Iva: <b>$<%=subtotal * 0.19 %> </b><br>
        Total: <b>$<%=subtotal*1.19 %> </b>
        </div>
        </div>
    </body>
</html>
