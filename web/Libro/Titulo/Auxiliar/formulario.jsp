<%-- 
    Document   : formulario
    Created on : 06-06-2017, 12:16:33
    Author     : Ramon Paris
--%>

<%@page import="model.dao.AuxiliarDAO"%>
<%@page import="model.dto.Auxiliar"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    String accion = request.getParameter("accion");
    String tabla = request.getParameter("tabla");
%>
<!DOCTYPE html>

<html>
    <head>                
        <title><%=accion%> <%=tabla%></title>
        <jsp:include page="../../../template.jsp" />
    </head>
    
    <body>
        <% 
            Auxiliar a = new Auxiliar();
            AuxiliarDAO dao = new AuxiliarDAO();            
            if (request.getParameter("id")!=null){                
                a = dao.buscar(Integer.parseInt(request.getParameter("id")),tabla);
            }
        %>
        
        <h1 class="text-center"><%=accion %> <%=tabla%></h1>
        <div class="container">
            <form method="post" action="<%=request.getContextPath()%>/<%=tabla%>/<%=accion %>">
                <div class="form-group">
                    <label>Detalle</label> 
                    <input class="form-control" type="text" name="detalle" value="<%=a.getDetalle()%>" required>
                </div>                
                <input type="text" name="id" value="<%=a.getId()%>" hidden="true">
                <input type="submit" class="form-control btn btn-success">

            </form>
        </div>
    </body>
</html>
