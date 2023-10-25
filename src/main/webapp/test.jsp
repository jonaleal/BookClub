<%-- 
    Document   : test
    Created on : 17/09/2023, 3:53:18 p. m.
    Author     : Jon Leal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String userName = (String) request.getSession().getAttribute("userName");
            boolean isLogued = (boolean) request.getSession().getAttribute("isLogued");
            if (isLogued) {
        %>
            <p><%= userName%> ha iniciado sesion.</p>
        <%
            } else {
        %>
            <p>No se ha iniciado sesion.</p>
        <%
            }
        %>
    </body>
</html>
