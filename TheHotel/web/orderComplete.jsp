<%-- 
    Document   : orderComplete
    Created on : Nov 6, 2020, 8:38:57 PM
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

        <h1>Order Complete!</h1>
        <p>Your order has been complete!! Please comfirm order by mail we sent to you, check inbox in your email address</p>
        <p><b>${requestScope.ORDERID_NUMBER}</b> is your order number, please follow your order in history book</p>
    </body>
</html>
