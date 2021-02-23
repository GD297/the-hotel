<%-- 
    Document   : createDiscount
    Created on : Nov 7, 2020, 3:32:21 PM
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
        <c:set var="fullname" value="${sessionScope.FULLNAME}"/>
        <c:set var="role" value="${sessionScope.ROLE_USER}"/>
        <c:set var="Admin" value="1"/>
        <c:if test="${role ne Admin}">
            <c:redirect url="loginPage1.html"/>
        </c:if>
        <c:if test="${not empty fullname}">
            <font color="red">
            Welcome ${sessionScope.FULLNAME}
            </font><br/>
            <a href="logout">Logout</a><br/>
            <a href="loadadmin">Home</a><br/>
        </c:if>
        <c:set var="error" value="${requestScope.ERROR_HAPPEN}"/>
        <form action="creatediscount" method="POST">
            Discount ID <input type="text" name="txtDiscountID" value="" />
            <c:if test="${not empty error.discountID}">
                <font color="red">
                ${error.discountID}
                </font><br/>
            </c:if>
            Name<input type="text" name="txtDiscountName" value="" />
            <c:if test="${not empty error.discountID}">
                <font color="red">
                ${error.name}
                </font><br/>
            </c:if>
            Percent <input type="text" name="txtPercent" value="" />
            <c:if test="${not empty error.discountID}">
                <font color="red">
                ${error.percent}
                </font><br/>
            </c:if>
            <input type="submit" value="Create" name="btAction" />
        </form>
    </body>
</html>
