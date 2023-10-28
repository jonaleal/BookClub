<%-- 
    Document   : user-update
    Created on : 2/10/2023, 3:36:09 p. m.
    Author     : Jon Leal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Actualización Usuario</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="/BookClub/css/book-club.css"/>
    </head>
    <body>

        <!-- imagen superior  -->
        <jsp:include page="../WEB-INF/jspf/header.jspf"/>

        <!-- Barra de navegacion  -->
        <jsp:include page="../WEB-INF/jspf/nav-bar.jspf"/>

        <div class="container my-container mb-3">
            <h1 class="text-center">Actualizar Datos</h1>
            <form action="/BookClub/user/update" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" id="username" name="username" value="${user.userName}" required>
                </div>

                <div class="form-group">
                    <label for="pictureUrl">URL de Imagen:</label>
                    <input type="url" class="form-control" id="pictureUrl" name="pictureUrl" value="${user.pictureUrl}">
                </div>

                <div class="form-group">
                    <label for="firstName">Nombre:</label>
                    <input type="text" class="form-control" id="firstName" name="firstName" value="${user.firstName}" required>
                </div>

                <div class="form-group">
                    <label for="lastName">Apellido:</label>
                    <input type="text" class="form-control" id="lastName" name="lastName" value="${user.lastName}" required>
                </div>

                <div class="form-group">
                    <label for="email">Correo Electrónico:</label>
                    <input type="email" class="form-control" id="email" name="email" value="${user.email}" required>
                </div>         

                <button type="submit" class="btn btn-primary btn-block" onclick="return showWarning();">Actualizar</button>
            </form>
        </div>

        <!-- Footer -->
        <jsp:include page="../WEB-INF/jspf/footer.jspf"/>
        <script src="/BookClub/js/funtions.js"></script>
    </body>
</html>
