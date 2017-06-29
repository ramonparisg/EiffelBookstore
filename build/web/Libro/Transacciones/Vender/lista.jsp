<%-- 
    Document   : lista
    Created on : 25-06-2017, 21:17:02
    Author     : Ramon Paris
--%>

<%@page import="model.dto.venta.Venta"%>
<%@page import="model.dto.compra.Compra"%>
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
            ArrayList<Venta> lista = (ArrayList) request.getAttribute("lista");
            
        %>
        
        <h1 class="text-center">Venta</h1>
        <div class="container">
            <p><a href="<%=request.getContextPath() %>/Vender/listarDisponibles" class="glyphicon glyphicon-plus btn btn-success" ></a></p>
            <table class="table">
                <thead>
                    <th>ID de Venta</th>    
                    <th>Número de folio</th>
                    <th>Rut Cliente</th>                                   
                    <th>Rut Trabajador</th>
                    <th>Acciones</th>
                </thead>
                
                    <% for (Venta v : lista){%>
                    <tbody>
                        <td><%=v.getId() %></td>
                        <td><%=v.getBoleta() %></td>
                        <td><%=v.getRutCliente() %></td>
                        <td><%=v.getRutTrabajador() %></td>
                        <td>                        
                        <a href="<%=request.getContextPath()%>/Vender/recibo?id=<%=v.getId() %>" class="btn btn-warning"><i class="fa fa-eye" aria-hidden="true"></i></a> 
                        </td> 
                    </tbody>
                    <% }%>
                
            </table>
        </div>
    <script src="https://use.fontawesome.com/70a1abf14a.js"></script>                
    </body>
</html>
