<%-- 
    Document   : comprar
    Created on : 17-06-2017, 22:45:28
    Author     : Ramon Paris
--%>

<%@page import="model.dto.Titulo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.dao.TituloDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

        <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../../../template.jsp" />
        <title>JSP Page</title>
    </head>
    <body class="container">
        <h1>Hacer compra <span class="text-right">Fecha</span></h1>
       
       <form method="post" action="<%=request.getContextPath()%>/Compra/insertar">
           
           <div class="form-group">
               <label>Distribuidor</label>
                <select class="form-control">
                         <option></option>
                </select>
            </div>      
        <div style="border: 1px solid black;padding:15px;">
            <label>Titulo</label>
            <select name="isbn" class="form-control">    
                        <option></option>
                        <%  
                            TituloDAO titulo = new TituloDAO();
                            ArrayList<Titulo> listaTitulo = titulo.listar();
                            for(Titulo t : listaTitulo){ 
                        %>
                        <option value="<%=t.getIsbn() %>" name="isbn" required><%=t.getNombre() %></option>
                        Hola
                        <% } %>    
                    </select>
                        
             <div class="form-group">
                    <label>Idioma</label>
                     <select class="form-control">
                              <option></option>
                     </select>
             </div>
            <div class="form-group">
                    <label>Cantidad</label>
                    <input type="number">
             </div>
            <div class="form-group">
                <label>Precio: <span id="precio"></span></label>                
                     
             </div>
            
        </div>
        <button class="btn btn-info pull-right" style="margin:10px;">Agregar Libro</button>
        
       

       <table class="table">
	       	<thead>
	       		<th>Cantidad</th>
	       		<th>Titulo</th>
	       		<th>Idioma</th>
	       		<th>Precio</th>
	       		<th>Acción</th>
	       	</thead>
	       	<tbody>
	       		<td></td>
	       		<td></td>
	       		<td></td>
	       		<td></td>
	       		<td>
                        <a href="<%=request.getContextPath()%>" class="btn btn-danger"><spam class="glyphicon glyphicon-remove"></spam></a> </td>
	       	</tbody>
       </table>
<br>
    <div class="form-group">
       <label>Metodo pago</label>
       <select class="form-control">
       		<option>TDC</option>
       </select>
    </div>
       
       Subtotal: <br>
       IVA: <br>
       Total: <br><br>
       <div class="form-group">
        <input type="submit" class="form-control btn btn-success"/>
       </div>
       </form>
        <script>
            var 
        </script>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous">  </script>
    </body>
</html>
    

