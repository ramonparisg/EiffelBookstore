<%-- 
    Document   : formulario
    Created on : 09-06-2017, 17:40:05
    Author     : Ramon Paris
--%>

<%@page import="model.dto.Auxiliar"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.AuxiliarDAO"%>
<%@page import="model.dao.compra.FacturaDAO"%>
<%@page import="model.dto.FacturaBoleta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
    String accion = request.getParameter("accion");
    
%>
<!DOCTYPE html>

<html>
    <head>                
        <title><%=accion%></title>
        <jsp:include page="../../template.jsp" />
    </head>
    
    <body>
        <% 
            FacturaBoleta r = new FacturaBoleta();
            FacturaDAO dao = new FacturaDAO();
            
        %>
        
        <h1 class="text-center"><%=accion %></h1>
        <div class="container">
            <form method="post" action="<%=request.getContextPath()%>/Factura/<%=accion %>">
                <%                     
                    if (request.getParameter("id")!=null){                
                        r = dao.buscar(Integer.parseInt(request.getParameter("id")));
                    }
                %>
                <div class="form-group">
                    <label>Fecha de compra</label> 
                    <input class="form-control" type="date" name="fecha" value="<%=r.getFechaCompra() %>" required>
                </div>                
                <div class="form-group">
                    <label>Hora de compra</label> 
                    <input class="form-control" type="time" name="hora" value="<%=r.getHoraCompra() %>" required>
                </div>
                <div class="form-group">
                    <label>Metodo Pago</label> 
                    
                    <select name="idMetodo" class="form-control">    
                        <option></option>
                        <%  
                            AuxiliarDAO metodo = new AuxiliarDAO();
                            ArrayList<Auxiliar> listaMetodo = metodo.listar("metodo_pago");
                            for(Auxiliar a : listaMetodo){ 
                        %>
                        <option value="<%=a.getId() %>" name="idMetodo" required><%=a.getDetalle() %></option>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label>Precio neto</label> 
                    <input id="neto" class="form-control" type="text" name="neto" value="<%=r.getPrecioNeto() %>" required>         
                </div>
                <div class="form-group">
                    <label>Precio iva</label> 
                    <input id="iva" class="form-control" type="text" name="iva" value="<%=r.getPrecioIva() %>" required disabled>         
                </div>                
                
                <input type="text" name="folio" value="<%=r.getFolio()%>" hidden="true">
                <input type="submit" class="form-control btn btn-success">

            </form>
        </div>
                <script>
                    var neto = document.getElementById('neto');
                    var iva = document.getElementById('iva');
                    neto.onchange = function(){
                        iva.setAttribute("value",Math.round(neto.value * 1.19));
                    }
                    
                </script>
    </body>
</html>
