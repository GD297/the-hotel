<%-- 
    Document   : showFeedback
    Created on : Nov 7, 2020, 10:12:24 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>The Hotel</title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.FULLNAME}">
            <font color="red">
            Welcome ${sessionScope.FULLNAME}
            </font>
            <a href="logout">Logout</a><br/>
            <a href="load">Home</a>
        </c:if>
        <c:if test="${ empty sessionScope.FULLNAME}">
            <c:redirect url="loginPage1.html"/>
        </c:if>
        <c:set var="listFB" value="${requestScope.LIST_FEEDBACK}"/> 
        <table border="1">
            <thead>
                <tr>
                    <th>User</th>
                    <th>Content</th>
                    <th>Rating</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="feedback" items="${listFB}">
                    <tr>
                        <td>${feedback.userID}</td>
                        <td>${feedback.content}</td>
                        <td>${feedback.rate}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </body>
</html>
