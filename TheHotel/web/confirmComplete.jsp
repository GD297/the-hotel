<%-- 
    Document   : confirmComplete
    Created on : Nov 6, 2020, 9:40:25 PM
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
            <a href="logout">Logout</a>
        </c:if>
        <a href="load">Click here back to home page</a>
        <c:if test="${ empty sessionScope.FULLNAME}">
            <c:redirect url="loginPage1.html"/>
        </c:if>

        <h1>Confirm order complete!</h1>
        <p>You have successfully booked. Please arrive on time to check in the room</p>
    </body>
</html>
