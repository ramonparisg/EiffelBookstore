<%-- 
    Document   : exito
    Created on : 25-06-2017, 15:43:06
    Author     : Ramon Paris
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../../../template.jsp" />
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Compra realizada con éxito</h1>
        <a href="<%=request.getContextPath()%>">Inicio</a>
        <a href="<%=request.getContextPath()%>/Compra/listar">Ver compras</a>
    </body>
</html>
