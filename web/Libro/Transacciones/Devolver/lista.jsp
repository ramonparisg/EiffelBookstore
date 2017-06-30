<%-- 
    Document   : lista
    Created on : 30-06-2017, 2:53:03
    Author     : Ramon Paris
--%>

<%@page import="model.dto.arriendo.Arriendo"%>
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
            ArrayList<Arriendo> lista = (ArrayList) request.getAttribute("lista");
            
        %>
        
        <h1 class="text-center">Arriendo</h1>
        <div class="container">
            <p><a href="<%=request.getContextPath() %>/Arrendar/listarDisponibles" class="glyphicon glyphicon-plus btn btn-success" ></a></p>
            <table class="table">
                <thead>
                    <th>ID de Arriendo</th>                       
                    <th>Rut Cliente</th>                                   
                    <th>Rut Trabajador</th>
                    <th>Acciones</th>
                </thead>
                
                    <% for (Arriendo a : lista){%>
                    <tbody>
                        <td><%=a.getId() %></td>                        
                        <td><%=a.getRutCliente() %></td>
                        <td><%=a.getRutTrabajador() %></td>
                        <td>                        
                        <a href="<%=request.getContextPath()%>/Arrendar/recibo?id=<%=a.getId() %>" class="btn btn-warning"><i class="fa fa-eye" aria-hidden="true"></i></a> 
                        </td> 
                    </tbody>
                    <% }%>
                
            </table>
        </div>
    <script src="https://use.fontawesome.com/70a1abf14a.js"></script>                
    </body>
</html>
