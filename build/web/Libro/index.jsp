<%-- 
    Document   : index
    Created on : 09-06-2017, 2:28:53
    Author     : Ramon Paris
--%>

<%@page import="model.dto.Libro"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tabla Libro</title>
        <jsp:include page="../template.jsp" />
    </head>
    <body>
        <% 
            ArrayList<Libro> lista = (ArrayList) request.getAttribute("lista");
            
        %>
        
        <h1 class="text-center">Libros</h1>
        <div class="container">
            <p><a href="<%=request.getContextPath() %>/Libro/formulario.jsp?accion=insertar" class="glyphicon glyphicon-plus btn btn-success" ></a></p>
            <table class="table">
                <thead>
                    <th>Número de Serie</th>
                    <th>ISBN</th>                    
                    <th>ID Estado</th>                    
                    <th>Acciones</th>
                </thead>
                
                    <% for (Libro l : lista){%>
                    <tbody>
                        <td><%=l.getNroSerie() %></td>
                        <td><%=l.getIsbn() %></td>                       
                        <td><%=l.getIdEstado() %></td>
                        <td>
                        <a href="<%=request.getContextPath() %>/Libro/formulario.jsp?accion=modificar&id=<%=l.getNroSerie() %>" class="btn btn-warning"><spam class="glyphicon glyphicon-pencil"></spam></a>
                        <a href="<%=request.getContextPath()%>/Libro/eliminar?id=<%=l.getNroSerie() %>" class="btn btn-danger"><spam class="glyphicon glyphicon-remove"></spam></a> 
                        </td> 
                    </tbody>
                    <% }%>
                
            </table>
        </div>
    </body>
</html>
