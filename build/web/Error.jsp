<%-- 
    Document   : Error
    Created on : 05-06-2017, 17:11:20
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
        <h1>Error <%=request.getAttribute("error") %></h1>
    </body>
</html>
