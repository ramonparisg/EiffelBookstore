<%-- 
    Document   : Recibo
    Created on : 09-06-2017, 16:22:01
    Author     : Ramon Paris
--%>

<%@page import="model.dto.FacturaBoleta"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    String tabla ="";
    if (request.getParameter("tabla")!=null){
        tabla = request.getParameter("tabla");
    }
    

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tabla Distribuidor</title>
        <jsp:include page="../../template.jsp" />
    </head>
    <body>
        <% 
            ArrayList<FacturaBoleta> lista = (ArrayList) request.getAttribute("lista");
            
        %>
        
        <h1 class="text-center"><%=tabla %> </h1>
        <div class="container">
            <p><a href="<%=request.getContextPath() %>/Libro/Transacciones/formulario.jsp?accion=insertar" class="glyphicon glyphicon-plus btn btn-success" ></a></p>
            <table class="table">
                <thead>
                    <th>Folio</th>    
                    <th>Fecha de emisión</th>                   
                    <th>Precio neto</th>
                    <th>IVA</th>                   
                    <th>Precio con IVA</th>                   
                    <th>ID método pago</th>                   
                    <th>Acciones</th>
                </thead>
                
                    <% for (FacturaBoleta r : lista){%>
                    <tbody>
                        <td><%=r.getFolio() %></td>
                        <td><%=r.getFechaCompra() %></td>                        
                        <td><%=r.getPrecioNeto() %></td>
                        <td><%=r.getIva() %>%</td>
                        <td><%=r.getPrecioIva() %></td>
                        <td><%=r.getMetodoPago() %></td>
                        
                        
                        <td>
                        <a href="<%=request.getContextPath() %>/Libro/Transacciones/formulario.jsp?accion=modificar&id=<%=r.getFolio() %>&tabla=<%=tabla %>" class="btn btn-warning"><spam class="glyphicon glyphicon-pencil"></spam></a>
                        <a href="<%=request.getContextPath()%>/Factura/eliminar?id=<%=r.getFolio() %>" class="btn btn-danger"><spam class="glyphicon glyphicon-remove"></spam></a> 
                        </td> 
                    </tbody>
                    <% }%>
                
            </table>
        </div>
    </body>
</html>