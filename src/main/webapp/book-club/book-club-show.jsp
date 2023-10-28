<%-- 
    Document   : book-club-show
    Created on : 17/10/2023, 10:10:26 a. m.
    Author     : Jon Leal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Clubes de Lectura</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="/BookClub/css/book-club.css">
    </head>
    <body>

        <!-- imagen superior  -->
        <jsp:include page="../WEB-INF/jspf/header.jspf"/>

        <!-- Barra de navegacion  -->
        <jsp:include page="../WEB-INF/jspf/nav-bar.jspf"/>

        <div class="container">

            <h2>Club: ${bookClub.name}</h2>
            <p>${bookClub.description}</p>
            <a href="${bookClub.meetLink}">Enlace de Reunión</a>
            <h1>Discusiones</h1>
            <form action="/BookClub/discussion/create" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" id="tittle" name="tittle" placeholder="Título" required>
                </div>
                <div class="form-group">
                    <textarea class="form-control" id="description" name="description" placeholder="Descripción:" required></textarea>
                </div>
                <input type="text" id="username" name="username" value="${sessionScope.username}" hidden>
                <input type="text" id="clubId" name="clubId" value="${bookClub.clubId}" hidden>
                <button type="submit" class="btn btn-primary">Comentar</button>
            </form>

            <br><br>
            <ul class="list-group">
                <c:forEach var="discussion" items="${bookClub.discussionList}">
                    <li class="list-group-item mb-3">
                        <h4>${discussion.tittle}</h4>
                        <p>${discussion.description}</p>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <!-- Footer -->
        <jsp:include page="../WEB-INF/jspf/footer.jspf"/>

    </body>
</html>
