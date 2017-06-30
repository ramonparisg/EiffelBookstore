<%-- 
    Document   : recibo
    Created on : 18-06-2017, 12:51:54
    Author     : Ramon Paris
--%>


<%@page import="model.dto.DetalleTransaccion"%>
<%@page import="model.dto.arriendo.DetalleArriendo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dto.arriendo.Arriendo"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="../../../template.jsp" />
    </head>
    <body>
        <%            
            Arriendo a = (Arriendo) request.getAttribute("arriendo");
            ArrayList<DetalleArriendo> lista = (ArrayList) request.getAttribute("lista");
            ArrayList<DetalleTransaccion> titulos = (ArrayList) request.getAttribute("titulos");
            
            int subtotal = 0;           
            int multa=0;

        %>
        <div class="container" >
        <h1 class="text-center">Arriendos </h1>
        
        <h3 class="">Nro de Arriendo <b><%=a.getId() %></b></h3>
        Fecha de Arriendo <b><%=lista.get(0).getfArriendo() %></b>
        
        <p>Trabajador: <b> <%=a.getRutTrabajador() %></b> <br>
            Cliente: <b> <%=a.getRutCliente() %></b></p>
        <table class="table table-striped">
           <thead>
               <th>Nro Serie</th> 
               <th>Titulo</th>
                <th>Idioma</th>
                   
                <th>Fecha Estimada de devolución</th>
                <th>Fecha de devolución real</th>
                <th>Subtotal</th>
                <th>Multa</th>
                <th>Total</th>                           
            </thead>
            <% 
                int i =0;
                for (DetalleArriendo d : lista){
                    subtotal= subtotal + d.getSubtotal();
                    multa = multa + d.getMulta();
                    
            %>
            <tbody>
                <td><%=d.getNroSerie() %></td>
                <td><%=titulos.get(i).getTitulo() %></td>
                <td><%=titulos.get(i).getIdioma()%></td>                
                <td><%=d.getfDevEsti() %></td>
                <% if (d.getfDevReal().equals("0001-01-01")){ %>
                <td>No devuelto aún</td>
                <% }else{ %>                
                <td><%=d.getfDevReal() %></td>
                <% } %>
                <td><%=d.getSubtotal() %></td>
                <td><%=d.getMulta() %></td>
                <td><%=d.getTotal() %></td>
            </tbody>
            <% i++; }%>
        </table>        
        <div class="text-right" style="font-size: 18px;">
            
        Subtotal: <b>$<%=subtotal %> </b><br>
        Multa: <b>$<%=multa %> </b><br>
        Total: <b>$<%=subtotal+multa %> </b>
        </div>
        </div>
    </body>
</html>
