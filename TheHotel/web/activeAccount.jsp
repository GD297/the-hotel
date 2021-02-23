<%-- 
    Document   : activeAccount
    Created on : Nov 2, 2020, 8:59:08 PM
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
        <h2>You Are Register Complete!!</h2>
        <h3>Only one step remain to finish setup.</h3>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <form action="code" method="POST">
            OTP CODE <input type="text" name="txtOTPCode" value="" />
            <c:if test="${error.wrongOTPCode}">
                <font color="red">
                    ${error.wrongOTPCode}
                <font>
            </c:if>
            <input type="hidden" name="txtEmail" value="${param.txtEmail}" />
            <input type="submit" value="Submit" name="btAction" />
        </form>
        <a href="resend?txtEmail=${param.txtEmail}">Re-send OTP</a>
    </body>
</html>
