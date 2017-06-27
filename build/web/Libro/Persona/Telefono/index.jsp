<%-- 
    Document   : index
    Created on : 26-06-2017, 19:15:29
    Author     : Ramon Paris
--%>

<%@page import="model.dto.Telefono"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../../../template.jsp" />
        <title>JSP Page</title>
    </head>
     <body>
        <% 
            ArrayList<Telefono> lista = (ArrayList) request.getAttribute("lista");
            String tabla ="";
            if (request.getParameter("tabla")!=null){
                tabla = request.getParameter("tabla");
            }
            
        %>
        
        <h1 class="text-center"><%=tabla%> </h1>
        <div class="container">
            <p><a href="<%=request.getContextPath() %>/Libro/Persona/Telefono/formulario.jsp?accion=insertar&tabla=<%=tabla%>" class="glyphicon glyphicon-plus btn btn-success" ></a></p>
            <table class="table">
                <thead>
                    <th>Rut</th>    
                    <th>Número de Teléfono</th>                   
                    <th>Acciones</th>
                </thead>
                
                    <% for (Telefono t : lista){%>
                    <tbody>
                        <td><%=t.getRut() %></td>
                        <td><%=t.getNro() %></td>                        
                        <td>
                        <a href="<%=request.getContextPath() %>/Libro/Persona/Telefono/formulario.jsp?accion=modificar&id=<%=t.getRut() %>&tabla=<%=tabla%>" class="btn btn-warning"><spam class="glyphicon glyphicon-pencil"></spam></a>
                        <a href="<%=request.getContextPath()%>/Telefono/eliminar?id=<%=t.getRut() %>&tabla=<%=tabla%>" class="btn btn-danger"><spam class="glyphicon glyphicon-remove"></spam></a> 
                        </td> 
                    </tbody>
                    <% }%>
                
            </table>
        </div>
    </body>
</html>
