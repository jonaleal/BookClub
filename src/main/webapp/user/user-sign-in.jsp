<%-- 
    Document   : user_login
    Created on : 24/09/2023, 5:13:54 p. m.
    Author     : Jon Leal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Iniciar Sesión</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="/BookClub/css/style.css">
    </head>
    <body>
        <div class="container my-container">
            <h1 class="text-center">Iniciar Sesión</h1>
            <form action="/BookClub/user/sign-in" method="post">
                <div class="form-group">
                    <label for="username">Usuario</label>
                    <input type="text" class="form-control" id="username" name="username" required>
                </div>
                <div class="form-group">
                    <label for="password">Contraseña</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
                <a href="/BookClub/user/create-form" class="btn btn-secondary">Registrarse</a>
            </form>
        </div>
    </body>
</html>






