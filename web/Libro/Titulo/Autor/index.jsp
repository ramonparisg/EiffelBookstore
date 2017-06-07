<%-- 
    Document   : index
    Created on : 05-06-2017, 11:51:53
    Author     : Ramon Paris
--%>

<%@page import="model.dto.Autor"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tabla autor</title>
        <jsp:include page="../../../template.jsp" />
    </head>
    <body>
        <% 
            ArrayList<Autor> lista = (ArrayList) request.getAttribute("lista");
            
        %>
        
        <h1 class="text-center">Autores </h1>
        <div class="container">
            <p><a href="<%=request.getContextPath() %>/Libro/Titulo/Autor/formulario.jsp?accion=insertar" class="glyphicon glyphicon-plus btn btn-success" ></a></p>
            <table class="table">
                <thead>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Apellido Paterno</th>
                    <th>Apellido Materno</th>
                    <th>Acciones</th>
                </thead>
                
                    <% for (Autor a : lista){%>
                    <tbody>
                        <td><%=a.getIdAutor() %></td>
                        <td><%=a.getNombre()%></td>
                        <td><%=a.getApePat() %></td>
                        <td><%=a.getApeMat() %></td>      
                        <td>
                        <a href="<%=request.getContextPath() %>/Libro/Autor/formulario.jsp?accion=modificar&id=<%=a.getIdAutor() %>" class="btn btn-warning"><spam class="glyphicon glyphicon-pencil"></spam></a>
                        <a href="<%=request.getContextPath()%>/Autor/eliminar?id=<%=a.getIdAutor() %>" class="btn btn-danger"><spam class="glyphicon glyphicon-remove"></spam></a> 
                        </td> 
                    </tbody>
                    <% }%>
                
            </table>
        </div>
    </body>
</html>
