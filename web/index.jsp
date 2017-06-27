<%-- 
    Document   : index
    Created on : 05-06-2017, 11:38:41
    Author     : Ramon Paris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Mantenedores</h1>
        
        <ul>
            <h4>Titulo</h4>
            <li><a href="Titulo/listar">Titulo</a></li>
            <ul> 
                <li><a href="Autor/listar">Autor</a></li>
                <li><a href="Categoria/listar">Categoria</a></li>
                <li><a href="Idioma/listar">Idioma</a></li>
                <li><a href="Editorial/listar">Editorial</a></li>
                <li><a href="Publicacion/listar">Publicación</a></li>
            </ul>    
            
            
            
            <li><a href="Libro/listar">Libro</a></li>
            <ul>
                <li><a href="Estado/listar">Estado</a></li>
            </ul>
        </ul>
        <h3>Transacciones</h3>
        <h4>Comprar</h4>
        <ul>
            <li><a href="Libro/Transacciones/Comprar/index.jsp">Comprar</a></li>
            <ul>
                <li><a href="MetodoPago/listar">Método Pago</a></li>
                <li><a href="Distribuidor/listar">Distribuidor</a></li>
                <li><a href="Factura/listar">Factura</a></li>
            </ul>
        </ul>
        ´<h4>Vender</h4>
        <ul>
            <li><a href="Libro/Transacciones/Vender/index.jsp">Vender</a></li>
            <ul>                
                <li><a href="Libro/Persona/index.jsp?tabla=Cliente">Cliente</a></li>
                <li><a href="Libro/Persona/index.jsp?tabla=Trabajador">Trabajador</a></li>                
            </ul>
        </ul>
        
        
    </body>
</html>
