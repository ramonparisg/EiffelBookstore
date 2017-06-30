<%-- 
    Document   : vender
    Created on : 26-06-2017, 21:14:01
    Author     : Ramon Paris
--%>

<%@page import="controller.ArrendarServlet"%>
<%@page import="model.dao.LibroDAO"%>
<%@page import="model.dto.Libro"%>
<%@page import="model.dto.DetalleTransaccion"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <jsp:include page="../../../template.jsp" />
        <style>
            /* The Modal (background) */
            .modal {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 1; /* Sit on top */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow: auto; /* Enable scroll if needed */
                background-color: rgb(0,0,0); /* Fallback color */
                background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
            }

            /* Modal Content/Box */
            .modal-content {
                background-color: #fefefe;
                margin: 15% auto; /* 15% from the top and centered */
                padding: 20px;
                border: 1px solid #888;
                width: 80%; /* Could be more or less, depending on screen size */
            }

            /* The Close Button */
            .close {
                color: #aaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: black;
                text-decoration: none;
                cursor: pointer;
            }
            /* Modal Content */
.modal-content {
    position: relative;
    background-color: #fefefe;
    margin: auto;
    padding: 0;
    border: 1px solid #888;
    width: 80%;
    box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2),0 6px 20px 0 rgba(0,0,0,0.19);
    -webkit-animation-name: animatetop;
    -webkit-animation-duration: 0.4s;
    animation-name: animatetop;
    animation-duration: 0.4s
}

/* Add Animation */
@-webkit-keyframes animatetop {
    from {top: -300px; opacity: 0} 
    to {top: 0; opacity: 1}
}

@keyframes animatetop {
    from {top: -300px; opacity: 0}
    to {top: 0; opacity: 1}
}
        </style>
    </head>
    <body class="container">
        <% 
            ArrendarServlet.Shopping.clear();            
            ArrayList<DetalleTransaccion> lista = (ArrayList) request.getAttribute("lista");
            int k=0;
        %>
        

        <!-- The Modal -->
        <div id="myModal" class="modal">
          
          <!-- Modal content -->
            <div class="modal-content">
              <div class="modal-header">
                
                <h2 class="text-center">Carrito de compras</h2>
              </div>
              <div class="modal-body">
                  Nada aún :(
                
              </div>
              <div class="modal-footer">
                  <a href="../Libro/Transacciones/Arrendar/concretar.jsp" class="btn btn-success">Continuar</a>
                  <span class="btn btn-danger" id="cerrar">Cerrar</span>
              </div>
            </div>

        </div>
        
        <h1 class="text-center">Libros disponibles</h1> 
        <br>
        <span class="pull-right btn btn-info" style="width: 100px;" id="carrito"><i class="fa fa-shopping-cart" aria-hidden="true">  </i>        <span id="shopping"></span> </span>
        <form>
            
            <input class="" style="margin-top:20px;"> 
            <button class="btn btn-success" style=""><i class="fa fa-search" aria-hidden="true"></i></button>
            
        </form>
        <% for (DetalleTransaccion d : lista){%>
        <div class="row" style="margin:30px auto;border:3px solid black;">
            <div class="col-md-6 text-center" style="font-size: 200px;border-right: 1px solid black;color:#<%=Integer.toHexString((int) (Math.random() * 16)+1) + Integer.toHexString((int) (Math.random() * 16) +1) + Integer.toHexString((int) (Math.random() * 16)+1) %>;">
                <i class="fa fa-book" aria-hidden="true"></i>
            </div>
            <div class="col-md-6" style="padding-top:30px;">
                <b><u><h1 class="text-center bold"><%=d.getTitulo() %></h1></u></b>
                <p>ISBN: <%=d.getIsbn() %></p>
                <p style="font-size: 20px;">Idioma: <span style="color:red;"><%=d.getIdioma() %></span> </p>
                <p>Cantidad: <%=d.getCantidad() %> </p>
                <button class="btn btn-success" style="margin-left:200px;" onclick="agregar(<%=k%>)">Agregar al carrito</button>                                
            </div>
        </div>
            <% k++;%>
        <% } %>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js" integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous">  </script>       
        <script src="https://use.fontawesome.com/70a1abf14a.js"></script>  
        <script>
            var contador=0;
            function agregar(i){
                contador = contador +1;
                $('#shopping').html(contador);
                $.post("<%=request.getContextPath() %>/Arrendar/agregarLibro",                    
                    {index : i
                    },function(data,status){
                        $('.modal-body').html(data);
                        
                    }                    
                );  
            }
            
             function eliminarLibro(i,precio){
                contador = contador -1;
                $('#shopping').html(contador);
                subtotal = $('#subtotal');                
                iva= $('#iva');
                total = $('#total');
                $.post("<%=request.getContextPath() %>/Arrendar/eliminarLibro",
                    {i : i},function(data,status){
                        $('.modal-body').html(data);
                        subtotal = subtotal.val(subtotal.val()-precio);
                        iva.val(subtotal.val()*0.19);
                        total.val(subtotal.val()*1.19);   
                        
                    }
                );
            }

            // Get the modal
            var modal = document.getElementById('myModal');

            // Get the button that opens the modal
            var btn = document.getElementById("carrito");

            // Get the <span> element that closes the modal
            var span = document.getElementById("cerrar");

            // When the user clicks on the button, open the modal 
            btn.onclick = function() {
                modal.style.display = "block";
            }

            // When the user clicks on <span> (x), close the modal
            span.onclick = function() {
                modal.style.display = "none";
            }

            // When the user clicks anywhere outside of the modal, close it
            window.onclick = function(event) {
                if (event.target == modal) {
                    modal.style.display = "none";
                }
            }
            
        </script>
        
    </body>
</html>
