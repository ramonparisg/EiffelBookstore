<%-- 
    Document   : formulario
    Created on : 05-06-2017, 11:53:13
    Author     : Ramon Paris
--%>

<%@page import="model.dto.Autor"%>
<%@page import="model.dao.AutorDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String accion="";
   if (request.getParameter("accion")!=null)
        accion = request.getParameter("accion"); %>
<html>
    <head>                
        <title><%=accion%> autor</title>
        <jsp:include page="../../../template.jsp" />
    </head>
    
    <body>
        <% 
            Autor a = new Autor();
            if (request.getParameter("id")!=null){
                AutorDAO dao = new AutorDAO();
                a = dao.buscar(Integer.parseInt(request.getParameter("id")));
            }
        %>
        
        <h1 class="text-center"><%=accion %> Autor</h1>
        <div class="container">
            <form method="post" action="<%=request.getContextPath()%>/Autor/<%=accion %>">
                <div class="form-group">
                    <label>Nombre</label> 
                    <input class="form-control" type="text" name="nombre" value="<%=a.getNombre() %>" required>
                </div>
                <div class="form-group">
                    <label>Apellido Paterno</label> 
                    <input class="form-control" type="text" name="apePat" value="<%=a.getApePat() %>" required>
                </div>
                <div class="form-group">
                    <label>Apellido Materno</label> 
                    <input class="form-control" type="text" name="apeMat" value="<%=a.getApeMat() %>">
                </div>           
                <input type="text" name="id" value="<%=a.getIdAutor()%>" hidden="true">
                <input type="submit" class="form-control btn btn-success">

            </form>
        </div>
    </body>
</html>
