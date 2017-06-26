<%-- 
    Document   : index
    Created on : 26-06-2017, 16:25:51
    Author     : Ramon Paris
--%>

<%@page import="model.dto.Persona"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
     <body>
        <% 
            ArrayList<Persona> lista = (ArrayList) request.getAttribute("lista");
            
        %>
        
        <h1 class="text-center">Titulo </h1>
        <div class="container">
            <p><a href="<%=request.getContextPath() %>/Libro/Titulo/formulario.jsp?accion=insertar" class="glyphicon glyphicon-plus btn btn-success" ></a></p>
            <table class="table">
                <thead>
                    <th>Rut</th>    
                    <th>Nombre</th>
                    <th>Apellido Paterno</th>
                    <th>Apellido Materno</th>
                    <th>Fecha de nacimiento</th>
                    <th>Acciones</th>
                </thead>
                
                    <% for (Persona p : lista){%>
                    <tbody>
                        <td><%=p.getRut() %></td>
                        <td><%=p.getNombre() %></td>
                        <td><%=p.getApePat() %></td>
                        <td><%=p.getApeMat() %></td>
                        <td><%=p.getFechaNac() %></td>                                                
                        <td>
                        <a href="<%=request.getContextPath() %>/Libro/Titulo/formulario.jsp?accion=modificar&id=<%=p.getRut() %>" class="btn btn-warning"><spam class="glyphicon glyphicon-pencil"></spam></a>
                        <a href="<%=request.getContextPath()%>/Autor/eliminar?id=<%=p.getRut() %>" class="btn btn-danger"><spam class="glyphicon glyphicon-remove"></spam></a> 
                        </td> 
                    </tbody>
                    <% }%>
                
            </table>
        </div>
    </body>
</html>
