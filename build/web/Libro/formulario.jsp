<%-- 
    Document   : formulario
    Created on : 09-06-2017, 2:52:05
    Author     : Ramon Paris
--%>

<%@page import="model.dto.Titulo"%>
<%@page import="model.dao.TituloDAO"%>
<%@page import="model.dto.Auxiliar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.AuxiliarDAO"%>
<%@page import="model.dao.LibroDAO"%>
<%@page import="model.dto.Libro"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% String accion="";
   if (request.getParameter("accion")!=null)
        accion = request.getParameter("accion"); %>
<html>
    <head>                
        <title><%=accion%> autor</title>
        <jsp:include page="../template.jsp" />
    </head>
    
    <body>
        <% 
            Libro l = new Libro();
            if (request.getParameter("id")!=null){
                LibroDAO dao = new LibroDAO();
                l = dao.buscar(request.getParameter("id"));
            }
        %>
        
        <h1 class="text-center"><%=accion %> Autor</h1>
        <div class="container">
            <form method="post" action="<%=request.getContextPath()%>/Libro/<%=accion %>">
                <div class="form-group"> 
                    <label>Titulo</label> 
                    <select name="isbn" class="form-control">    
                        <option></option>
                        <%  
                            TituloDAO titulo = new TituloDAO();
                            ArrayList<Titulo> listaTitulo = titulo.listar();
                            for(Titulo t : listaTitulo){ 
                        %>
                        <option value="<%=t.getIsbn() %>" name="isbn" required><%=t.getNombre() %></option>
                        <% } %>
                    </select>
                </div>  
                <div class="form-group"> 
                    <label>Estado</label> 
                    <select name="idEstado" class="form-control">    
                        <option></option>
                        <%  
                            AuxiliarDAO estado = new AuxiliarDAO();
                            ArrayList<Auxiliar> listaEstado = estado.listar("Estado");
                            for(Auxiliar a : listaEstado){ 
                        %>
                        <option value="<%=a.getId() %>" name="idEstado" required><%=a.getDetalle() %></option>
                        <% } %>
                    </select>
                </div>         
                <input type="text" name="nro" value="<%=l.getNroSerie() %>" hidden="true">
                <input type="submit" class="form-control btn btn-success">

            </form>
        </div>
    </body>
</html>
