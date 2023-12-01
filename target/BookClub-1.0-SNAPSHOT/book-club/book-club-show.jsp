<%-- 
    Document   : book-club-show
    Created on : 17/10/2023, 10:10:26 a. m.
    Author     : Jon Leal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="isJoined" value="false" />
<c:forEach items="${bookClub.userList}" var="user">
    <c:out value="${user.userName}" />
    <c:if test="${user.userName == sessionScope.username}">
        <c:set var="isJoined" value="true" />
    </c:if>
</c:forEach>
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
            <div class="row ml-1"><p>${discussion.description}</p></div>
            <div class="row ml-0">
                <c:if test="${!isJoined}">
                    <form action="/BookClub/book-club/join" method="post">                       
                        <input type="text" id="clubId" name="clubId" value="${bookClub.clubId}" hidden required>
                        <input type="text" id="name" name="name" value="${bookClub.name}" hidden required>
                        <input type="text" id="descripcion" name="descripcion" value="${bookClub.description}" hidden required>
                        <input type="text" id="tags" name="tags" value="${bookClub.tags}" hidden required>


                        <input type="url"  id="meetLink" name="meetLink" value="${bookClub.meetLink}" hidden required>

                        <button type="submit" class="btn btn-primary btn-block">Unirse al Club</button>
                    </form>  
                </c:if>
            </div>
            <h1>Discusiones</h1>
            <c:if test="${isJoined}">
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
            </c:if>


            <br><br>
            <ul class="list-group">
                <c:forEach var="discussion" items="${bookClub.discussionList}">
                    <li class="list-group-item mb-3">
                        <div class="row ml-1"><a href="#">@${discussion.userName.userName}</a></div>
                        <div class="row ml-1"><h4>${discussion.tittle}</h4></div>
                        <div class="row ml-1"><p>${discussion.description}</p></div>
                                <c:if test="${discussion.userName.userName == sessionScope.username}">
                            <div class="row justify-content-end mr-3"><a href="/BookClub/discussion/delete?discussionId=${discussion.discussionId}&fromClubId=${bookClub.clubId}">Eliminar</a></div>   
                        </c:if>
                    </li>
                </c:forEach>
            </ul>
        </div>
        <!-- Footer -->
        <jsp:include page="../WEB-INF/jspf/footer.jspf"/>

    </body>
</html>
