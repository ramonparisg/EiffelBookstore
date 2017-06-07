<%-- 
    Document   : formulario
    Created on : 07-06-2017, 14:47:27
    Author     : Ramon Paris
--%>

<%@page import="model.dto.Autor"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.AutorDAO"%>
<%@page import="model.dao.TituloDAO"%>
<%@page import="model.dto.Titulo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String accion="";
   if (request.getParameter("accion")!=null)
        accion = request.getParameter("accion"); %>
<html>
    <head>                
        <title><%=accion%> autor</title>
        <jsp:include page="../../template.jsp" />
    </head>
    
    <body>
        <% 
            Titulo t = new Titulo();
            if (request.getParameter("id")!=null){
                TituloDAO dao = new TituloDAO();
                t = dao.buscar(Integer.parseInt(request.getParameter("id")));
            }
        %>
        
        <h1 class="text-center"><%=accion %> Autor</h1>
        <div class="container">
            <form method="post" action="<%=request.getContextPath()%>/Autor/<%=accion %>">
                <div class="form-group">
                    <label>ISBN</label> 
                    <input class="form-control" type="number" name="isbn" value="<%=t.getIsbn() %>" required>
                </div>
                <div class="form-group">
                    <label>Año de publicación</label> 
                    <input class="form-control" type="date" name="anyo" value="<%=t.getAnyoPublicacion() %>" required>
                </div>
                <div class="form-group">
                    <label>Precio referencial</label> 
                    <input class="form-control" type="number" name="precio" value="<%=t.getPrecioReferencia() %>">
                </div>           
                <div class="form-group">
                    <label>Número de páginas</label> 
                    <input class="form-control" type="number" name="nro" value="<%=t.getPrecioReferencia() %>">
                </div>           
                <div class="form-group"> 
                    <label>Autor</label> 
                    <select name="idUsuario" class="form-control">                        
                        <option value=""> </option>
                        <%  
                            AutorDAO autor = new AutorDAO();
                            ArrayList<Autor> listaAutor = autor.listar();
                            for(Autor a : listaAutor){ 
                        %>
                        <option value="<%=a.getIdAutor() %>" name="idAutor" required><%=a.getNombre() %> <%=a.getApePat() %> <%=a.getApeMat() %></option>
                        <% } %>
                    </select>
                </div>
                <input type="submit" class="form-control btn btn-success">

            </form>
        </div>
    </body>
</html>
