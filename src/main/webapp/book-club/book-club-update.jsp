<%-- 
    Document   : book-club-create
    Created on : 17/10/2023, 9:28:22 a. m.
    Author     : Jon Leal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Club</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="/BookClub/css/book-club.css">
    </head>
    <body>

        <!-- imagen superior  -->
        <jsp:include page="../WEB-INF/jspf/header.jspf"/>

        <!-- Barra de navegacion  -->
        <jsp:include page="../WEB-INF/jspf/nav-bar.jspf"/>

        <div class="container my-container mb-3">
            <h1 class="text-center">Actualizar Club</h1>
            <form action="/BookClub/book-club/update" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" id="clubId" name="clubId" value="${bookClub.clubId}" hidden required>
                </div>

                <div class="form-group">
                    <label for="name">Nombre:</label>
                    <input type="text" class="form-control" id="name" name="name" value="${bookClub.name}" required>
                </div>

                <div class="form-group">
                    <label for="descripcion">Descripción:</label>
                    <input type="text" class="form-control" id="descripcion" name="descripcion" value="${bookClub.description}" required>
                </div>

                <div class="form-group">
                    <label for="tags">Tags:</label>
                    <input type="text" class="form-control" id="tags" name="tags" value="${bookClub.tags}" required>
                </div>

                <div class="form-group">
                    <label for="pictureUrl">Enlace de imagen:</label>
                    <input type="url" class="form-control" id="pictureUrl" name="pictureUrl" required>
                </div>

                <div class="form-group">
                    <label for="meetLink">Enlace de Meet:</label>
                    <input type="url" class="form-control" id="meetLink" name="meetLink" value="${bookClub.meetLink}" required>
                </div>

                <button type="submit" class="btn btn-primary btn-block">Actulizar</button>
            </form>
        </div>

        <!-- Footer -->
        <jsp:include page="../WEB-INF/jspf/footer.jspf"/>

    </body>
</html>

