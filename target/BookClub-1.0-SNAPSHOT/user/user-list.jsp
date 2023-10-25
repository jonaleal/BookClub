<%-- 
    Document   : list_users
    Created on : 17/09/2023, 4:31:55 p. m.
    Author     : Jon Leal
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista Usuarios</title>
    </head>
    <body>
        <h1>Users</h1>
        <table border="1" cellpadding="1">
            <thead>
                <tr>
                    <th>UserName</th>
                    <th>FirstName</th>
                    <th>LastName</th>
                    <th>Email</th>
                </tr>
            </thead>
            <tbody>
                <c:if test="${empty users}">
                    <c:redirect url="test.jsp" />
                </c:if>

                <c:forEach var="user" items="${users}">
                    <tr>
                        <td>${user.userName}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.email}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>

