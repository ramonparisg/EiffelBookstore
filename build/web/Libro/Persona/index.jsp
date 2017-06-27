<%-- 
    Document   : index
    Created on : 26-06-2017, 16:37:13
    Author     : Ramon Paris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <% 
        String tabla = "";
        if (request.getParameter("tabla")!=null){
            tabla = request.getParameter("tabla");
        }
    %>
    <body>
        <h1><%=tabla %></h1>
        <ul>
            <li><a href="<%=request.getContextPath() %>/Persona/listar?tabla=<%=tabla%>">Lista</a></li>
            <li><a href="<%=request.getContextPath() %>/Telefono/listar?tabla=<%=tabla%>">Telefono</a></li>
            <li><a href="<%=request.getContextPath() %>/Direccion/listar?tabla=<%=tabla%>">Direccion</a></li>
            <li><a href="<%=request.getContextPath() %>/Email/listar?tabla=<%=tabla%>">Email</a></li>
        </ul>
    </body>
</html>
