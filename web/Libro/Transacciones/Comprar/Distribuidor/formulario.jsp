<%-- 
    Document   : formulario
    Created on : 09-06-2017, 16:16:19
    Author     : Ramon Paris
--%>

<%@page import="model.dto.compra.Distribuidor"%>
<%@page import="model.dao.compra.DistribuidorDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String accion="";
   if (request.getParameter("accion")!=null)
        accion = request.getParameter("accion"); %>
<html>
    <head>                
        <title><%=accion%> autor</title>
        <jsp:include page="../../../../template.jsp" />
    </head>
    
    <body>
        <% 
            Distribuidor d = new Distribuidor();
            if (request.getParameter("id")!=null){
                DistribuidorDAO dao = new DistribuidorDAO();
                d = dao.buscar(Integer.parseInt(request.getParameter("id")));
            }
        %>
        
        <h1 class="text-center"><%=accion %> Distribuidor</h1>
        <div class="container">
            <form method="post" action="<%=request.getContextPath()%>/Distribuidor/<%=accion %>">
                <div class="form-group">
                    <label>Rut</label> 
                    <input class="form-control" type="number" name="rut" value="<%=d.getRut() %>" required>
                </div>
                <div class="form-group">
                    <label>Nombre</label> 
                    <input class="form-control" type="text" name="nombre" value="<%=d.getNombre() %>" required>
                </div>
                <div class="form-group">
                    <label>Telefono</label> 
                    <input class="form-control" type="text" name="tlf" value="<%=d.getTelefono() %>">
                </div>           
                <div class="form-group">
                    <label>Dirección</label> 
                    <input class="form-control" type="text" name="dir" value="<%=d.getDireccion() %>">
                </div>
                <div class="form-group">
                    <label>Año</label> 
                    <input class="form-control" type="number" name="anyo" value="<%=d.getAnyo() %>">
                </div>
                
                <input type="submit" class="form-control btn btn-success">

            </form>
        </div>
    </body>
</html>
