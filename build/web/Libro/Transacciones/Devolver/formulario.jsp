<%-- 
    Document   : formulario
    Created on : 30-06-2017, 4:03:08
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
        <jsp:include page="../../../template.jsp" />
        <title>JSP Page</title>
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
        <h1 class="text-center">Devolución </h1>
        
        <h3 class="">Nro de Arriendo <b><%=a.getId() %></b></h3>
        Fecha de Arriendo <b><%=lista.get(0).getfArriendo() %></b>
        
        <p>Trabajador: <b> <%=a.getRutTrabajador() %></b> <br>
            Cliente: <b> <%=a.getRutCliente() %></b></p>
        
            <% 
                int i =0;
                for (DetalleArriendo d : lista){
                    subtotal= subtotal + d.getSubtotal();
                    multa = multa + d.getMulta();
                    
            
            
                 if (d.getfDevReal().equals("0001-01-01")){ %>
                 <div style="border:1px solid black;margin:10px;padding:10px;" >
                    Nro de serie: <%=d.getNroSerie() %> <br>
                    Titulo: <%=titulos.get(i).getTitulo() %> <br>
                    Idioma: <%=titulos.get(i).getIdioma()%> <br>
                    Fecha devolución estimada: <%=d.getfDevEsti() %> <br>
                    Fecha devolución real:
                    <div  id="success<%=i%>" >                        
                        <input type="date" name="fecha" required class="form-control fech" id="fecha<%=i%>">
                        <input type="number" name="nro" value="<%=d.getNroSerie() %>" id="nroSerie<%=i%>" hidden>
                        <input type="number" name="id" value="<%=d.getIdArriendo() %>" id="idArriendo<%=i%>" hidden>
                        <button class="btn btn-success" onclick="devolver(<%=i%>)">Enviar</button>
                    </div>
                </div>
                <% }%>                
                
                
                
            <% i++; }%>               
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous">  </script>       
        <script>
            var fecha;
            var nroSerie;
            var idArriendo;
            function devolver(i){
                fecha = $('#fecha'+i).val();
                nroSerie = $('#nroSerie'+i).val();
                idArriendo = $('#idArriendo'+i).val();
                $.post("<%=request.getContextPath() %>/Devolver/insertar",                    
                    {index : i,
                     fecha: fecha,
                     nro:nroSerie,
                     id:idArriendo
                    },function(data,status){
                        $('#success'+i).html(data);

                    }                    
                );
            }
            
            $(document).ready(function (){
                
            });
        </script>
    </body>
</html>
