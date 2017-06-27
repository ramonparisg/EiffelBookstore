<%-- 
    Document   : formulario
    Created on : 26-06-2017, 17:13:45
    Author     : Ramon Paris
--%>

<%@page import="model.dao.CorreoDAO"%>
<%@page import="model.dto.Correo"%>
<%@page import="model.dto.Direccion"%>
<%@page import="model.dao.DireccionDAO"%>
<%@page import="model.dao.TelefonoDAO"%>
<%@page import="model.dto.Telefono"%>
<%@page import="model.dao.PersonaDAO"%>
<%@page import="model.dto.Persona"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../../template.jsp" />
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            Persona p = new Persona();
            Telefono t = new Telefono();
            TelefonoDAO tdao = new TelefonoDAO();
            Direccion d = new Direccion();
            DireccionDAO dDAO = new DireccionDAO();
            Correo c =  new Correo();
            CorreoDAO cDAO = new CorreoDAO();
            PersonaDAO dao = new PersonaDAO();
            String accion = request.getParameter("accion");
            String tabla = request.getParameter("tabla");          
            
        %>
        
        <h1 class="text-center"><%=accion %> <%=tabla%></h1>
        <div class="container">
            <form method="post" action="<%=request.getContextPath()%>/Persona/<%=accion %>?tabla=<%=tabla%>">
                <%                     
                    if (request.getParameter("id")!=null){                
                        p = dao.buscar(Integer.parseInt(request.getParameter("id")),tabla);
                        t = tdao.buscar(Integer.parseInt(request.getParameter("id")), tabla);
                        d = dDAO.buscar(Integer.parseInt(request.getParameter("id")), tabla);
                        c = cDAO.buscar(request.getParameter("id"), tabla);
                    }
                %>
                <div class="form-group">
                    <label>Rut</label> 
                    <input type="text" name="rut" value="<%=p.getRut() %>" class="form-control">
                </div>
                <div class="form-group">
                    <label>Nombre</label> 
                    <input class="form-control" type="text" name="nombre" value="<%=p.getNombre() %>" required>
                </div>                
                <div class="form-group">
                    <label>Apellido Paterno</label> 
                    <input class="form-control" type="text" name="apePat" value="<%=p.getApePat() %>" required>
                </div>
                <div class="form-group">
                    <label>Apellido Materno</label> 
                    <input class="form-control" type="text" name="apeMat" value="<%=p.getApeMat() %>" required>
                </div>
                <div class="form-group">
                    <label>Fecha de nacimiento</label> 
                    <input class="form-control" type="date" name="fnac" value="<%=p.getFechaNac() %>" required>
                </div>
                <div class="form-group">
                    <label>Telefono</label> 
                    <input class="form-control" type="number" name="telefono" value="<%=d.getNro() %>" required>
                </div>
                <div class="form-group">
                    <label>Correo</label> 
                    <input class="form-control" type="email" name="correo" value="<%=c.getCorreo() %>" required>
                </div>
                <div class="form-group">
                    <label>Nombre Calle</label> 
                    <input class="form-control" type="text" name="nombreCalle" value="<%=d.getNombre() %>" required>
                </div>
                <div class="form-group">
                    <label>Numero Calle</label> 
                    <input class="form-control" type="number" name="nroCalle" value="<%=d.getNro() %>" required>
                </div>                                
                <input type="submit" class="form-control btn btn-success">

            </form>
        </div>
    </body>
</html>
