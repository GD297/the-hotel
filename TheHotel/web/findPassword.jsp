<%-- 
    Document   : findPassword
    Created on : Nov 2, 2020, 8:54:06 PM
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
        <h1>Find Password</h1>
        <c:set var="error" value="${requestScope.ERROR}"/>
        <form action="forget" method="POST">
            Enter your Email<input type="text" name="txtEmail" value="" /></br
            <c:if test="${not empty error.userErrorLength}">
                <font color="red">
                    ${error.userErrorLength}
                </font><br/>
            </c:if>
            <input type="submit" value="Submit" name="btAcion" />
        </form>
    </body>
</html>
