<%-- 
    Document   : comprar
    Created on : 17-06-2017, 22:45:28
    Author     : Ramon Paris
--%>

<%@page import="model.dto.compra.Distribuidor"%>
<%@page import="model.dao.compra.DistribuidorDAO"%>
<%@page import="java.util.Calendar"%>
<%@page import="java.sql.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="model.dto.compra.DetalleCompra"%>
<%@page import="controller.CompraServlet"%>
<%@page import="model.dao.AuxiliarDAO"%>
<%@page import="model.dto.Auxiliar"%>
<%@page import="model.dto.Titulo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.TituloDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% CompraServlet.listaDC.clear(); %>
<!DOCTYPE html>

        <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../../../template.jsp" />
        <title>JSP Page</title>
    </head>
    <body class="container">
        <% DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
            Calendar cal = Calendar.getInstance();
            request.setAttribute("fecha",sdf.format(cal.getTime()));
            
        %>
        <h1>Hacer compra <span class="pull-right"><%=sdf.format(cal.getTime())%></span></h1>
        
           <form method="post" action="<%=request.getContextPath()%>/Compra/insertar">
               <label>Distribuidor</label>
           <select name="distribuidor" class="form-control" id="distribuidor">    
                        <option></option>
                        <%  
                            
                            DistribuidorDAO distribuidor = new DistribuidorDAO();
                            ArrayList<Distribuidor> listaDistribuidor = distribuidor.listar();
                            
                            
                            for(Distribuidor d : listaDistribuidor){ 
                        %>
                        <option value="<%=d.getRut() %>" name="distribuidor" required><%=d.getNombre() %></option>
                        
                        
                        <% } %>    
                    </select>  

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
            <div style="border:1px solid black;padding:15px;">               
            <label>Titulo</label>
            <select name="isbn" class="form-control" onchange="setPrecio()" id="titulo">    
                        <option></option>
                        <%  
                            
                            TituloDAO titulo = new TituloDAO();
                            ArrayList<Titulo> listaTitulo = titulo.listar();
                            ArrayList<Integer> precio = new ArrayList<Integer>();
                            
                            for(Titulo t : listaTitulo){ 
                        %>
                        <option value="<%=t.getIsbn() %>" name="isbn" data-precio="<%=t.getPrecioReferencia()%>" required><%=t.getNombre() %></option>
                        
                        
                        <% } %>    
                    </select>
                        
                        
             <div class="form-group">
                 <label>Idioma</label>
                    <select name="idIdioma" class="form-control" id="idioma">    
                        <option></option>
                        <%               
                            AuxiliarDAO auxiliar = new AuxiliarDAO();
                            ArrayList<Auxiliar> listaIdioma = auxiliar.listar("Idioma");
                            for(Auxiliar a : listaIdioma){ 
                        %>
                        <option value="<%=a.getId() %>" name="idIdioma" required><%=a.getDetalle() %> </option>
                        <% } %>
                    </select>
             </div>
            <div class="form-group">
                    <label>Cantidad</label>
                    <input type="number" id="cantidad" value="1" onchange="setPrecio()">
             </div>
            <div class="form-group">
                <label >Precio: $<span id="precio"></span></label>                
                     
            </div>
                    
            <span class="btn btn-default" id="agregar" onclick="agregarLibro()">Agregar libro</span>
        </div>
         <!-- ----------------------------------------------------------------------------------------------------------- -->
         
                <div id="libros"></div>
        
        <!-- ----------------------------------------------------------------------------------------------------------- -->
                                
       
        Subtotal: <input type="text" disabled="true" value="0" name="subtotal" id="subtotal" onchange="asd()"><br>
        IVA 19%: <input type="text" disabled="true" value="0" name="iva" id="iva"><br>
        Total: <input type="text" disabled="true" value="0" name="total" id="total"><br>
       <div class="form-group">
        <input type="submit" class="form-control btn btn-success"/>
       </div>
       </form>
       <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous">  </script>       
        <script>
            function setPrecio(){
                var subtotal = $('#titulo option:selected').attr('data-precio');
                var cantidad = $('#cantidad').val();
                $('#precio').text(subtotal*cantidad)
            }
            var subtotal=0;
            function agregarLibro(){    
                var titulo = $('#titulo option:selected').text();
                var isbn = $('#titulo option:selected').val();
                var idioma = $('#idioma option:selected').text();
                var idIdioma = $('#idioma option:selected').val();
                var cantidad = $('#cantidad').val();
                var precio = $('#titulo option:selected').attr('data-precio');
                llenarSubtotal(cantidad,precio);                
                $.post("<%=request.getContextPath() %>/Compra/agregarLibro",                    
                    {titulo : titulo,
                    isbn : isbn,
                    idioma : idioma,
                    idIdioma : idIdioma,
                    cantidad : cantidad,
                    precio : precio
                    },function(data,status){
                        $('#libros').html(data)
                    }                    
                );                                
            }            
            
            function eliminarLibro(i,cantidad,precio){
                subtotal = $('#subtotal');
                
                iva= $('#iva');
                total = $('#total');
                $.post("<%=request.getContextPath() %>/Compra/eliminarLibro",
                    {i : i},function(data,status){
                        $('#libros').html(data);
                        subtotal = subtotal.val(subtotal.val()-(cantidad*precio));
                        iva.val(subtotal.val()*0.19);
                        total.val(subtotal.val()*1.19);
                      
                        
                    }
                );
            }
             
           
           function llenarSubtotal(cantidad,precio){
               subtotal = subtotal + cantidad * precio;
               $('#subtotal').val(subtotal);
               $('#iva').val(subtotal*0.19);
               $('#total').val(subtotal*1.19);
           };
            
            
            
           
        </script>
        
    </body>
</html>
    

