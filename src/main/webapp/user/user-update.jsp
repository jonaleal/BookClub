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
    </head>
    <body>
        <h1>Actualización de Usuario</h1>
        <form action="/BookClub/user/update" method="post">
            <label for="username">Nombre de Usuario:</label>
            <input type="text" id="username" name="username" value="${user.userName}" required><br><br>

            <label for="pictureUrl">URL de Imagen:</label>
            <input type="url" id="pictureUrl" name="pictureUrl" value="${user.pictureUrl}"><br><br>

            <label for="firstName">Nombre:</label>
            <input type="text" id="firstName" name="firstName" value="${user.firstName}" required><br><br>

            <label for="lastName">Apellido:</label>
            <input type="text" id="lastName" name="lastName" value="${user.lastName}" required><br><br>

            <label for="email">Correo Electrónico:</label>
            <input type="email" id="email" name="email" value="${user.email}" required><br><br>

            <label for="password">Contraseña:</label>
            <input type="password" id="password" name="password" value="${user.password}" required><br><br>

            <label for="birthdate">Fecha de Nacimiento:</label>
            <input type="date" id="birthdate" name="birthdate" required><br><br>

            <input type="submit" value="Actualizar">
        </form>
    </body>
</html>
