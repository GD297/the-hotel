<%-- 
    Document   : resetPassword
    Created on : Nov 2, 2020, 8:56:49 PM
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
        <h1>Reset Password</h1>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <form action="repassword" method="POST">
            New Password<input type="password" name="txtPassword" value="" /><br/>
            <c:if test="${not empty error.passErrorLength}">
                <font color="red">
                    ${error.passErrorLength}
                </font><br/>
            </c:if>
            Confirm New Password<input type="password" name="txtComfirmPassword" value="" /><br/>
             <c:if test="${not empty error.cPassError}">
                <font color="red">
                    ${error.cPassError}
                </font><br/>
            </c:if>
            <input type="hidden" name="txtEmail" value="${requestScope.USERNAME}" />
            <br/><input type="submit" value="Submit" name="btAction" />
        </form>
    </body>
</html>
