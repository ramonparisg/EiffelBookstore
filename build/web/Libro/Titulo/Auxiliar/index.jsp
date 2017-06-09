<%-- 
    Document   : index
    Created on : 05-06-2017, 18:57:42
    Author     : Ramon Paris
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.dto.Auxiliar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    String tabla = (String)request.getAttribute("tabla");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tabla <%=tabla %></title>
        <jsp:include page="../../../template.jsp" />
    </head>
    <body>
        <% 
            ArrayList<Auxiliar> lista = (ArrayList) request.getAttribute("lista");
            
        %>
        
        <h1 class="text-center"><%=tabla %> </h1>
        <div class="container">
            <p><a href="<%=request.getContextPath() %>/Libro/Titulo/Auxiliar/formulario.jsp?accion=insertar&tabla=<%=tabla %>" class="glyphicon glyphicon-plus btn btn-success" ></a></p>
            <table class="table">
                <thead>
                    <th>ID</th>
                    <th>Detalle</th>                    
                    <th>Acciones</th>
                </thead>
                
                    <% for (Auxiliar a : lista){%>
                    <tbody>
                        <td><%=a.getId() %></td>
                        <td><%=a.getDetalle() %></td>                       
                        <td>
                        <a href="<%=request.getContextPath() %>/Libro/Auxiliar/formulario.jsp?accion=modificar&id=<%=a.getId() %>&tabla=<%=tabla %>" class="btn btn-warning"><spam class="glyphicon glyphicon-pencil"></spam></a>
                        <a href="<%=request.getContextPath()%>/<%=tabla%>/eliminar?id=<%=a.getId() %>" class="btn btn-danger"><spam class="glyphicon glyphicon-remove"></spam></a> 
                        </td> 
                    </tbody>
                    <% }%>
                
            </table>
        </div>
    </body>
</html>
