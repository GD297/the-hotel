<%-- 
    Document   : feedBackPage
    Created on : Nov 7, 2020, 9:21:08 PM
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
            <a href="history">Booking history</a>
        </c:if>
        <c:if test="${ empty sessionScope.FULLNAME}">
            <c:redirect url="loginPage1.html"/>
        </c:if>
        <c:set var="error" value="${requestScope.ERROR}"/>
            <form action="feedbackOrder" method="post">
                <textarea name="txtContent"></textarea>
                <select name="txtRate">
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                    <option>6</option>
                    <option>7</option>
                    <option>8</option>
                    <option>9</option>
                    <option>10</option>
                </select>
                <c:if test="${not empty error}">
                    <font color="red">
                        ${error}
                    </font>
                </c:if>
                <input type="hidden" name="txtOrderID" value="${param.txtOrderID}" />
                <input type="hidden" name="txtDate" value="${param.txtODate}" />
                <input type="hidden" name="txtHotelID" value="${param.txtHotelID}" />
                <input type="submit" value="Submit" name="btAction" />
            </form>
    </body>
</html>
