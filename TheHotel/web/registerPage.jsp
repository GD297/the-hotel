<%-- 
    Document   : registerPage
    Created on : Oct 20, 2020, 3:04:04 PM
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
        <h1>Register</h1>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <form action="register" method="POST">
            Username <input type="text" name="txtUsername" value="${param.txtUsername}" /><br/>
            <c:if test="${not empty error.userErrorLength}">
                <font color="red">
                ${error.userErrorLength}
                </font>
            </c:if><br/>
            <c:if test="${not empty error.userDuplicate}">
                <font color="red">
                ${error.userDuplicate}
                </font><br/>
            </c:if>
            Password <input type="password" name="txtPassword" value="" /><br/>
            <c:if test="${not empty error.passErrorLength}">
                <font color="red">
                ${error.passErrorLength}
                </font><br/>
            </c:if>
            Confirm Password <input type="password" name="txtConfirmPassword" value="" /><br/>
            <c:if test="${not empty error.cPassError}">
                <font color="red">
                ${error.cPassError}
                </font><br/>
            </c:if>
            Full Name <input type="text" name="txtFullname" value="${param.txtFullname}" /><br/>
            <c:if test="${not empty error.nameErrorLength}">
                <font color="red">
                ${error.nameErrorLength}
                </font><br/>
            </c:if>
            Phone <input type="text" name="txtPhone" value="${param.txtPhone}" /><br/>
            <c:if test="${not empty error.phoneErrorLength}">
                <font color="red">
                ${error.phoneErrorLength}
                </font><br/>
            </c:if>
            Address <input type="text" name="txtAddress" value="${param.txtAddress}" /> <br/>
            <c:if test="${not empty error.addressErrorLength}">
                <font color="red">
                ${error.addressErrorLength}
                </font><br/>
            </c:if>
            <input type="submit" value="Register" />
        </form>
    </body>
</html>
