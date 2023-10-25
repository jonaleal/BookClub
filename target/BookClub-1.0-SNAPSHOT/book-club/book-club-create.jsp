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
        <header class="container-fluid mt-3 mb-3">
            <div style="text-align: center">
                <img src="/BookClub/Assets/Logosímbolo+-Universidad-de-Antioquia-horizontal.png" class="img-fluid" alt="Logo">
            </div>
        </header>

        <!-- Barra de navegacion  -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <a class="navbar-brand" href="#">Club De Lectura</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/BookClub/book-club/list">Clubes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/BookClub/user/sign-out">Cerrar Sesión</a>
                    </li>
                </ul>
            </div>
        </nav>

        <div class="container my-container mb-3">
            <h1 class="text-center">Registro de Club</h1>
            <form action="/BookClub/book-club/create" method="post">
                <div class="form-group">
                    <label for="name">Nombre:</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>

                <div class="form-group">
                    <label for="descripcion">Descripción:</label>
                    <input type="text" class="form-control" id="descripcion" name="descripcion" required>
                </div>

                <div class="form-group">
                    <label for="tags">Tags:</label>
                    <input type="text" class="form-control" id="tags" name="tags" required>
                </div>

                <div class="form-group">
                    <label for="meetLink">Enlace de Meet:</label>
                    <input type="url" class="form-control" id="meetLink" name="meetLink" required>
                </div>

                <input type="text" id="username" name="username" value="${sessionScope.username}" hidden>

                <button type="submit" class="btn btn-primary btn-block">Registrar</button>
            </form>
        </div>

        <footer>
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <img src="img/icon.png" alt="">
                    </div>
                    <div class="col-md-6" id="informacionF">
                        <ul id="informacionContacto">
                            <li><h3>Información de Contacto</h3></li>
                            <li><h4>Casa matriz:</h4></li>
                            <li><p>Av Libertador Bernardo Ohiggins 949, <br> Local 154, subterráneo. Santiago</p></li>
                            <li><h4>Atención al cliente:</h4></li>
                            <li><p>Av Libertador Bernardo Ohiggins 949, <br> Oficina 704, piso 7. Santiago <br> Whatsapp +56 9 4186 9896</p></li>
                        </ul>
                    </div>
                    <div class="col-md-3" id="newsletter">
                        <h3>NEWSLETTER</h3>
                        <p>Suscríbete a nuestro Newsletter</p>
                        <form action="">
                            <input type="email" id="email" placeholder="Ej: contacto@mail.com">
                            <input type="submit" class="bt" id="suscribirse" value="Suscribirse">
                        </form>
                        <div id="redesF">
                            <a href="#" target="_blank"><i class="fab fa-facebook-square fa-2x"></i></a>
                            <a href="#" target="_blank"><i class="fab fa-twitter-square fa-2x"></i></a>
                            <a href="#" target="_blank"><i class="fab fa-youtube-square fa-2x"></i></a>
                            <a href="#"><i class="fas fa-envelope-square fa-2x"></i></a>
                            <a href="#" target="_blank"><i class="fab fa-instagram-square fa-2x"></i></a>
                        </div>
                    </div>
                </div>
            </div>
        </footer>

    </body>
</html>

