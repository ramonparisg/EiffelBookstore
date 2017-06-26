<%-- 
    Document   : recibo
    Created on : 18-06-2017, 12:51:54
    Author     : Ramon Paris
--%>

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
            ArrayList<DetalleCompra> lista = (ArrayList) request.getAttribute("lista");
            int subtotal = 0;           
            

        %>
        <div class="" style="width:800px;margin: 100px auto;">
        <h1 class="text-center">Recibo de Compra </h1>
        
        <h3 class="">Nro de Factura: <b><%=f.getFolio() %></b></h3>
        Fecha de compra: <b><%=f.getFechaCompra() %></b>
        <table class="table table-striped">
           <thead>
                <th>Cantidad</th>    
                <th>Titulo</th>
                <th>Idioma</th>
                <th>Precio</th>                
            </thead>
            <% 
                for (DetalleCompra dc : lista){
            %>
            <tbody>
                <td><%=dc.getCantidad() %></td>
                <td><%=dc.getTitulo() %></td>
                <td><%=dc.getIdioma() %></td>
                <td><%=dc.getPrecio() %></td>
            </tbody>
            <% }%>
        </table>
        <% 
            for (DetalleCompra d : lista){
                subtotal = subtotal + (d.getPrecio()*d.getCantidad());
            }
        %>
        <div class="text-right" style="font-size: 18px;">
            
        Subtotal: <b>$<%=subtotal %> </b><br>
        Iva: <b>$<%=subtotal * 0.19 %> </b><br>
        Total: <b>$<%=subtotal*1.19 %> </b>
        </div>
        </div>
    </body>
</html>
