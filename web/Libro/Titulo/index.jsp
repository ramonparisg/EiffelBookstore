<%-- 
    Document   : index
    Created on : 07-06-2017, 12:10:41
    Author     : Ramon Paris
--%>

<%@page import="model.dto.Titulo"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tabla autor</title>
        <jsp:include page="../../template.jsp" />
    </head>
    <body>
        <% 
            ArrayList<Titulo> lista = (ArrayList) request.getAttribute("lista");
            
        %>
        
        <h1 class="text-center">Titulo </h1>
        <div class="container">
            <p><a href="<%=request.getContextPath() %>/Libro/Titulo/formulario.jsp?accion=insertar" class="glyphicon glyphicon-plus btn btn-success" ></a></p>
            <table class="table">
                <thead>
                    <th>ISBN</th>                    
                    <th>Año de publicación</th>
                    <th>Precio referencial</th>
                    <th>Número de Páginas</th>
                    <th>ID Editorial</th>
                    <th>ID Publicación</th>
                    <th>Acciones</th>
                </thead>
                
                    <% for (Titulo t : lista){%>
                    <tbody>
                        <td><%=t.getIsbn() %></td>
                        <td><%=t.getAnyoPublicacion() %></td>
                        <td><%=t.getPrecioReferencia() %></td>
                        <td><%=t.getNroPaginas() %></td>
                        <td><%=t.getIdEditorial() %></td>      
                        <td><%=t.getIdPublicacion() %></td>      
                        
                        <td>
                        <a href="<%=request.getContextPath() %>/Libro/Autor/formulario.jsp?accion=modificar&id=<%=t.getIsbn() %>" class="btn btn-warning"><spam class="glyphicon glyphicon-pencil"></spam></a>
                        <a href="<%=request.getContextPath()%>/Autor/eliminar?id=<%=t.getIsbn() %>" class="btn btn-danger"><spam class="glyphicon glyphicon-remove"></spam></a> 
                        </td> 
                    </tbody>
                    <% }%>
                
            </table>
        </div>
    </body>
</html>
