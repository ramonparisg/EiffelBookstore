<%-- 
    Document   : index
    Created on : 09-06-2017, 16:16:08
    Author     : Ramon Paris
--%>

<%@page import="model.dto.compra.Distribuidor"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tabla Distribuidor</title>
        <jsp:include page="../../../../template.jsp" />
    </head>
    <body>
        <% 
            ArrayList<Distribuidor> lista = (ArrayList) request.getAttribute("lista");
            
        %>
        
        <h1 class="text-center">Distribuidor </h1>
        <div class="container">
            <p><a href="<%=request.getContextPath() %>/Libro/Transacciones/Comprar/Distribuidor/formulario.jsp?accion=insertar" class="glyphicon glyphicon-plus btn btn-success" ></a></p>
            <table class="table">
                <thead>
                    <th>Rut</th>    
                    <th>Nombre</th>
                    <th>Número de Teléfono</th>
                    <th>Dirección</th>
                    <th>Año</th>                   
                    <th>Acciones</th>
                </thead>
                
                    <% for (Distribuidor d : lista){%>
                    <tbody>
                        <td><%=d.getRut() %></td>
                        <td><%=d.getNombre() %></td>
                        <td><%=d.getTelefono() %></td>
                        <td><%=d.getDireccion() %></td>
                        <td><%=d.getAnyo() %></td>
                        
                        
                        <td>
                        <a href="<%=request.getContextPath() %>/Libro/Transacciones/Comprar/Distribuidor/formulario.jsp?accion=modificar&id=<%=d.getRut() %>" class="btn btn-warning"><spam class="glyphicon glyphicon-pencil"></spam></a>
                        <a href="<%=request.getContextPath()%>/Distribuidor/eliminar?id=<%=d.getRut() %>" class="btn btn-danger"><spam class="glyphicon glyphicon-remove"></spam></a> 
                        </td> 
                    </tbody>
                    <% }%>
                
            </table>
        </div>
    </body>
</html>
