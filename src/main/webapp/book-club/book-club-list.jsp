<%-- 
    Document   : book-club-list
    Created on : 15/10/2023, 5:39:13 p. m.
    Author     : Jon Leal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de Clubes</title>
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="/BookClub/css/book-club.css"/>
    </head>
    <body>

        <!-- imagen superior  -->
        <jsp:include page="../WEB-INF/jspf/header.jspf"/>

        <!-- Barra de navegacion  -->
        <jsp:include page="../WEB-INF/jspf/nav-bar.jspf"/>

        <div class="container">
            <h1 class="text-center">Lista de Clubes</h1>
            <div class="row">
                <div class="col-md-4 mb-3">
                    <div class="card">
                        <img src="https://udeaeduco-my.sharepoint.com/:i:/r/personal/jonatan_leal_udea_edu_co/Documents/BookClubApp/photo-1637681068516-2b22116e68cf.jfif?csf=1&web=1&e=tGKCie" class="card-img-top" alt="Imagen crear club">
                        <div class="card-body">
                            <h5 class="card-title">Crea un Club</h5>
                            <p class="card-text">Aquí puedes crear un club de lectura para discutir sobre diversos temas.</p>
                            <a href="/BookClub/book-club/create-form" class="btn btn-primary">Crear Club</a>
                        </div>
                    </div>
                </div>
                <c:forEach var="bookClub" items="${bookClubs}">
                    <div class="col-md-4 mb-3">
                        <div class="card">
                            <img src="https://source.unsplash.com/random/300x200?sig=${Math.random()}" class="card-img-top" alt="Imagen 1">
                            <div class="card-body">
                                <h5 class="card-title">${bookClub.name}</h5>
                                <p class="card-text">${bookClub.description}</p>
                                <a href="/BookClub/book-club/show?clubId=${bookClub.clubId}" class="btn btn-primary">Ver detalles</a>
                            </div>
                        </div>
                    </div>  
                </c:forEach>
            </div>
        </div>

        <!-- Footer -->
        <jsp:include page="../WEB-INF/jspf/footer.jspf"/>

    </body>
</html>

